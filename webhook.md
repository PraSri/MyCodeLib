# Webhook Callback System Design - Feedback Summary

## 1. High-Level Architecture
A production-ready webhook system should be split into two separate pipelines:
*   **Control Plane (Registration):** Handles webhook registration and config changes. 
    *   *Flow:* Organization calls `POST /webhooks` -> API Gateway -> Registration Service -> Postgres DB -> Emits "subscription changed" event -> Updates Redis/In-memory cache.
*   **Data Plane (Event Delivery):** Handles high-volume event delivery.
    *   *Flow:* Internal Service -> Kafka -> Delivery Worker -> Cache Lookup (org_id, event_type) -> Expand into delivery jobs -> Sign with HMAC -> HTTP Callback.

## 2. Kafka Topology & Routing
*   **Avoid Topic Explosion:** Do not create a topic per organization (can crash Kafka at scale).
*   **Optimal Strategy:** Keep one topic per `event_type` and use `organization_id` as the **partition key**. 
*   **Worker Consumption:** Delivery workers in a consumer group automatically get only the orgs for their assigned partitions—eliminating the need for application-level filtering. 
*   **Hot Tenants:** If one organization dominates, you can sub-shard (e.g., `org_id:{1-10}`) only if you are willing to sacrifice strict ordering for that organization.

## 3. Subscription Lookup & Caching
*   **Avoid the Database on the Hot Path:** Do not query Postgres per event to find webhook URLs.
*   **Routing Cache:** Maintain a routing cache in Redis keyed by `(org_id, event_type) -> [webhooks]`. 
*   **CDC/Events for Updates:** When subscriptions change, the Registration service should publish an event to update the cache atomically with versioning.

## 4. Retries, Backoff, and Dead Letter Queue (DLQ)
*   **Retry Limits:** 3 attempts are too low for internet-facing endpoints. Use a longer schedule (e.g., immediate, 5s, 30s, 2m, 10m, 1h, 6h, capped at 24-48h). Include jitter.
*   **Kafka Delayed Delivery:** Kafka doesn't support native delayed queues. Use the **Retry Topic Pattern** (e.g., `retry-5s`, `retry-30s`) or a **Redis ZSET** scheduler to re-enqueue payloads when their `next_attempt_at` time arrives.
*   **Dead Letter Queue:** After exhausting max retries, send the event to a DLQ and log the failure (with reason) in an append-only store like Cassandra.
*   **Reconciliation:** Expose APIs to list failed deliveries by time/org and provide a replay tool.

## 5. Rate Limiting, Timeouts, & Security
*   **Rate Limiting:** Use a Redis token bucket per webhook URL. If rate-limited, do not drop the event—schedule it for a retry with a small delay to preserve at-least-once delivery and propagate backpressure.
*   **Timeouts:** Set connect timeouts low (~300-500ms) and total request timeouts tight (1-3s). Cap per-endpoint concurrency and use circuit breakers to prevent failing endpoints from blocking workers.
*   **Security & Idempotency:**
    *   Include a stable `delivery_id` (e.g., `hash(event_id + webhook_id)`) so clients can deduplicate.
    *   Sign an HMAC over the `timestamp`, `delivery_id`, `attempt`, and `payload` to ensure authenticity and prevent replay attacks.

## 6. Multi-Region Strategy
When designing for **Availability over Consistency**:
*   **Control Plane:** Use an active-active setup with a globally replicated database for subscription metadata. A slight delay in config propagation is acceptable.
*   **Data Plane:** Maintain one Kafka cluster per region. Internal services publish events locally.
*   **Failover Handling:** Use active-passive event processing. Events are written locally for low latency and asynchronously replicated to a secondary region.
*   **The Trade-off:** During a hard regional failure, you accept a small data loss (RPO) of un-replicated events in exchange for high availability. 
*   **Duplicates:** Failovers will inevitably cause duplicate sends. This reinforces the need for stable `delivery_id` headers and idempotent consumers.
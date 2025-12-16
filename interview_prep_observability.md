# Interview Preparation: Observability & System Health Framework

## Your Projects Enhanced with Observability Narrative

### 1. AdTech Catalog Services (Walmart)
**Scale**: 5+ Scala/Play microservices | 1M+ daily requests | 100M+ products | 100K+ advertisers

**Observability Strategy to discuss:**

**SLOs (Define "Healthy" in Numbers)**
- Latency SLO: p99 < 200ms (critical for advertiser real-time campaign setup)
- Error Rate SLO: < 0.1% (100M+ products, small % = significant impact)
- Availability SLO: 99.95% (advertisers depend on uptime for campaign activation)

**Three Pillars Implementation**
- **Metrics**: Golden signals per service (latency p50/p95/p99, traffic QPS, error rate, saturation/CPU)
- **Logs**: Structured logs with request context, query patterns, cache hits/misses
- **Traces**: Distributed tracing (X-Ray/Jaeger) to track request flow from API → Elasticsearch → response

**Liveness vs Readiness**
- Liveness: JVM memory pressure, GC pauses affecting process health
- Readiness: Elasticsearch cluster health before accepting traffic from load balancer

**Health Check Layers**
1. Basic: JVM heap, thread pool status
2. Fast dependency: Elasticsearch cluster state (sub-second)
3. Synthetic: Periodic test queries to verify end-to-end latency

**Golden Signals Dashboard (OnCall)**
- Primary metric: p99 latency (30% reduction → why we track it)
- Traffic: Daily request volume vs baseline
- Error rate: Spike detection for failing queries
- Saturation: Elasticsearch shard allocation, connection pool usage

**Correlation IDs**
- Every advertiser search request gets unique ID: traced from API Gateway → Load Balancer → Microservice → Elasticsearch
- Enables debugging of slow queries or failures for specific advertisers

**Incident Response**
- Feature flag: Gradual rollout of new Elasticsearch query optimization (canary 5% → 25% → 100%)
- Runbook: "If p99 > 300ms: check ES cluster health, GC logs, and query patterns"
- Postmortem: "Previous latency incident → improved monitoring of slow queries → added query timeout alerts"

---

### 2. Display Ads Inventory Management (Walmart)
**Scale**: Vespa-powered forecasting APIs | 10K+ advertisers | 99.9% uptime | 25% revenue increase

**Observability Strategy to discuss:**

**SLOs**
- Uptime: 99.9% (0.1% downtime = ~43 minutes/month, directly impacts ad revenue)
- Forecasting accuracy: Within 5% of actual demand (business metric)
- API response time: p95 < 150ms for forecast calls

**Three Pillars for Forecasting**
- **Metrics**: Forecast accuracy deviation, model staleness, prediction latency per advertiser tier
- **Logs**: Which advertisers queried, forecast confidence scores, demand spikes
- **Traces**: Full path from forecasting API → Vespa index lookup → model inference → response

**Liveness & Readiness**
- Liveness: Model serving process, Vespa daemon health
- Readiness: Model freshness (is today's data loaded?) + Vespa index ready before accepting requests

**Health Check Layers**
1. Basic: Vespa process + model file existence
2. Fast: Index query latency on small test set
3. Scheduled: Run synthetic forecast for top 100 advertisers at 2am, compare with previous day

**Golden Signals Dashboard**
- Latency: p50/p95/p99 forecast response time
- Traffic: Forecasts/second per advertiser tier
- Errors: Failed predictions, timeout queries
- Saturation: Vespa memory usage, model inference queue depth

**Correlation IDs**
- Track individual advertiser forecast requests through: API endpoint → Vespa query → ML model inference
- Helps debug "why did forecast fail for advertiser X?"

**Feature Flags & Rollout**
- New forecasting model: Start with 2% traffic, monitor accuracy vs old model
- Slow rollout: Watch revenue impact before full deployment
- Rollback: If accuracy drops < 95%, auto-rollback to previous model

**Incident Narrative**
- "25% revenue increase came with uptime guarantee — this required: SLO definition, layered health checks, correlation tracing, and runbooks for common failures like model staling"

---

### 3. AdTech MCP Server (Walmart)
**Modern AI/LLM System**: Spring AI integration with Walmart's Marty AI Agent platform

**Observability Strategy to discuss:**

**SLOs for AI Services**
- LLM response time: p99 < 500ms (agent orchestration latency matters)
- Token accuracy: Semantic correctness of advertiser onboarding steps
- Availability: 99.9% (AI agents calling this service for campaign activation)

**Three Pillars for AI/LLM Systems**
- **Metrics**: Token usage per advertiser, model inference time, hallucination detection rate, downstream API call success
- **Logs**: Prompt context, model output, tool selection decisions, advertiser feedback
- **Traces**: Marty Agent request → MCP Server → Tool invocations → LLM calls → Campaign creation

**Liveness & Readiness**
- Liveness: LLM client connection, Spring context alive
- Readiness: LLM model loaded + MCP schema validated before agent invokes

**Health Check Layers**
1. Basic: Spring AI client connection, model availability
2. Fast: Test tool invocation (semantic validation)
3. Scheduled: Daily test campaign creation through full Marty → MCP → LLM flow

**Golden Signals for AI Services**
- Latency: LLM response time, end-to-end campaign activation latency
- Traffic: Requests/second from Marty agent
- Errors: Tool invocation failures, hallucination detection
- Saturation: LLM model queue, token throughput

**Correlation IDs**
- Chain IDs: Marty request ID → MCP Server ID → LLM call ID → Tool execution ID
- Enables debugging: "Why did this advertiser's onboarding fail? Trace the full flow"

**Feature Flags & Rollout**
- New LLM model (better understanding): Canary with 10% of advertisers, measure task success rate
- Gradual: 10% → 25% → 50% → 100%
- Rollback: If success rate drops, revert to previous model

**Incident Response**
- Runbook: "If LLM response time spikes: check token costs, model load, downstream API latency"
- Postmortem: "Previous hallucination incident → added semantic validation → improved prompts"

---

### 4. Loyalty Points & Wallet Service (Paytm)
**Scale**: Kafka + DynamoDB | 10M+ monthly transactions | 50M+ users | 99.9% uptime

**Observability Strategy:**

**SLOs**
- Transaction success rate: 99.95% (financial system)
- Latency: p99 < 100ms (user expectation for points credit)
- Uptime: 99.9% (50M+ users depend on it)

**Three Pillars**
- **Metrics**: Transaction success rate, points processed/second, Kafka lag, DynamoDB throttling
- **Logs**: Transaction ID, user ID, points amount, final balance (PII redacted)
- **Traces**: User request → Service → Kafka producer → Consumer → DynamoDB write → Confirmation

**Liveness & Readiness**
- Liveness: JVM, Kafka client connection alive
- Readiness: Kafka consumer lag acceptable + DynamoDB connection ready

**Health Checks**
1. Basic: Process alive, Kafka brokers reachable
2. Fast: DynamoDB write test (shadow write)
3. Scheduled: Hourly synthetic transaction, verify points balance

**Golden Signals**
- Latency: p50/p95/p99 points credit time
- Traffic: Transactions/second (track seasonal spikes)
- Errors: Failed transactions, Kafka rebalances, DynamoDB hotspots
- Saturation: Kafka partition lag, DynamoDB write throttling

**Correlation IDs**
- Transaction ID propagated: API → Kafka topic → Consumer → DynamoDB → Final confirmation

**Feature Flags & Slowdown**
- New loyalty algorithm: Route 5% of users, measure redemption rate
- Gradual rollout based on success metrics
- Runbook: "High Kafka lag? Check consumer threads, DynamoDB throttling"

---

### 5. Recommendation Service (Paytm)
**Scale**: Spring Boot + Elasticsearch | 50M+ users | Finding nearby stores

**Observability Strategy:**

**SLOs**
- Search latency: p99 < 200ms (Home page UX)
- Result relevance: Top 3 results match user intent
- Availability: 99.95%

**Golden Signals**
- Latency: p50/p95/p99 search time
- Traffic: Searches/second by time of day
- Errors: Failed Elasticsearch queries, timeouts
- Saturation: Elasticsearch shard load, query complexity

**Correlation IDs**
- User session ID → Search request → Elasticsearch query → Results returned

**OnCall Dashboard**
- Main: p99 latency + error rate
- Dependencies: Elasticsearch cluster health
- Alerts: Latency spike (> 300ms) or error rate > 1%

---

## Interview Talking Points: How to Weave This In

### For System Design Questions:
*"At Walmart, we realized that the 30% latency reduction for AdTech Catalog wasn't just about optimizing queries. After launch, we had to design for observability: We defined SLOs (p99 < 200ms for 100K+ advertisers), implemented correlation IDs through Elasticsearch, and set up dashboards that surfaced golden signals—latency, traffic, errors, saturation. This is what kept the system healthy at 1M+ daily requests."*

### For Operational Excellence Questions:
*"The 99.9% uptime for Display Ads wasn't luck—it was designed observability. We layered health checks: basic JVM checks, fast dependency checks on Vespa index readiness, and scheduled synthetic forecasts. When incidents happened, we had runbooks tied to specific alerts, and postmortems that improved our dashboards and code, not just blame."*

### For AI/LLM Integration Questions:
*"With the MCP Server, we applied the same observability principles to AI systems. Correlation IDs traced Marty Agent → MCP Server → LLM → Tool invocations. We monitored golden signals like LLM response time and semantic correctness. Feature flags let us test new models on 10% of advertisers before full rollout."*

### For Scaling Questions:
*"At Paytm with 50M+ users and 10M+ monthly transactions, we couldn't just build and forget. We separated liveness (is the process healthy?) from readiness (should it get traffic?). Kafka lag monitoring, DynamoDB throttling alerts, and synthetic transaction verification kept the system humming 24/7."*

### For Incident & Reliability Questions:
*"Every incident became observability feedback. If something broke in production, we asked: Did we have the right alerts? Should our dashboard have surfaced this faster? Did our runbook catch this? This loop—incident → improved monitoring → better code—is what differentiates mature systems."*

---

## Key Numbers to Memorize for Your Interview

| Project | Scale | SLO | Golden Signal | Key Tech |
|---------|-------|-----|---------------|----------|
| AdTech Catalog | 1M+ daily requests, 100M+ products | p99 < 200ms, 99.95% uptime | Latency spikes indicate ES issues | Scala, Play, Elasticsearch |
| Display Ads | 10K+ advertisers | 99.9% uptime, forecast accuracy ±5% | Vespa saturation early warning | Vespa, forecasting model |
| MCP Server | Marty AI Agent integration | 99.9% uptime, LLM p99 < 500ms | Token usage, hallucination rate | Spring AI, LangChain |
| Loyalty Points | 50M+ users, 10M+ monthly transactions | 99.95% success, p99 < 100ms | Kafka lag, DynamoDB throttling | Kafka, DynamoDB, Kotlin |
| Recommendation | 50M+ users | p99 < 200ms, 99.95% uptime | Elasticsearch shard hotspots | Elasticsearch, Spring Boot |

---

## Follow-Up Questions You Should Prepare For

1. *"Walk me through your incident response. Tell me about a time latency spiked in production."*
   - Answer with: Your SLO, how you detected it (which golden signal), the correlation ID trace, and the runbook

2. *"How would you monitor this new service for a launch?"*
   - Answer: Define SLOs first, implement 3 pillars (metrics/logs/traces), layer health checks, design OnCall dashboard

3. *"What's the difference between liveness and readiness probes?"*
   - Answer: With real examples from your projects (Kafka consumer lag, Elasticsearch readiness)

4. *"How do feature flags help with reliability?"*
   - Answer: Slow rollouts (5% → 25% → 100%), watch golden signals, rollback if metrics degrade

5. *"Tell me about a postmortem that changed how you build systems."*
   - Answer: Structure—what went wrong, how observability missed it, what changed
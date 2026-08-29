## Requirements & Scale

* **Functional:**
* **Ingest View Events:** Record a view when a user plays a video past a threshold (e.g., 30s).
* **Fetch View Counts:** Return total view counts for a video in near-real-time (<1 second lag).
* **Deduplication & Anti-Abuse:** Filter bot traffic and duplicate views from the same user/IP within a time window (e.g., 24 hours).


* **Non-Functional & Scale (10B Views/Day):**
* **Write Throughput:** ~115,000 avg write RPS, peaking at 300,000+ RPS.
* **Read Throughput:** ~500,000 read RPS across video playback pages, search, and feeds.
* **Latency:** Write ingestion <20ms response to client; Read latency <50ms.
* **Consistency:** Eventual consistency is acceptable; bounded count drift (<1–2% transient delay) is tolerable.



---

## High-Level Architecture

```
[ Client App ] ---> [ API Gateway / CDN ]
                         |
                         v
              [ View Ingestion Service ]
                         |
               (Kafka / Event Queue)
                         |
              [ Stream Processor (Flink) ] <---> [ Redis Cluster / Cache ]
                         |                                 ^
                         v                                 |
              [ Storage Writer Service ]         [ View Read Service ]
                         |                                 ^
                         v                                 |
              [ NoSQL DB (Cassandra/Dynamo) ] -------------+

```

---

## Core Components & Data Flow

* **API Gateway & Ingestion Service:**
* Accepts `POST /v1/videos/{id}/views` events with payload containing `video_id`, `user_id`, `session_id`, `timestamp`, and client fingerprint/IP.
* Performs lightweight token verification and immediately pushes an event payload to Kafka, returning HTTP 202 Accepted.


* **Stream Processing (Apache Flink / Spark Streaming):**
* Consumes raw view event streams from Kafka.
* **Deduplication Layer:** Uses a sliding-window Redis Bloom Filter or HyperLogLog per `(user_id, video_id)` over 24-hour windows to drop invalid duplicate views.
* **Tumbling Window Aggregation:** Micro-batches valid view increments in 5-second windows per `video_id` to reduce database write operations by orders of magnitude.


* **Data Storage Strategy:**
* **In-Memory Cache (Redis Cluster):** Stores current active view counts (`video:{id}:counts`). Updated continuously by Flink via atomic operations (`INCRBY`).
* **Persistent Store (Cassandra or DynamoDB):** Key-value schema partitioned by `video_id`. Flink periodically flushes aggregated delta counts to disk (e.g., every 10 seconds or every N events).


* **Read Service:**
* Handles GET requests for view counts. Queries Redis cluster first; falls back to persistent storage on cache miss.



---

## SSE Deep Dives & Key Tradeoffs

### 1. Handling Hot Key Contention (Viral Videos)

A top-trending video receiving millions of simultaneous views can bottleneck a single Redis node or partition key.

* **Partition Sharding (Distributed Counters):**
* Split the video count across $N$ virtual sub-keys in Redis: `video:123:count:1` through `video:123:count:N`.
* Ingestion workers write deltas to a randomly chosen sub-key shard ($1..N$).
* Reads sum all $N$ shards (or read an asynchronously aggregated master counter updated every second).



### 2. Anti-Abuse & Deduplication Scale

* **HyperLogLog / Bloom Filters:** Storing billions of `(user_id, video_id)` pairs in exact Redis sets requires excessive RAM. Using Bloom Filters or HyperLogLog provides $99\%$ accuracy with minimal fixed memory consumption per video.
* **Rate Limiting & Fingerprinting:** Implement IP-based sliding-window rate limiters at the API Gateway level to block botnet requests before queue ingestion.

### 3. Failure Recovery & Fault Tolerance

* **At-Least-Once Delivery:** Flink maintains checkpointing on Kafka consumer offsets. If a worker node crashes, Flink replays state from the last committed offset without losing view counts.
* **Batch Flushing Risk:** If Redis fails, counts are re-hydrated directly from the persistent Cassandra store plus Kafka event replaying.
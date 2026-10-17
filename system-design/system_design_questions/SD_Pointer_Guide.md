# 🧠 System Design Interview Guide (FAANG Focus)

> This guide expands core system design concepts with practical examples and mental models. It's designed to build intuition rather than memorize specific problems.

---

## 📡 Core Concepts

### Networking Essentials
- **Latency vs Throughput**: Latency is time to deliver a single message; throughput is messages per second.
- **TCP vs UDP**: TCP reliable (handshake, retransmission) for HTTP, DBs. UDP fast but lossy for VoIP, gaming.
- **HTTP/1.1 vs HTTP/2 vs HTTP/3**: Multiplexing, header compression, QUIC transport.
- **DNS Resolution**: Recursive lookup, TTL, anycast for global distribution.
- **TLS/SSL**: Handshake overhead, session resumption.

### API Design
- **REST**: Resource-oriented, stateless, HTTP verbs.
    - *Example*: `/users/{id}` GET/PUT/DELETE.
- **GraphQL**: Single endpoint, client-specified fields, reduces over-fetching.
    - *Example*: GitHub API v4.
- **gRPC**: Protobuf, HTTP/2, bi-directional streaming, strong typing.
    - *Example*: Microservice internal communication.
- **Idempotency Keys**: For safe retries (stripe-like APIs).
- **Pagination**: Cursor-based vs offset.

### Data Modeling
- **Relational**: Normalization, ACID, joins.
- **Document**: Denormalized, flexible schema (MongoDB).
- **Graph**: Nodes & edges for highly connected data (Neo4j).
- **Time-series**: Optimized for timestamped metrics (InfluxDB).
- **Key-Value**: Simple lookups (Redis, DynamoDB).

### Database Indexing
- **B-Tree Index**: Default in most RDBMS; good for range queries.
- **Hash Index**: Exact match only (in-memory).
- **Composite Index**: Order of columns matters (leftmost prefix).
- **Covering Index**: All queried fields included, avoids row lookup.
- **Trade-off**: Indexes speed reads but slow writes and consume storage.

### Caching
- **Client-side**: Browser cache, mobile local storage.
- **CDN**: Edge caching for static assets and API responses.
- **Application Cache**: Redis/Memcached.
- **Database Cache**: Buffer pool, query cache.

### Sharding
- **Horizontal Partitioning**: Split data across multiple databases.
- **Shard Key Selection**: Avoid hotspots. Use hashed keys for uniform distribution.
- *Example*: **Uber** shards trip data by `rider_id` to keep a rider's history on one shard.

### Consistent Hashing
- Minimizes key redistribution when nodes are added/removed.
- Used in **DynamoDB**, **Cassandra**, **CDN request routing**.
- Virtual nodes help balance load.

### CAP Theorem
- **Consistency**: All nodes see same data at same time.
- **Availability**: Every request gets a response (non-error).
- **Partition Tolerance**: System continues despite network splits.
- *In practice*: CP (HBase) or AP (Cassandra) during partition; choose trade-off.

### Numbers to Know (Back-of-envelope)
| Operation | Approximate Time |
|-----------|------------------|
| L1 cache reference | 0.5 ns |
| Main memory reference | 100 ns |
| SSD random read | 150,000 ns (150 µs) |
| Round trip within same DC | 500,000 ns (500 µs) |
| Round trip CA to Netherlands | 150,000,000 ns (150 ms) |

- **Data conversions**: 1 char = 1 byte, 1 million chars = 1 MB.
- **Throughput assumptions**: 1 server can handle ~10k concurrent connections, ~500-1000 requests/sec (depending on workload).

---

## 🧰 Key Technologies

### Core Database

#### Relational (SQL)
- **SQL Joins**: Inner, left/right, full outer, cross.
- **Indexes**: B-Tree, Bitmap, GIN (PostgreSQL for arrays).
- **Transactions**: ACID with isolation levels (Read Committed, Repeatable Read, Serializable).
- **Use case**: Financial ledgers, inventory systems.
- *Example*: **Booking.com** uses PostgreSQL for reservations.

#### NoSQL Database
- **Flexible Data Models**: Schema-less, nested documents.
- **Scalability**: Horizontal scaling via sharding.
- **Handling Big Data & Real-time Apps**.
- *Example*: **Netflix** uses Cassandra for viewing history (high write throughput).

### Blob Storage (S3, GCS)
- **Design YouTube**: Videos stored in S3; metadata (title, description, transcoding status) in SQL/NoSQL.
- **Design Instagram**: Images/videos in S3 + CDN; user posts metadata in DB.
- **Design Dropbox**: Files chunked and stored in blob storage; metadata and sharing info in DB.
- **Durability**: Replication across AZs/regions (S3 has 99.999999999% durability).
- **Scalability**: Virtually unlimited capacity.
- **Cost**: Storage classes (Standard, Infrequent Access, Glacier).
- **Security**: Signed URLs, IAM policies, encryption at rest.
- **Client Direct Upload**: Pre-signed URLs allow clients to upload securely without passing through backend.
- **Chunking**: Large files split for parallel upload/resume.

### Search Optimized Database (Elasticsearch)
- **Inverted Index**: Maps terms to document IDs (like a book index).
- **Tokenization**: Splits text into tokens ("Hello World" → ["hello","world"]).
- **Stemming**: Reduces words to root form ("running" → "run").
- **Fuzzy Search**: Levenshtein distance for typos.
- **Scaling**: Shards (data partitions) and replicas (high availability).
- *Example*: **Tinder** uses Elasticsearch for geo-based profile discovery.

### API Gateway
- **AWS API Gateway**: Managed service with throttling, caching, authorization.
- **Kong**: Open-source, plugin-based.
- **Apigee**: Enterprise with analytics.
- **nginx / Apache**: Lightweight reverse proxy.
- *Responsibilities*: Rate limiting, authentication, request/response transformation, routing.

### Load Balancer
- **L4 (Transport)**: TCP/UDP level; persistent connections for WebSockets.
- **L7 (Application)**: HTTP routing based on headers, cookies.
- **AWS ELB/ALB**, **NGINX**, **HAProxy**.
- *Algorithms*: Round-robin, least connections, IP hash (sticky sessions).

### Queue (Kafka, SQS)
- **Buffer for Bursty Traffic**: Decouples producers and consumers.
- **Message Ordering**: FIFO queues (SQS FIFO, Kafka partitions).
- **Retry Mechanisms**: Exponential backoff with jitter.
- **Dead Letter Queues (DLQ)**: Store failed messages for inspection.
- **Scaling with Partitions**: Kafka partitions allow parallel consumption.
- **Backpressure**: Slow consumers can be addressed by increasing consumer count or queue size.

### Streams / Event Sourcing (Kafka, Flink, Kinesis)
- **Real-time Processing**: Clickstream analytics, fraud detection.
- **Event Sourcing**: Store immutable sequence of events instead of current state.
    - *Example*: **Ledger system** replays transactions to compute balance.
- **Multiple Consumer Groups**: Each group gets a copy of the stream.
- **Replication**: Data durability across brokers.
- **Windowing**: Aggregations over time windows (tumbling, sliding).
- *Example*: **Uber** uses Kafka + Flink for real-time driver-rider matching.

### Distributed Lock (Redis, ZooKeeper)
- **Use Cases**:
    - **E-commerce checkout**: Prevent overselling of limited stock.
    - **Ride-sharing matchmaking**: Prevent double assignment of a driver.
    - **Distributed cron jobs**: Ensure only one node runs scheduled task.
    - **Online auction**: Serialize bids for final seconds.
- **Locking Mechanism**: Setnx in Redis with TTL to avoid deadlock.
- **Lock Expiry**: Renewal mechanism for long operations.
- **Granularity**: Lock only necessary resource (e.g., `product_id:123` not entire product table).
- **Deadlocks**: Avoid by using timeout and ordered lock acquisition.

### Distributed Cache (Redis, Memcached)
- **Save Aggregated Metrics**: Daily active users, trending topics.
- **Reduce DB Queries**: Cache frequently accessed user profiles.
- **Speed Up Expensive Queries**: Cache results of complex joins.
- **Eviction Policy**: LRU, LFU, TTL.
- **Cache Invalidation**: Write-through (update cache on write), cache-aside (lazy loading).
- **Cache Write Strategies**:
    - *Write-through*: Write to cache and DB simultaneously (consistency).
    - *Write-around*: Write directly to DB, cache on read (good for write-heavy).
    - *Write-back*: Write to cache, async flush to DB (risks data loss).
- *Example*: **Twitter** caches tweet timelines in Redis.

### CDN (Cloudflare, Akamai, CloudFront)
- **Static Assets**: Images, CSS, JS.
- **API Response Caching**: Edge caching of semi-static API data.
- **Eviction Policies**: TTL-based purge or invalidation APIs.
- *Example*: **Netflix** uses Open Connect CDN appliances inside ISPs.

### Docker
- **Unit of Deployment**: Container image includes OS, dependencies, app code.
- **Isolation**: Namespaces and cgroups.
- **Consistency**: "Works on my machine" problem solved.

### Kubernetes (K8s)
- **Orchestrator**: Manages container lifecycles.
- **Self-healing**: Restarts failed containers.
- **Horizontal Pod Autoscaler (HPA)**: Scale based on CPU/memory or custom metrics.
- **Service Discovery**: Internal DNS (e.g., `my-service.namespace.svc.cluster.local`).
- **Rolling Updates**: Zero-downtime deployments.
- **Ingress**: L7 routing to services.
- *Example Statement*: "I would containerize microservices using Docker and deploy them on Kubernetes. This enables HPA to handle traffic spikes and self-healing for high availability."

---

## 🧩 Common Patterns & System Design Recipes

### Pushing Real-time Updates
- **HTTP Polling**: Simple but inefficient; high overhead.
- **SSE (Server-Sent Events)**: Unidirectional from server to client over HTTP.
- **WebSockets**: Full-duplex, low latency; used in chat, live sports.
- **Pub/Sub**: Backend publishes to topic; clients subscribed via WebSocket receive updates.
- *Example*: **Slack** uses WebSockets + fallback to long polling.

### Managing Long Running Tasks
- **Queues + Worker Pools**: Task is enqueued, workers pick up and process.
- **Monitoring**: Track queue depth, processing time, failure rate.
- *Example*: **Instagram** processes uploaded videos via async workers (transcoding, thumbnail generation).

### Dealing with Contention
- **Pessimistic Locking**: Lock row/table before update (SELECT ... FOR UPDATE). Good for high contention.
- **Optimistic Concurrency**: Version number or timestamp; retry on conflict. Good for low contention.
- *Example*: **Wikipedia edits** use optimistic concurrency (compare-and-swap on version).

### Scaling Reads
- **Read Replicas**: Asynchronous replication can cause lag.
- **Hot Keys**: A celebrity's profile becomes a hotspot.
    - *Solutions*: Client-side caching, in-memory cache with short TTL, key sharding.

### Scaling Writes
- **Sharding**: Partition data by a key.
- **Vertical Partitioning**: Split tables by columns (e.g., main profile vs extended attributes).
- **Handling Write Bursts**: Queue writes (e.g., likes on a viral post).
- **Batching Writes**: Accumulate and flush periodically.

### Handling Large Blobs
- **Pre-signed URLs**: Client uploads/downloads directly to cloud storage, reducing backend load.
- **CDN Delivery**: Cache blobs at edge for faster access.
- **Chunking + Resumable Uploads**: Use multipart upload APIs.

### Multi-step Processes (Saga Pattern)
- **Event Sourcing**: Record each step as an event; replay to recover state.
- **State Management**: Store intermediate state in a durable store.
- **Failure Recovery**: Compensating transactions to rollback.
- **Retry Logic**: Idempotent operations with exponential backoff.
- *Example*: **E-commerce order fulfillment**: Reserve inventory → Charge payment → Schedule shipment.

### Proximity Based Services
- **Geospatial Indexes**: Geohash, QuadTree, R-tree.
- **Redis Geospatial Data Type**: `GEOADD`, `GEORADIUS`.
- **Elasticsearch**: Geo-point and geo-shape queries.
- *Example*: **Yelp** uses geohash to find nearby restaurants.

---

## 🏢 System Design Examples (Mental Models)

### Design YouTube
- **Upload**: Client gets pre-signed URL → uploads to S3/GCS → notifies backend → transcode job queued → store metadata in DB.
- **Streaming**: Video served via CDN with adaptive bitrate (HLS/DASH).
- **Thumbnails**: Generated by workers and stored in blob storage.
- **Comments**: Relational DB with pagination.
- **View Count**: Increment in Redis and periodically flush to DB.

### Design Uber
- **Location Tracking**: Driver app sends location every 4 seconds via WebSocket to location service.
- **Matching**: Geospatial index (Redis Geo / QuadTree) to find nearby drivers.
- **Booking**: Distributed lock (Redis) to assign driver to rider.
- **Pricing**: Real-time stream processing (Kafka + Flink) for surge pricing.
- **Trip State**: Event sourcing for trip lifecycle (requested, accepted, started, completed).

### Design Twitter
- **Tweet Write**: Append to user's timeline cache (Redis list) for followers.
- **Timeline Read**: Fanout on write for active users; hybrid approach for celebrities (pull on read).
- **Search**: Early tweets in Elasticsearch.
- **Trending Topics**: Sliding window count of hashtags using stream processing.

### Design TinyURL
- **Short URL Generation**: Base62 encoding of an auto-increment ID or hash.
- **Storage**: Key-value store (DynamoDB) mapping short code to long URL.
- **Caching**: CDN + Redis for popular URLs.
- **Analytics**: Click events streamed to Kafka.

---

## 💡 Interview Tips

- **Clarify Requirements**: Functional (what the system does) and non-functional (scale, latency, consistency).
- **Back-of-envelope Estimation**: Estimate traffic, storage, bandwidth.
- **Data Flow Diagram**: Draw high-level architecture with arrows.
- **Deep Dive on 2-3 Components**: Be ready to discuss trade-offs (SQL vs NoSQL, push vs pull).
- **Discuss Failure Scenarios**: What if a cache node dies? What if a queue fills up?

---

*Happy studying! Build mental models, not memorized solutions.*
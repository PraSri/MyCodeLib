## Points

Scale - 1M rps, 100M DAU

3 algos
1. Fixed window counter
2. Sliding window log
3. Sliding window counter (Hybrid of above 2)
4. Token bucket

token bucket :
- each client has bucket
- each bucket can hold certain number of tokens (bucket size  = throttle)
- tokens are added at steady rate (refill rate)
- each request consumes one token
- if not token left, request is rejected
- track (tokens, last_refill_time) per client
- put in redis, fetch with HMGET alice:bucket tokens last_refill
- update bucket atomically using redis transaction to prevent race condition (HSET)
- to make read atomic as well, use Lua scripting


Deep drives:
1. Scale to 1M rps
2. high availability & fault tolerance
3. minimize latency overhead
4. handle hot keys
5. handle dynamic rules configuration
6. 


1M rps scale - distributed redis cluster
sharding - consistent hashing

high availability - redis master and read replicas

latency minimize
- connection pooling - pool of persistent connections rather than new TCP for every check
- allow connections reuse
- geo location of infra
- redis pipelining

hot key problem
- single client overwhelm a redis shard with enough requests
- client side rate limiting
- request queuing/batches
- premium tier
- automatic blocking for abusive traffic
- DDoS protection

handle dynamic configuation 
- poll based 
- push based - zookeeper - distributed configuration management with real time notifications

Notes for SSE role:
* **Focus & Ratio:** Shift to 60% breadth / 40% depth; quickly move past basic algorithms to distributed scaling.
* **Algorithm Trade-offs:** Justify selection (e.g., Token Bucket vs. Sliding Window Counter/Log) based on burstiness, accuracy, and memory.
* **Core Concepts:** Demonstrate mastery of consistent hashing, Redis Cluster, and connection pooling without prompting.
* **Atomic Concurrency:** Enforce state checks/updates via Lua scripts or `MULTI`/`EXEC` to prevent race conditions.
* **Limiter Placement:** Weigh API Gateway/edge vs. dedicated service vs. in-process based on latency and context availability.
* **Fail Strategy:** Evaluate fail-open (prioritize availability) vs. fail-closed (prioritize security/protection) during dependencies outages.
* **System Bottlenecks:** Proactively address hot keys, Redis node failure/sharding, and latency optimization (<10ms target).
* **Configuration:** Have clear opinions on dynamic rule updates and central configuration management (e.g., using ZooKeeper/etcd).

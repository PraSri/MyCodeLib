# Rate Limiter — Medium (Grey Orange)

### 1. Invariant Trigger
* **Signal:** Throttling API requests per client with burst handling or sliding window limits (Grey Orange round) -> Token Bucket & Sliding Window Log.
* **Core Invariant:** 
  - Token Bucket: Refill amount is continuous: $\min(C, \text{tokens} + \Delta t \times R)$. Consume 1 token if $\text{tokens} \ge 1$.
  - Sliding Window Log: Evict timestamps $\le \text{now} - W$. Allow request if remaining log size $< \text{maxRequests}$.

### 2. Failure Traps & Edge Cases
* Floating point nano-precision: Calculate elapsed time using `System.nanoTime()` rather than `currentTimeMillis()` to avoid clock skew and NTP backward jumps.
* Memory leak in logs: Inactive client entries stay in memory. In production, wrap client buckets in a Guava/Caffeine cache with TTL expiration.
* Concurrency race conditions: Updating `tokens` and `lastRefill` must be atomic under client-level lock.

### 3. Complexity & Variations
* **Time:** $O(1)$ for Token Bucket; $O(\log N)$ or amortized $O(1)$ for Sliding Window Log.
* **Space:** $O(U)$ for Token Bucket where $U$ is active users; $O(U \times R)$ for Sliding Window Log.
* **Google Follow-up:** "How to implement this across a distributed cluster?" -> Redis + Lua script with Token Bucket or Sliding Window Counter using Sorted Sets (`ZADD`, `ZREMRANGEBYSCORE`).

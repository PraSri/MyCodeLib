# Ad Server — Hard (PhonePe R1)

### 1. Invariant Trigger
* **Signal:** High-throughput ad matching engine with real-time budget deduction, targeting filters, and frequency capping (PhonePe R1 round) -> Pipeline Architecture with Auction Ranking.
* **Core Invariant:** Pipeline order: (1) Filtering (Targeting + Frequency Cap) $\to$ (2) Scoring ($eCPM = \text{Bid} \times \text{QualityScore}$) $\to$ (3) Atomic budget reservation via `CAS`.

### 2. Failure Traps & Edge Cases
* Floating point budget drift: Adding floating-point doubles directly results in precision loss over millions of transactions. Always represent currency/spend in micro-units (integer micros = cents $\times 10^4$) with `AtomicLong.compareAndSet()`.
* Frequency cap race condition: Multiple concurrent requests for the same user can breach frequency caps without thread-safe counters.
* Unmatched campaigns or zero eCPM.

### 3. Complexity & Variations
* **Time:** $O(A \log A)$ where $A$ is number of eligible ads matching user context.
* **Space:** $O(A + U \times A)$ for ad index and user frequency counter maps.
* **Google Follow-up:** "How to serve 500,000 ad requests/sec under 20ms p99 latency?" -> Inverted index on targeting attributes, distributed budget caching with local worker allocation slices, and asynchronous event log flushing.

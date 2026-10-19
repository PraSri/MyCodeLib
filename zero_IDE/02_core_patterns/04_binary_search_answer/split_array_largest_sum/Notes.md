# Split Array Largest Sum — Hard

### 1. Invariant Trigger
* **Signal:** Minimize the maximum subarray sum when splitting into $k$ non-empty contiguous subarrays -> Binary search on target subarray sum.
* **Core Invariant:** If a maximum subarray sum of $M$ is feasible with $\le k$ subarrays via greedy packing, any $M' > M$ is also feasible. Monotonic predicate $F \dots F T T \dots T$.

### 2. Failure Traps & Edge Cases
* Lower bound of search space: `lo` must be $\max(nums)$ because no subarray can have a sum smaller than the largest individual element.
* Initializing split count: `count` starts at 1 (the first subarray).
* Contiguity: Subarrays must be contiguous; sorting is forbidden.

### 3. Complexity & Variations
* **Time:** $O(N \log(\sum nums - \max(nums)))$.
* **Space:** $O(1)$ constant auxiliary space.
* **Google Follow-up:** "What if subarrays do not need to be contiguous?" -> Subset sum / Partition problem (NP-Hard, requires DP or backtracking).

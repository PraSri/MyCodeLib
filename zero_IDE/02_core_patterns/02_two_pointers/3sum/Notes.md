# 3Sum — Medium

### 1. Invariant Trigger
* **Signal:** Find all unique triplets summing to zero without duplicate sets -> Sort array, fix first element, and run Two Pointers for target `-nums[i]`.
* **Core Invariant:** In a sorted array, if $nums[l] + nums[r] < \text{target}$, only incrementing $l$ can increase the sum; if $> \text{target}$, only decrementing $r$ can decrease it.

### 2. Failure Traps & Edge Cases
* Triplet deduplication trap: Must skip duplicate values at ALL 3 levels ($i$, $l$, and $r$). Using a `Set<List<Integer>>` adds significant memory and hashing overhead.
* Array sorting requirement: Two pointers requires monotonicity; sorting is non-negotiable.
* Boundary: All zeros `[0, 0, 0, 0]` must produce exactly one triplet `[[0, 0, 0]]`.

### 3. Complexity & Variations
* **Time:** $O(N^2)$ outer loop times two-pointer inner scan.
* **Space:** $O(1)$ auxiliary space excluding sorting and output list.
* **Google Follow-up:** "How to generalize this to k-Sum in $O(N^{k-1})$ time?" -> Recursive reduction from $k$-Sum down to 2-Sum using two pointers.

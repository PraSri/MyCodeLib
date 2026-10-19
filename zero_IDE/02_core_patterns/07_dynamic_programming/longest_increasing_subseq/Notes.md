# Longest Increasing Subsequence — Medium

### 1. Invariant Trigger
* **Signal:** Find length of longest strictly increasing subsequence in $O(N \log N)$ time -> Patience Sorting with binary search.
* **Core Invariant:** `tails[i]` stores the smallest tail of all increasing subsequences of length $i + 1$. `tails` is strictly monotonic increasing, enabling binary search for insertion.

### 2. Failure Traps & Edge Cases
* Strictly increasing vs non-decreasing: For strictly increasing, condition is `tails[mid] >= x`, so duplicates overwrite existing positions rather than extending length.
* Misinterpreting `tails` content: `tails` does NOT represent the actual LIS subsequence elements, only the lengths.
* All elements equal: returns length 1.

### 3. Complexity & Variations
* **Time:** $O(N \log N)$ performing $N$ binary searches.
* **Space:** $O(N)$ auxiliary space for `tails` array.
* **Google Follow-up:** "How to reconstruct the actual LIS sequence in $O(N \log N)$?" -> Maintain parent index pointers alongside tails indices.

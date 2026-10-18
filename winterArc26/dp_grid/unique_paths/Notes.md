# Unique Paths

[LeetCode](https://leetcode.com/problems/unique-paths/) · Medium · Pattern: `dp_grid`

- **Trigger Invariant:** Only right/down moves ⇒ paths(r, c) = paths(r−1, c) + paths(r, c−1); a single row rolled in place suffices.
- **Edge Cases:** 1×n or m×1 → 1; m = n = 1 → 1; result fits int under constraints (≤ 2·10^9).
- **Complexity:** Time O(m · n) · Aux space O(n).

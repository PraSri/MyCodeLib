# Permutations

[LeetCode](https://leetcode.com/problems/permutations/) · Medium · Pattern: `backtracking`

- **Trigger Invariant:** Fill positions left to right; each position may take any element not yet used — a `used[]` mask enforces that, giving n! leaves.
- **Edge Cases:** Single element → [[x]]; distinct values assumed (duplicates need sort + skip rule); copy the path at leaves.
- **Complexity:** Time O(n · n!) · Aux space O(n).

# Longest Increasing Path in a Matrix

[LeetCode](https://leetcode.com/problems/longest-increasing-path-in-a-matrix/) · Hard · Pattern: `dp_grid`

- **Trigger Invariant:** Strictly increasing moves can never cycle, so the grid is a DAG; memoised DFS computes each cell's longest path once.
- **Edge Cases:** 1×1 → 1; all equal values → 1; memo value 0 means 'not computed' (every real answer ≥ 1).
- **Complexity:** Time O(m · n) · Aux space O(m · n).

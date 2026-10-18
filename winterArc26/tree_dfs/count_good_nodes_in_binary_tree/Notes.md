# Count Good Nodes in Binary Tree

[LeetCode](https://leetcode.com/problems/count-good-nodes-in-binary-tree/) · Medium · Pattern: `tree_dfs`

- **Trigger Invariant:** 'Good' depends only on the max along the root-to-node path, so pass that max down as DFS state.
- **Edge Cases:** Root is always good; equal values count as good (≥); all-decreasing path → only root; negative values.
- **Complexity:** Time O(n) · Aux space O(h).

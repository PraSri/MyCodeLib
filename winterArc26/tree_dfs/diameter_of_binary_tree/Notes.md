# Diameter of Binary Tree

[LeetCode](https://leetcode.com/problems/diameter-of-binary-tree/) · Easy · Pattern: `tree_dfs`

- **Trigger Invariant:** Every path has a unique highest node where it bends; at that node the path length = height(left) + height(right). Return height upward, track the bend globally.
- **Edge Cases:** Single node → 0 (edges, not nodes); the diameter may not pass through the root; skewed tree.
- **Complexity:** Time O(n) · Aux space O(h).

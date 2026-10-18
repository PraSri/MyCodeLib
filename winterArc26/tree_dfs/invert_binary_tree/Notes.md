# Invert Binary Tree

[LeetCode](https://leetcode.com/problems/invert-binary-tree/) · Easy · Pattern: `tree_dfs`

- **Trigger Invariant:** Mirroring a tree = swap children at the root and mirror each subtree; the operation is local, so any traversal order works.
- **Edge Cases:** Empty tree → null; single node; deep skewed tree (recursion depth = height).
- **Complexity:** Time O(n) · Aux space O(h).

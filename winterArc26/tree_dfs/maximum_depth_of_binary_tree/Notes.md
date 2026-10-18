# Maximum Depth of Binary Tree

[LeetCode](https://leetcode.com/problems/maximum-depth-of-binary-tree/) · Easy · Pattern: `tree_dfs`

- **Trigger Invariant:** depth(node) = 1 + max(depth(left), depth(right)) — a post-order fold where null contributes 0.
- **Edge Cases:** Empty tree → 0; single node → 1; skewed tree (h = n).
- **Complexity:** Time O(n) · Aux space O(h).

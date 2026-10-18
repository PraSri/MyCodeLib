# Balanced Binary Tree

[LeetCode](https://leetcode.com/problems/balanced-binary-tree/) · Easy · Pattern: `tree_dfs`

- **Trigger Invariant:** Compute height bottom-up and short-circuit with −1 as an 'unbalanced' sentinel, so each node is visited once instead of recomputing heights (O(n²)).
- **Edge Cases:** Empty tree → true; balance must hold at EVERY node, not just the root; long chains of length ≥ 3 fail.
- **Complexity:** Time O(n) · Aux space O(h).

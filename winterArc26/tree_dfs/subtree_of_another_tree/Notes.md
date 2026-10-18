# Subtree of Another Tree

[LeetCode](https://leetcode.com/problems/subtree-of-another-tree/) · Easy · Pattern: `tree_dfs`

- **Trigger Invariant:** subRoot is a subtree iff some node of root is the start of an identical tree — try `isSame` at every node (or serialize both and substring-match for O(m + n)).
- **Edge Cases:** subRoot == null → true; root == null → false; matching values but extra descendants in root (must be the whole subtree).
- **Complexity:** Time O(m · n) worst case · Aux space O(h).

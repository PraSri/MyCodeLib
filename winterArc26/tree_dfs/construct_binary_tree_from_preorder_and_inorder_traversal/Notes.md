# Construct Binary Tree from Preorder and Inorder Traversal

[LeetCode](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/) · Medium · Pattern: `tree_dfs`

- **Trigger Invariant:** Preorder's next value is the current root; its position in inorder splits left/right subtrees. A value→index map plus a global preorder cursor makes each split O(1).
- **Edge Cases:** Single node; skewed trees (deep recursion); values must be unique for the index map to work.
- **Complexity:** Time O(n) · Aux space O(n) map + O(h) recursion.

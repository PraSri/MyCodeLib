# Lowest Common Ancestor of a Binary Search Tree

[LeetCode](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/) · Medium · Pattern: `bst`

- **Trigger Invariant:** BST ordering tells you where p and q live: both smaller → go left, both larger → go right; the first node where they split (or equal one) is the LCA.
- **Edge Cases:** p is an ancestor of q (answer is p); p == q; values may be negative.
- **Complexity:** Time O(h) · Aux space O(1).

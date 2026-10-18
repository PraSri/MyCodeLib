# Kth Smallest Element in a BST

[LeetCode](https://leetcode.com/problems/kth-smallest-element-in-a-bst/) · Medium · Pattern: `bst`

- **Trigger Invariant:** Inorder traversal of a BST visits values in ascending order, so the kth popped node is the answer — stop early with an explicit stack.
- **Edge Cases:** k == 1 (leftmost) / k == n (rightmost); skewed tree; k assumed valid.
- **Complexity:** Time O(h + k) · Aux space O(h).

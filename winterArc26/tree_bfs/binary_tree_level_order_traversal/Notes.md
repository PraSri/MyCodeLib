# Binary Tree Level Order Traversal

[LeetCode](https://leetcode.com/problems/binary-tree-level-order-traversal/) · Medium · Pattern: `tree_bfs`

- **Trigger Invariant:** Snapshot the queue size at the start of each round — exactly that many nodes form the current level; their children form the next.
- **Edge Cases:** Empty tree → []; single node; very unbalanced tree (one node per level).
- **Complexity:** Time O(n) · Aux space O(w) queue width.

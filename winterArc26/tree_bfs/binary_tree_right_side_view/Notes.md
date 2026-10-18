# Binary Tree Right Side View

[LeetCode](https://leetcode.com/problems/binary-tree-right-side-view/) · Medium · Pattern: `tree_bfs`

- **Trigger Invariant:** The visible node at each depth is the last node of that BFS level (or the first reached in a right-first DFS).
- **Edge Cases:** Empty tree; a left subtree deeper than the right one shows through; single node.
- **Complexity:** Time O(n) · Aux space O(w).

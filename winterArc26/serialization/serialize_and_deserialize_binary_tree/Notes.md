# Serialize and Deserialize Binary Tree

[LeetCode](https://leetcode.com/problems/serialize-and-deserialize-binary-tree/) · Hard · Pattern: `serialization`

- **Trigger Invariant:** Preorder with explicit null markers is a unique encoding: the first token is always the root, and recursion consumes exactly one subtree's tokens before returning.
- **Edge Cases:** Empty tree → "N"; negative / multi-digit values; skewed tree of depth 10^4 risks recursion depth (switch to iterative BFS if needed).
- **Complexity:** Time O(n) · Aux space O(n) string + O(h) recursion.

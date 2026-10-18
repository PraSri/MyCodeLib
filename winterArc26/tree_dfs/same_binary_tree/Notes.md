# Same Tree

[LeetCode](https://leetcode.com/problems/same-tree/) · Easy · Pattern: `tree_dfs`

- **Trigger Invariant:** Two trees are equal iff roots match and both subtree pairs are equal; null/null is the base truth, null/non-null the base failure.
- **Edge Cases:** Both empty → true; same values but different shape ([1,2] vs [1,null,2]); duplicate values.
- **Complexity:** Time O(min(n, m)) · Aux space O(h).

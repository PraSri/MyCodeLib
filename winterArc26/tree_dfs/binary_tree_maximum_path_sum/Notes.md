# Binary Tree Maximum Path Sum

[LeetCode](https://leetcode.com/problems/binary-tree-maximum-path-sum/) · Hard · Pattern: `tree_dfs`

- **Trigger Invariant:** At each node, the best path bending here = val + max(0, leftGain) + max(0, rightGain); only a single branch (val + max gain) can be returned upward. Negative gains are dropped.
- **Edge Cases:** All negative values → the max single node (init best to MIN_VALUE, not 0); single node; path need not include the root.
- **Complexity:** Time O(n) · Aux space O(h).

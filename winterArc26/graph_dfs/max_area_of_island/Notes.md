# Max Area of Island

[LeetCode](https://leetcode.com/problems/max-area-of-island/) · Medium · Pattern: `graph_dfs`

- **Trigger Invariant:** Flood fill returns the size of the component it consumes; the answer is the max over all component roots.
- **Edge Cases:** No land → 0; whole grid is land; mark visited before recursing to avoid double counting.
- **Complexity:** Time O(m · n) · Aux space O(m · n) worst-case recursion.

# Pacific Atlantic Water Flow

[LeetCode](https://leetcode.com/problems/pacific-atlantic-water-flow/) · Medium · Pattern: `graph_dfs`

- **Trigger Invariant:** Reverse the flow: start from each ocean's border and climb to cells of height ≥ current; a cell reachable from both border sets drains to both oceans. Two DFS passes instead of one per cell.
- **Edge Cases:** Single row/column (every cell touches both oceans); flat plateaus (≥, not >); corner cells belong to both borders.
- **Complexity:** Time O(m · n) · Aux space O(m · n).

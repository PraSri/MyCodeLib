# Surrounded Regions

[LeetCode](https://leetcode.com/problems/surrounded-regions/) · Medium · Pattern: `graph_dfs`

- **Trigger Invariant:** An 'O' survives iff it connects to the border; mark border-reachable 'O's as safe first, then everything else that is still 'O' must be captured.
- **Edge Cases:** Grids with 1–2 rows/cols (everything is border); no 'O's; a border 'O' chain winding deep inside.
- **Complexity:** Time O(m · n) · Aux space O(m · n) recursion.

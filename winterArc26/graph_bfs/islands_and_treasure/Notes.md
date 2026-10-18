# Walls and Gates (Islands and Treasure)

[LeetCode](https://leetcode.com/problems/walls-and-gates/) · Medium · Pattern: `graph_bfs`

- **Trigger Invariant:** Multi-source BFS from every treasure/gate at once: the first time BFS reaches a cell is via its nearest source, so each cell is written exactly once.
- **Edge Cases:** No treasure → grid unchanged; cells walled off stay INF (2147483647); only overwrite cells still equal to INF.
- **Complexity:** Time O(m · n) · Aux space O(m · n).

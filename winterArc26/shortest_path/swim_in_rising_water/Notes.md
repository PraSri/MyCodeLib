# Swim in Rising Water

[LeetCode](https://leetcode.com/problems/swim-in-rising-water/) · Hard · Pattern: `shortest_path`

- **Trigger Invariant:** Path cost = max elevation on the path (a bottleneck metric, still monotone along a path), so Dijkstra with max() instead of + pops each cell at its minimal required time.
- **Edge Cases:** 1×1 grid → grid[0][0]; start cell's own height counts; the answer is at least max(start, end).
- **Complexity:** Time O(n² log n) · Aux space O(n²).

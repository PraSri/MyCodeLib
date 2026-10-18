# Rotting Oranges

[LeetCode](https://leetcode.com/problems/rotting-oranges/) · Medium · Pattern: `graph_bfs`

- **Trigger Invariant:** Multi-source BFS: all rotten oranges are level 0; each BFS level is one minute, so the level count when the last fresh orange rots is the answer.
- **Edge Cases:** No fresh oranges → 0 (even if no rotten ones); unreachable fresh orange → −1; don't count the final empty level as a minute.
- **Complexity:** Time O(m · n) · Aux space O(m · n).

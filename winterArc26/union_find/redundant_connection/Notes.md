# Redundant Connection

[LeetCode](https://leetcode.com/problems/redundant-connection/) · Medium · Pattern: `union_find`

- **Trigger Invariant:** A tree plus one edge has exactly one cycle; processing edges in order, the first edge whose endpoints are already in the same set closes that cycle (and is the last such edge in input order).
- **Edge Cases:** Nodes are 1-indexed (size parent array n + 1); cycle involving the very first edges; use path compression + union by rank.
- **Complexity:** Time O(n · α(n)) · Aux space O(n).

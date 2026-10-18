# Number of Islands

[LeetCode](https://leetcode.com/problems/number-of-islands/) · Medium · Pattern: `graph_dfs`

- **Trigger Invariant:** Each unvisited land cell starts a new component; flood-filling it (sinking to '0') guarantees the component is counted exactly once.
- **Edge Cases:** All water → 0; one giant island (recursion depth up to m·n — use an explicit stack for huge grids); diagonal cells are NOT connected.
- **Complexity:** Time O(m · n) · Aux space O(m · n) worst-case recursion.

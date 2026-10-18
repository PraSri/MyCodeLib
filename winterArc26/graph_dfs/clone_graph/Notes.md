# Clone Graph

[LeetCode](https://leetcode.com/problems/clone-graph/) · Medium · Pattern: `graph_dfs`

- **Trigger Invariant:** A map old → clone doubles as the visited set; registering the clone BEFORE recursing into neighbours is what makes cycles terminate.
- **Edge Cases:** null input → null; single node without neighbours; self-loops and cycles.
- **Complexity:** Time O(V + E) · Aux space O(V).

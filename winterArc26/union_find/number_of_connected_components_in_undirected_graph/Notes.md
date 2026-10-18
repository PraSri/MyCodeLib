# Number of Connected Components in an Undirected Graph

[LeetCode](https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/) · Medium · Pattern: `union_find`

- **Trigger Invariant:** Start with n singleton components; every successful union merges two components, so the answer is n minus the number of successful unions.
- **Edge Cases:** No edges → n; duplicate edges / cycles (failed unions don't decrement); isolated nodes.
- **Complexity:** Time O((n + E) · α(n)) · Aux space O(n).

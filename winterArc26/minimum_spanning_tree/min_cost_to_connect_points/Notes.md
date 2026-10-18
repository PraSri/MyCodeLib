# Min Cost to Connect All Points

[LeetCode](https://leetcode.com/problems/min-cost-to-connect-all-points/) · Medium · Pattern: `minimum_spanning_tree`

- **Trigger Invariant:** Cut property: the cheapest edge leaving the current tree is always in some MST. On a dense complete graph, array-based Prim (O(n²)) beats heap/Kruskal (O(n² log n)).
- **Edge Cases:** Single point → 0; duplicate points (distance 0); Manhattan distance up to 4·10^6 fits int, total cost fits int under constraints.
- **Complexity:** Time O(n²) · Aux space O(n).

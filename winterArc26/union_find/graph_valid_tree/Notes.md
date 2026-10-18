# Graph Valid Tree

[LeetCode](https://leetcode.com/problems/graph-valid-tree/) · Medium · Pattern: `union_find`

- **Trigger Invariant:** A graph on n nodes is a tree ⇔ it has exactly n − 1 edges AND no cycle (equivalently: connected). Check the edge count first, then detect cycles with union-find.
- **Edge Cases:** n = 1 with no edges → true; n − 1 edges but a cycle + disconnected node → false; duplicate edges.
- **Complexity:** Time O(n · α(n)) · Aux space O(n).

# Course Schedule

[LeetCode](https://leetcode.com/problems/course-schedule/) · Medium · Pattern: `topological_sort`

- **Trigger Invariant:** A DAG always has a node with in-degree 0; Kahn's algorithm repeatedly removes such nodes — if some nodes are never freed, they sit on a cycle.
- **Edge Cases:** No prerequisites → true; self-dependency [a, a] → false; disconnected components.
- **Complexity:** Time O(V + E) · Aux space O(V + E).

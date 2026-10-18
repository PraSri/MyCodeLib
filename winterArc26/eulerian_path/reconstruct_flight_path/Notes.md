# Reconstruct Itinerary

[LeetCode](https://leetcode.com/problems/reconstruct-itinerary/) · Hard · Pattern: `eulerian_path`

- **Trigger Invariant:** Using every ticket once = Eulerian path. Hierholzer: greedily take the lexicographically smallest unused edge; when a node is stuck, it's the end of the remaining path, so prepend it (post-order).
- **Edge Cases:** Dead-end branch chosen first lexicographically (post-order fixes it); duplicate tickets (PriorityQueue keeps multiplicity); airports with no outgoing flights.
- **Complexity:** Time O(E log E) · Aux space O(E).

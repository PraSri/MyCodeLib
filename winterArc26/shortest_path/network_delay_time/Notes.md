# Network Delay Time

[LeetCode](https://leetcode.com/problems/network-delay-time/) · Medium · Pattern: `shortest_path`

- **Trigger Invariant:** Non-negative weights ⇒ Dijkstra: the first time a node is popped from the min-heap its distance is final. Answer = max final distance, or −1 if some node is unreachable.
- **Edge Cases:** Unreachable node → −1; single node → 0; skip stale heap entries (d > dist[u]); nodes are 1-indexed.
- **Complexity:** Time O(E log V) · Aux space O(V + E).

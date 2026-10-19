# Cheapest Flights Within K Stops — Medium

### 1. Invariant Trigger
* **Signal:** Shortest path on weighted graph with a hard constraint on the number of edges ($k$ stops = at most $k + 1$ edges) -> Bellman-Ford / Level BFS.
* **Core Invariant:** Iteration $i$ computes the exact minimum cost to reach any node using at most $i$ flights. Using a snapshot `temp` array prevents cascading edge relaxations within the same step.

### 2. Failure Traps & Edge Cases
* Cascading updates: Updating the distance array in-place allows $k$ flights to traverse $> k$ edges in a single iteration. Always relax from the previous round's snapshot.
* Disconnected target: If `dist[dst]` remains `Integer.MAX_VALUE`, return $-1$.
* Cycle with zero/positive weight: Standard Dijkstra without stop-state pruning fails because a cheaper path with more stops might starve an expensive path with fewer stops.

### 3. Complexity & Variations
* **Time:** $O((k + 1) \cdot E)$ where $E = \text{flights.length}$.
* **Space:** $O(V)$ auxiliary space for `dist` and `temp` arrays.
* **Google Follow-up:** "What if $k$ is large and $V$ is small?" -> Dijkstra tracking 2D state `dist[node][stops]`, pruned by `stops <= k + 1`.

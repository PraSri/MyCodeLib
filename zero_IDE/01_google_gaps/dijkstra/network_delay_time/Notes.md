# Network Delay Time — Medium

### 1. Invariant Trigger
* **Signal:** Directed weighted graph with non-negative delays, finding time for a signal from source $k$ to reach all nodes -> Single-source shortest path (Dijkstra).
* **Core Invariant:** Greedy frontier selection: the unvisited node with minimum tentative distance is settled. Discard polled priority queue entries where `currDist > dist[u]`.

### 2. Failure Traps & Edge Cases
* 1-based indexing: Graph nodes are labeled $1$ to $n$. Off-by-one in distance array sizing results in `ArrayIndexOutOfBoundsException`.
* Unreachable nodes: If any node $1 \le i \le n$ retains $\infty$, return $-1$.
* Stale entries: Without the `d > dist[u]` guard, duplicate queue insertions cause $O(E \log V)$ to degrade in practical execution.

### 3. Complexity & Variations
* **Time:** $O((V + E) \log V)$ using binary heap PriorityQueue.
* **Space:** $O(V + E)$ for adjacency list and distance array.
* **Google Follow-up:** "How would you handle dynamic latency updates occurring during signal transmission?" -> Dynamic Dijkstra or Incremental Bellman-Ford / Contraction Hierarchies.

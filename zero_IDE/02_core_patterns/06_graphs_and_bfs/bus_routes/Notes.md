# Bus Routes — Hard

### 1. Invariant Trigger
* **Signal:** Minimum bus transfers to travel between two stops (Uber R2 problem) -> BFS where graph nodes are BUS ROUTES rather than individual stops.
* **Core Invariant:** Treating each bus route as a graph node reduces graph size from $10^5$ stops to $\le 500$ routes. Level-by-level BFS guarantees the minimum number of buses taken.

### 2. Failure Traps & Edge Cases
* Source equals target: `source == target` requires 0 buses (return 0 immediately).
* Unreachable stop: source or target not present in any bus route (return -1).
* Two-level visited tracking: Must track BOTH `visitedRoutes` and `visitedStops` to prevent exponential re-traversal of large loops.

### 3. Complexity & Variations
* **Time:** $O(\sum \text{len}(routes))$ each stop and route is enqueued at most once.
* **Space:** $O(\sum \text{len}(routes))$ for `stopToRoutes` mapping and BFS queues.
* **Google Follow-up:** "What if each bus transfer incurs a specific waiting penalty?" -> Use Dijkstra / 0-1 BFS over the bus-route dual graph.

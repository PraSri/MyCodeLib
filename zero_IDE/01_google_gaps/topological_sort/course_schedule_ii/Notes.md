# Course Schedule II — Medium

### 1. Invariant Trigger
* **Signal:** Directed dependency graph where tasks must be executed only after prerequisites are satisfied -> Kahn's Topological Sort.
* **Core Invariant:** Vertices with in-degree == 0 have all prerequisites met. Processing a node frees incoming edges to dependents. If processed count < total courses, a directed cycle exists.

### 2. Failure Traps & Edge Cases
* Prerequisites edge orientation: `[course, prereq]` means directed edge is `prereq -> course`. Inverting this corrupts the sequence.
* Self-dependencies or multi-node cycles: cycle detection must return `new int[0]` if `index != numCourses`.
* Boundary: Check $numCourses = 1$ with no prerequisites (should return `[0]`). Disconnected DAG components must all be included.

### 3. Complexity & Variations
* **Time:** $O(V + E)$ where $V = \text{numCourses}$ and $E = \text{prerequisites.length}$.
* **Space:** $O(V + E)$ for adjacency list, in-degree array, and BFS queue.
* **Google Follow-up:** "What if multiple valid topological orders exist and we want the lexicographically smallest order?" -> Replace `ArrayDeque` with `PriorityQueue<Integer>`.

# Most Profitable Path in a Tree — Medium

### 1. Invariant Trigger
* **Signal:** Tree game where Bob moves towards root 0 and Alice moves from root 0 towards leaves (Nutanix problem) -> Two-pass Tree DFS (Path Reconstruction + Tree Traversal).
* **Core Invariant:** Trees have exactly ONE simple path between any two vertices. Bob's trajectory to root 0 is deterministic. Once Bob's arrival time at each node is fixed, Alice's optimal route is a standard max-path-to-leaf DFS.

### 2. Failure Traps & Edge Cases
* Split reward condition: When Alice and Bob reach node $u$ at the EXACT same time (`time == bobTime[u]`), reward is split evenly (`amount[u] / 2`).
* Leaf node detection: In an undirected tree with parent tracking, node $u \neq 0$ is a leaf if and only if its only neighbor is `parent`.
* Negative total profits: `maxProfit` must be initialized to `Integer.MIN_VALUE`, not 0.

### 3. Complexity & Variations
* **Time:** $O(N)$ two linear passes over tree of $N$ nodes.
* **Space:** $O(N)$ for adjacency list, recursion stack, and `bobTime` array.
* **Google Follow-up:** "What if Bob's destination is an arbitrary node rather than root 0?" -> Use Lowest Common Ancestor (LCA) to compute tree distances in $O(1)$.

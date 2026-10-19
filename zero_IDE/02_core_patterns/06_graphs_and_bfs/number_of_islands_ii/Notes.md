# Number of Islands II — Hard

### 1. Invariant Trigger
* **Signal:** Dynamic graph where land cells are added one-by-one and connected component count must be queried after each addition (Uber R1 problem) -> Online Union-Find (DSU).
* **Core Invariant:** Flatten 2D grid index via $r \cdot n + c$. When a new land is added, increase component count by 1. For each of its 4 neighbors that is already land, union them and decrement component count.

### 2. Failure Traps & Edge Cases
* Duplicate add positions: The query stream can contain identical coordinates; adding existing land must not increment island count or corrupt parent pointers.
* 2D-to-1D mapping: $idx = r \cdot n + c$; ensure columns $n$ is used, not rows $m$.
* Empty positions or out of bounds coordinates.

### 3. Complexity & Variations
* **Time:** $O(K \cdot \alpha(M \cdot N))$ where $K = \text{positions.length}$.
* **Space:** $O(M \cdot N)$ parent array.
* **Google Follow-up:** "What if grid dimensions $M, N$ are up to $10^9$ and $K \le 10^5$?" -> Use `HashMap<Long, Long>` for sparse DSU with key $(long) r \ll 32 | c$.

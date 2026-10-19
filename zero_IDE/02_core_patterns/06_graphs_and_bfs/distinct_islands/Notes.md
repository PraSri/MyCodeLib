# Number of Distinct Islands — Medium

### 1. Invariant Trigger
* **Signal:** Count unique island shapes under translation invariance (Grey Orange bar raiser) -> DFS with traversal path signatures.
* **Core Invariant:** A shape is defined by its step-by-step exploration path. Crucially, the backtracking action `'B'` must be recorded to distinguish different branching tree topologies that share identical forward steps.

### 2. Failure Traps & Edge Cases
* Missing backtrack token: Without appending `'B'` upon return, different shapes can produce identical signatures (e.g., right then down vs down then right).
* Grid mutation: Sinking islands `grid[r][c] = 0` avoids a duplicate boolean matrix.
* No islands present: Returns 0.

### 3. Complexity & Variations
* **Time:** $O(M \cdot N)$ visiting each cell constant number of times.
* **Space:** $O(M \cdot N)$ for recursion stack and unique shape string set.
* **Google Follow-up:** "What if shapes are considered identical under 90-degree rotations and reflections?" -> Number of Distinct Islands II: normalize coordinates across all 8 dihedral transformations.

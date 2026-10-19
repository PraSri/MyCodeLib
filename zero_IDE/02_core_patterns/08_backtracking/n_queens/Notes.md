# N-Queens — Hard

### 1. Invariant Trigger
* **Signal:** Place $N$ non-attacking queens on an $N \times N$ chessboard -> Row-by-row backtracking with $O(1)$ attack lookups.
* **Core Invariant:** Placing exactly one queen per row guarantees row safety. Column collision is checked by $cols[c]$. Main diagonals have constant $r - c$ (offset by $+n$); anti-diagonals have constant $r + c$.

### 2. Failure Traps & Edge Cases
* Negative array index in diagonal lookup: $r - c$ ranges from $-(n - 1)$ to $n - 1$. Must add offset $+n$ to index into boolean array.
* $N = 2$ and $N = 3$: No valid solutions exist; returns empty list.
* Bitmask optimization: Use integer bitmasks for `cols`, `diag1`, `diag2` for maximum speed.

### 3. Complexity & Variations
* **Time:** $O(N!)$ upper bound since each row choice reduces subsequent valid columns.
* **Space:** $O(N)$ for recursion call stack and safety tracking arrays.
* **Google Follow-up:** "How to count total solutions for $N = 16$ in milliseconds?" -> Use bitwise operations: `cols | diag1 | diag2` with lowest bit extraction `bits & (-bits)`.

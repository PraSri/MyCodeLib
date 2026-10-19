# Koko Eating Bananas — Medium

### 1. Invariant Trigger
* **Signal:** Find minimum speed $k$ to consume all items within $h$ hours; monotonicity: higher speed always takes fewer or equal hours -> Binary Search on Answer.
* **Core Invariant:** Monotonic predicate $canFinish(speed) \to \{F, F, \dots, T, T\}$. Lower bound template finds the first speed where condition evaluates to true.

### 2. Failure Traps & Edge Cases
* 32-bit Integer overflow in total hours: Summing hours across large piles can exceed `Integer.MAX_VALUE`. Must accumulate `totalHours` in `long`.
* Ceiling division formula: Use `(pile + speed - 1) / speed` instead of floating point `Math.ceil()` which loses precision for large integers.
* Search space boundaries: Minimum possible speed is 1 (not 0); maximum speed is $\max(piles)$.

### 3. Complexity & Variations
* **Time:** $O(N \log(\max(piles)))$ where $N = \text{piles.length}$.
* **Space:** $O(1)$ constant auxiliary memory.
* **Google Follow-up:** "What if piles are distributed across external workers?" -> Distributed reduction to compute hours needed per candidate speed.

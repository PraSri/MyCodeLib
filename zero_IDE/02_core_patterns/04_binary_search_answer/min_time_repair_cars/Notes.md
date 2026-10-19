# Minimum Time to Repair Cars — Medium

### 1. Invariant Trigger
* **Signal:** Mechanic with rank $r$ repairs $n$ cars in $r \cdot n^2$ minutes. Find minimum time for all mechanics working simultaneously to repair $C$ cars (Blinkit problem) -> Binary Search on Answer.
* **Core Invariant:** In given time $t$, mechanic $i$ repairs $\lfloor \sqrt{t / r_i} \rfloor$ cars. Monotonic non-decreasing total cars repaired $\sum \lfloor \sqrt{t / r_i} \rfloor$.

### 2. Failure Traps & Edge Cases
* 64-bit Long overflow in upper bound: $hi = \min(rank) \times cars^2$. When $cars = 10^6$ and $r = 1$, $hi = 10^{12}$, which overflows 32-bit int. All search variables must be `long`.
* Early termination in predicate: As soon as `totalCars >= cars`, return `true` to avoid unnecessary computations.
* Boundary: $cars = 0$ or single mechanic.

### 3. Complexity & Variations
* **Time:** $O(N \log(\min(ranks) \cdot \text{cars}^2))$.
* **Space:** $O(1)$ constant auxiliary memory.
* **Google Follow-up:** "If mechanic ranks are small integers $\le 100$, how to optimize?" -> Frequency array of ranks to reduce predicate loop from $N$ to at most 100 iterations.

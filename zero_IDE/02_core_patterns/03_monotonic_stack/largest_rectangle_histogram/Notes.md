# Largest Rectangle in Histogram — Hard

### 1. Invariant Trigger
* **Signal:** Find maximum rectangular area under histogram bars -> Monotonic strictly increasing stack storing bar indices.
* **Core Invariant:** A bar popped from the stack is the minimum height of a rectangle bounded on the right by current index $i$ and bounded on the left by the new stack top. Width is $i - \text{stack.peek}() - 1$.

### 2. Failure Traps & Edge Cases
* Empty stack width calculation: When stack is empty after pop, the popped bar was the smallest seen so far from index 0 to $i - 1$; width is $i$, NOT $i - 1$.
* Sentinel bar at $i = n$: Adding a virtual bar of height 0 at $i = n$ flushes all remaining bars from the stack without writing duplicate clean-up code.
* All bars identical: stack pops and recalculates accurately.

### 3. Complexity & Variations
* **Time:** $O(N)$ each index is pushed and popped exactly once.
* **Space:** $O(N)$ for stack in worst case (monotonically increasing heights).
* **Google Follow-up:** "How to apply this to Maximal Rectangle in a binary 2D matrix?" -> Maintain running column heights per row and call `largestRectangleArea` on each row in $O(R \times C)$.

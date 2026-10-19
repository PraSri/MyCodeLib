# Partition Equal Subset Sum — Medium

### 1. Invariant Trigger
* **Signal:** Partition set of positive integers into two subsets with equal sum -> 0/1 Knapsack decision problem with target = $\text{sum} / 2$.
* **Core Invariant:** Can a subset of `nums` sum up to exactly `target`? Subproblem $dp[j]$ is reachable if $dp[j - num]$ was reachable using earlier numbers.

### 2. Failure Traps & Edge Cases
* Forward traversal trap: Traversing `j` forwards ($num \to target$) allows reusing the same number multiple times (Unbounded Knapsack). MUST traverse in reverse ($target \to num$).
* Odd sum check: If total sum is odd, immediately return `false`.
* Element exceeds target: If any single element `num > target`, it can never be balanced (handled naturally by loop condition).

### 3. Complexity & Variations
* **Time:** $O(N \times \text{target})$ pseudo-polynomial time where $\text{target} = \frac{\sum nums}{2}$.
* **Space:** $O(\text{target})$ using 1D space optimization.
* **Google Follow-up:** "How to optimize this if target is up to $10^5$?" -> Use `BitSet` or Java `BigInteger` with bit shifts: `bits |= (bits << num)`.

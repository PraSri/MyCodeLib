# Coin Change II — Medium

### 1. Invariant Trigger
* **Signal:** Count number of combinations that make up a given amount with unlimited supply of each coin -> Unbounded Knapsack (Combinations).
* **Core Invariant:** Iterating coins in the OUTER loop and amount in the INNER loop enforces non-decreasing coin indices, generating combinations rather than permutations.

### 2. Failure Traps & Edge Cases
* Permutations vs Combinations trap: Reversing the loop order (amount outer, coins inner) computes permutations ($[1, 2]$ and $[2, 1]$ counted separately).
* Base case: `dp[0] = 1` represents the empty set combination.
* Unreachable amount: if no combination exists, `dp[amount]` remains 0.

### 3. Complexity & Variations
* **Time:** $O(N \times \text{amount})$ where $N = \text{coins.length}$.
* **Space:** $O(\text{amount})$ 1D compressed dynamic programming array.
* **Google Follow-up:** "What if answer exceeds 32-bit signed integer limits?" -> Use `BigInteger` or modular arithmetic depending on specification.

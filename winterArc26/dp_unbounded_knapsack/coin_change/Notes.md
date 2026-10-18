# Coin Change

[LeetCode](https://leetcode.com/problems/coin-change/) · Medium · Pattern: `dp_unbounded_knapsack`

- **Trigger Invariant:** Unlimited coins ⇒ unbounded knapsack: dp[a] = 1 + min over coins of dp[a − c]; iterate amounts upward so a coin can be reused.
- **Edge Cases:** amount = 0 → 0; impossible → −1 (use amount+1 as ∞, never MAX_VALUE + 1 overflow); coin larger than amount.
- **Complexity:** Time O(amount · coins) · Aux space O(amount).

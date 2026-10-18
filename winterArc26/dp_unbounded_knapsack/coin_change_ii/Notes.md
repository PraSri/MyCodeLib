# Coin Change II

[LeetCode](https://leetcode.com/problems/coin-change-ii/) · Medium · Pattern: `dp_unbounded_knapsack`

- **Trigger Invariant:** Counting COMBINATIONS: put coins in the outer loop so each combination is built in a fixed coin order (amount-outer would count permutations).
- **Edge Cases:** amount = 0 → 1 (empty combination); no coins fit → 0; answer guaranteed to fit int.
- **Complexity:** Time O(amount · coins) · Aux space O(amount).

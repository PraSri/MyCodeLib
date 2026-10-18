# Min Cost Climbing Stairs

[LeetCode](https://leetcode.com/problems/min-cost-climbing-stairs/) · Easy · Pattern: `dp_linear`

- **Trigger Invariant:** dp[i] = cost to stand on step i = cost[i] + min(dp[i−1], dp[i−2]); the top (index n) is reached from either of the last two steps.
- **Edge Cases:** Two steps → min of them; you may start at index 0 or 1 for free; zero-cost steps.
- **Complexity:** Time O(n) · Aux space O(1).

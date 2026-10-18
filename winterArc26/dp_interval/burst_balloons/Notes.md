# Burst Balloons

[LeetCode](https://leetcode.com/problems/burst-balloons/) · Hard · Pattern: `dp_interval`

- **Trigger Invariant:** Think of the LAST balloon k burst in (l, r): its neighbours are then exactly l and r, so dp[l][r] = max over k of dp[l][k] + vals[l]·vals[k]·vals[r] + dp[k][r] — subproblems become independent.
- **Edge Cases:** Single balloon; pad with virtual 1s at both ends; zeros in nums (bursting them early is free).
- **Complexity:** Time O(n³) · Aux space O(n²).

# Best Time to Buy and Sell Stock with Cooldown

[LeetCode](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/) · Medium · Pattern: `dp_state_machine`

- **Trigger Invariant:** Each day you are in one of three states — holding, just sold (cooldown next), resting; transitions only depend on yesterday's states, so three variables carry the whole DP.
- **Edge Cases:** One day → 0; strictly decreasing → 0; buying is only allowed from 'rest', never directly after 'sold'.
- **Complexity:** Time O(n) · Aux space O(1).

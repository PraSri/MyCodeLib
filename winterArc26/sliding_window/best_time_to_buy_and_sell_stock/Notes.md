# Best Time to Buy and Sell Stock

[LeetCode](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/) · Easy · Pattern: `sliding_window`

- **Trigger Invariant:** The best buy for a sell on day r is the minimum price in [0, r]; a lower price makes every older left boundary obsolete, so the window's left jumps to it.
- **Edge Cases:** Single day → 0; strictly decreasing prices → 0 (never negative profit).
- **Complexity:** Time O(n) · Aux space O(1).

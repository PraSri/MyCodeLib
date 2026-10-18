# House Robber

[LeetCode](https://leetcode.com/problems/house-robber/) · Medium · Pattern: `dp_linear`

- **Trigger Invariant:** At house i you either skip it (best up to i−1) or rob it (nums[i] + best up to i−2); adjacency only reaches back two states.
- **Edge Cases:** Single house; two houses → max; all zeros; optimal may skip two in a row.
- **Complexity:** Time O(n) · Aux space O(1).

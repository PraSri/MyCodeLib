# Gas Station

[LeetCode](https://leetcode.com/problems/gas-station/) · Medium · Pattern: `greedy`

- **Trigger Invariant:** If total gas ≥ total cost a solution exists; if the tank goes negative at i, no station in [start, i] can be the start, so jump start to i + 1.
- **Edge Cases:** Total gas < total cost → −1; single station; start at index 0 is valid.
- **Complexity:** Time O(n) · Aux space O(1).

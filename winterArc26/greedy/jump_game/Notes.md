# Jump Game

[LeetCode](https://leetcode.com/problems/jump-game/) · Medium · Pattern: `greedy`

- **Trigger Invariant:** Track the farthest reachable index; every index ≤ reach is reachable, so you fail only when i passes reach.
- **Edge Cases:** Single element → true; zeros inside the array; nums[0] = 0 with n > 1 → false.
- **Complexity:** Time O(n) · Aux space O(1).

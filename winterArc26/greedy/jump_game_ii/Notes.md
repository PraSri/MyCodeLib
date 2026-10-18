# Jump Game II

[LeetCode](https://leetcode.com/problems/jump-game-ii/) · Medium · Pattern: `greedy`

- **Trigger Invariant:** Implicit BFS: indices reachable with j jumps form a contiguous window; the next window ends at the farthest reach from the current one.
- **Edge Cases:** Single element → 0; don't jump from the last index (loop to n − 2); large jumps that overshoot the end.
- **Complexity:** Time O(n) · Aux space O(1).

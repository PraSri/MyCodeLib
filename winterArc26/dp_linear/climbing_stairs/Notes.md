# Climbing Stairs

[LeetCode](https://leetcode.com/problems/climbing-stairs/) · Easy · Pattern: `dp_linear`

- **Trigger Invariant:** The last move is a 1-step or a 2-step, so ways(n) = ways(n−1) + ways(n−2) — Fibonacci; only the previous two states are ever needed.
- **Edge Cases:** n = 1 → 1; n = 2 → 2; n ≥ 46 overflows int (constraint is n ≤ 45).
- **Complexity:** Time O(n) · Aux space O(1).

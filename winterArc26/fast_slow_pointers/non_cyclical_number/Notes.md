# Happy Number

[LeetCode](https://leetcode.com/problems/happy-number/) · Easy · Pattern: `fast_slow_pointers`

- **Trigger Invariant:** n → sum of squared digits is a function on a bounded set (≤ 730 after one step for any int), so the sequence must enter a cycle; it's happy iff that cycle is the fixed point 1.
- **Edge Cases:** n = 1 → true; n = Integer.MAX_VALUE (digit-square sum ≤ 730, no overflow); the classic unhappy loop 4 → 16 → … → 4.
- **Complexity:** Time O(log n) · Aux space O(1).

# Single Number

[LeetCode](https://leetcode.com/problems/single-number/) · Easy · Pattern: `bit_manipulation`

- **Trigger Invariant:** XOR is associative, commutative and self-inverse (a ^ a = 0, a ^ 0 = a), so every pair cancels and only the single element survives.
- **Edge Cases:** Single-element array; negative numbers (XOR works on two's complement bits); zero as the answer.
- **Complexity:** Time O(n) · Aux space O(1).

# Reverse Bits

[LeetCode](https://leetcode.com/problems/reverse-bits/) · Easy · Pattern: `bit_manipulation`

- **Trigger Invariant:** Shift the result left and append n's lowest bit, 32 times; unsigned shift (>>>) on n so the sign bit doesn't smear.
- **Edge Cases:** Input treated as unsigned (results may be negative ints); all zeros / all ones; must loop exactly 32 times even after n becomes 0.
- **Complexity:** Time O(32) = O(1) · Aux space O(1).

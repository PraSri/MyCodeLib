# Sum of Two Integers

[LeetCode](https://leetcode.com/problems/sum-of-two-integers/) · Medium · Pattern: `bit_manipulation`

- **Trigger Invariant:** a ^ b is the sum without carries and (a & b) << 1 is the carry; repeat until no carry remains — the loop ends within 32 iterations since the carry shifts left each time.
- **Edge Cases:** Negative operands (two's complement handles them); a or b = 0; overflow wraps exactly like + does in Java.
- **Complexity:** Time O(32) = O(1) · Aux space O(1).

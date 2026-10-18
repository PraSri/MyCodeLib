# Number of 1 Bits

[LeetCode](https://leetcode.com/problems/number-of-1-bits/) · Easy · Pattern: `bit_manipulation`

- **Trigger Invariant:** n & (n − 1) clears the lowest set bit, so the loop runs once per set bit (Kernighan).
- **Edge Cases:** n = 0 → 0; treat input as unsigned: negative ints (e.g. −1 = 32 ones) — loop on n != 0, never n > 0.
- **Complexity:** Time O(set bits) ≤ O(32) · Aux space O(1).

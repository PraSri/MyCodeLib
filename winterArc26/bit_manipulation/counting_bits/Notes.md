# Counting Bits

[LeetCode](https://leetcode.com/problems/counting-bits/) · Easy · Pattern: `bit_manipulation`

- **Trigger Invariant:** bits(i) = bits(i >> 1) + (i & 1): dropping the lowest bit gives a smaller, already-computed number.
- **Edge Cases:** n = 0 → [0]; powers of two have exactly one bit; output length is n + 1.
- **Complexity:** Time O(n) · Aux space O(1) besides the output.

# Pow(x, n)

[LeetCode](https://leetcode.com/problems/powx-n/) · Medium · Pattern: `math`

- **Trigger Invariant:** Binary exponentiation: x^n = (x²)^(n/2) · (x if n odd); scanning n's bits squares the base each step, so only log n multiplications.
- **Edge Cases:** n = Integer.MIN_VALUE (−n overflows int — widen to long first); n = 0 → 1; x = 0 with negative n; negative x.
- **Complexity:** Time O(log n) · Aux space O(1).

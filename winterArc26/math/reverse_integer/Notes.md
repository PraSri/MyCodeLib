# Reverse Integer

[LeetCode](https://leetcode.com/problems/reverse-integer/) · Medium · Pattern: `math`

- **Trigger Invariant:** Pop the last digit and push it onto the result; overflow must be detected BEFORE the multiply (result > MAX/10 or == MAX/10 with digit > 7, symmetric for MIN).
- **Edge Cases:** Negative numbers (Java % keeps the sign, so no special case); trailing zeros (120 → 21); 1534236469 → 0; Integer.MIN_VALUE.
- **Complexity:** Time O(log₁₀ n) · Aux space O(1).

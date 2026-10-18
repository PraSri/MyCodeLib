# Multiply Strings

[LeetCode](https://leetcode.com/problems/multiply-strings/) · Medium · Pattern: `math`

- **Trigger Invariant:** Digit i of num1 times digit j of num2 contributes to positions i + j (carry) and i + j + 1 (units) of the product array — schoolbook multiplication without big-integer conversion.
- **Edge Cases:** Either operand "0" → "0"; strip leading zeros of the product; lengths up to 200 (never parse to long).
- **Complexity:** Time O(m · n) · Aux space O(m + n).

# Missing Number

[LeetCode](https://leetcode.com/problems/missing-number/) · Easy · Pattern: `bit_manipulation`

- **Trigger Invariant:** XOR all indices 0..n with all values; every present number cancels with its index, leaving the missing one (sum formula works too but can overflow for big n).
- **Edge Cases:** Missing 0; missing n; single element [0] → 1.
- **Complexity:** Time O(n) · Aux space O(1).

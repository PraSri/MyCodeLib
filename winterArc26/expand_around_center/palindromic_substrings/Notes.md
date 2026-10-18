# Palindromic Substrings

[LeetCode](https://leetcode.com/problems/palindromic-substrings/) · Medium · Pattern: `expand_around_center`

- **Trigger Invariant:** Each successful expansion step from one of the 2n − 1 centers is exactly one distinct palindromic substring (by position), so just count expansions.
- **Edge Cases:** Single char → 1; all same chars → n(n+1)/2; substrings counted by position, not by value.
- **Complexity:** Time O(n²) · Aux space O(1).

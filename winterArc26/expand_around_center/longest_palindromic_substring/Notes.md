# Longest Palindromic Substring

[LeetCode](https://leetcode.com/problems/longest-palindromic-substring/) · Medium · Pattern: `expand_around_center`

- **Trigger Invariant:** Every palindrome mirrors around a center — one of 2n − 1 (chars and gaps); expanding while ends match finds the longest palindrome per center in O(n) without a DP table.
- **Edge Cases:** Single char; whole string is a palindrome; even-length palindromes (gap centers) like "abba".
- **Complexity:** Time O(n²) · Aux space O(1).

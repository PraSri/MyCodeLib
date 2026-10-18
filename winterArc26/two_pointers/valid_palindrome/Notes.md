# Valid Palindrome

[LeetCode](https://leetcode.com/problems/valid-palindrome/) · Easy · Pattern: `two_pointers`

- **Trigger Invariant:** Palindrome is a symmetric check: converge from both ends, skipping non-alphanumerics, comparing case-insensitively; any mismatch is final.
- **Edge Cases:** Empty string or only punctuation → true; digits count ('0P' → false); single char → true.
- **Complexity:** Time O(n) · Aux space O(1).

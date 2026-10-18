# Regular Expression Matching

[LeetCode](https://leetcode.com/problems/regular-expression-matching/) · Hard · Pattern: `dp_two_sequences`

- **Trigger Invariant:** dp[i][j] = s[i..] matches p[j..]; a 'x*' pair either matches zero times (skip 2 in p) or, if s[i] matches x, consumes one char of s and stays on the same pattern.
- **Edge Cases:** Empty s with pattern like "a*b*" → true; ".*" matches everything; '*' never appears first; match must cover the WHOLE string.
- **Complexity:** Time O(m · n) · Aux space O(m · n).

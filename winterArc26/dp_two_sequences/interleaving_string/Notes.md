# Interleaving String

[LeetCode](https://leetcode.com/problems/interleaving-string/) · Medium · Pattern: `dp_two_sequences`

- **Trigger Invariant:** dp[i][j] = s3[0..i+j) is an interleaving of s1[0..i) and s2[0..j); the last char of s3 must come from s1 or s2 — one 1D row suffices.
- **Edge Cases:** |s1| + |s2| ≠ |s3| → false; empty strings; both s1 and s2 offering the same next char (DP explores both).
- **Complexity:** Time O(m · n) · Aux space O(n).

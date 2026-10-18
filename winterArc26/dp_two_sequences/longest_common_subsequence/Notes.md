# Longest Common Subsequence

[LeetCode](https://leetcode.com/problems/longest-common-subsequence/) · Medium · Pattern: `dp_two_sequences`

- **Trigger Invariant:** Compare last chars: equal ⇒ 1 + lcs(i−1, j−1); otherwise one of them is not in the LCS ⇒ max(lcs(i−1, j), lcs(i, j−1)).
- **Edge Cases:** One string empty → 0; identical strings → length; no common chars → 0.
- **Complexity:** Time O(m · n) · Aux space O(m · n) (rollable to O(min(m, n))).

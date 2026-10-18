# Word Break

[LeetCode](https://leetcode.com/problems/word-break/) · Medium · Pattern: `dp_linear`

- **Trigger Invariant:** dp[i] = prefix s[0..i) is segmentable ⇔ some j < i has dp[j] and s[j..i) in the dictionary; only look back as far as the longest word.
- **Edge Cases:** Words reused many times; dictionary words longer than s; s with no valid split → false.
- **Complexity:** Time O(n · L · L) (substring hashing) · Aux space O(n + dict).

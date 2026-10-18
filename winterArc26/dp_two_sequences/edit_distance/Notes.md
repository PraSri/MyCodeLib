# Edit Distance

[LeetCode](https://leetcode.com/problems/edit-distance/) · Medium · Pattern: `dp_two_sequences`

- **Trigger Invariant:** dp[i][j] = edits to turn word1[0..i) into word2[0..j); last chars equal ⇒ free, else 1 + min(insert dp[i][j−1], delete dp[i−1][j], replace dp[i−1][j−1]).
- **Edge Cases:** Either word empty → length of the other (base row/column); identical words → 0.
- **Complexity:** Time O(m · n) · Aux space O(m · n).

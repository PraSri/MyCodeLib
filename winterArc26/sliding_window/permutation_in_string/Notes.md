# Permutation in String

[LeetCode](https://leetcode.com/problems/permutation-in-string/) · Medium · Pattern: `sliding_window`

- **Trigger Invariant:** A permutation has a fixed length |s1|, so slide a fixed-size window and keep the count of letters whose frequencies already match; 26 matches ⇒ found.
- **Edge Cases:** |s1| > |s2| → false; window check must also run after the last slide; repeated letters in s1.
- **Complexity:** Time O(|s1| + |s2|) · Aux space O(26).

# Valid Anagram

[LeetCode](https://leetcode.com/problems/valid-anagram/) · Easy · Pattern: `hashing`

- **Trigger Invariant:** Anagram ⇔ identical character multisets; a single signed counter array (+s, −t) must end all-zero.
- **Edge Cases:** Different lengths → false immediately; both empty → true; Unicode input would need a HashMap instead of int[26].
- **Complexity:** Time O(n) · Aux space O(1) (26 counters).

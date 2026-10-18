# Longest Repeating Character Replacement

[LeetCode](https://leetcode.com/problems/longest-repeating-character-replacement/) · Medium · Pattern: `sliding_window`

- **Trigger Invariant:** Window is valid while (len − maxFreq) ≤ k; maxFreq never needs to decrease because the answer only improves when a larger maxFreq appears, so the window just slides instead of shrinking.
- **Edge Cases:** k = 0 (longest run); k ≥ n → n; single char string.
- **Complexity:** Time O(n) · Aux space O(26).

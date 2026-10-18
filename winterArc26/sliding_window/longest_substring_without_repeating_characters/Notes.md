# Longest Substring Without Repeating Characters

[LeetCode](https://leetcode.com/problems/longest-substring-without-repeating-characters/) · Medium · Pattern: `sliding_window`

- **Trigger Invariant:** If [l, r] has no repeats, every sub-window does too (monotone validity); on a repeat jump l past the previous occurrence — never move l backwards.
- **Edge Cases:** Empty string → 0; all same char → 1; spaces/symbols (use int[128] or map, not int[26]); stale last-index left of l.
- **Complexity:** Time O(n) · Aux space O(Σ) = O(128).

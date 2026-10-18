# Minimum Window Substring

[LeetCode](https://leetcode.com/problems/minimum-window-substring/) · Hard · Pattern: `sliding_window`

- **Trigger Invariant:** Expand r until the window covers t (missing == 0), then shrink l while it still covers; covering is monotone in window growth, so each pointer moves forward only.
- **Edge Cases:** t longer than s or empty → ""; duplicate chars in t (counts, not sets); mixed case (use int[128]).
- **Complexity:** Time O(|s| + |t|) · Aux space O(128).

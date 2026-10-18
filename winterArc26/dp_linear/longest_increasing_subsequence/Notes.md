# Longest Increasing Subsequence

[LeetCode](https://leetcode.com/problems/longest-increasing-subsequence/) · Medium · Pattern: `dp_linear`

- **Trigger Invariant:** tails[k] = smallest possible tail of an increasing subsequence of length k+1; tails stays sorted, so each element binary-searches its slot (patience sorting).
- **Edge Cases:** Strictly increasing required (lower bound, so duplicates replace, not extend); all equal → 1; single element.
- **Complexity:** Time O(n log n) · Aux space O(n).

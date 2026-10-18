# Longest Consecutive Sequence

[LeetCode](https://leetcode.com/problems/longest-consecutive-sequence/) · Medium · Pattern: `hashing`

- **Trigger Invariant:** Only start counting from a sequence head (x−1 not in set); every element is then walked at most twice, giving O(n) without sorting.
- **Edge Cases:** Empty array → 0; duplicates (set dedups); x = Integer.MAX_VALUE where x+1 overflows to MIN_VALUE (guard or use long).
- **Complexity:** Time O(n) · Aux space O(n).

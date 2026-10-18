# Median of Two Sorted Arrays

[LeetCode](https://leetcode.com/problems/median-of-two-sorted-arrays/) · Hard · Pattern: `binary_search`

- **Trigger Invariant:** Binary-search the cut i in the shorter array (j = half − i in the other); the cut is correct iff aLeft ≤ bRight and bLeft ≤ aRight, and the violation direction tells which way to move.
- **Edge Cases:** One array empty; total length odd vs even; sentinels ±∞ at cut boundaries; average via double to avoid int overflow.
- **Complexity:** Time O(log min(m, n)) · Aux space O(1).

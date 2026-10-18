# Merge Intervals

[LeetCode](https://leetcode.com/problems/merge-intervals/) · Medium · Pattern: `intervals`

- **Trigger Invariant:** After sorting by start, an interval overlaps the merged set iff it overlaps the LAST merged interval — so a single pass suffices.
- **Edge Cases:** Single interval; fully nested intervals ([1,10] ⊃ [2,3]) — take max end; touching endpoints merge.
- **Complexity:** Time O(n log n) · Aux space O(n) (sort + output).

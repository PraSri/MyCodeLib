# Search in Rotated Sorted Array

[LeetCode](https://leetcode.com/problems/search-in-rotated-sorted-array/) · Medium · Pattern: `binary_search`

- **Trigger Invariant:** At least one half around mid is always sorted; check whether target lies inside that sorted half's range to decide which half to keep.
- **Edge Cases:** Not rotated; single element; target equals a boundary (use ≤ / < carefully); distinct values assumed.
- **Complexity:** Time O(log n) · Aux space O(1).

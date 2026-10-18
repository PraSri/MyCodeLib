# Find Minimum in Rotated Sorted Array

[LeetCode](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/) · Medium · Pattern: `binary_search`

- **Trigger Invariant:** Compare mid with the right end: nums[mid] > nums[hi] means the drop (minimum) is strictly right of mid, otherwise it is at mid or left — the predicate is monotone.
- **Edge Cases:** Not rotated (or rotated n times); single element; two elements [2,1].
- **Complexity:** Time O(log n) · Aux space O(1).

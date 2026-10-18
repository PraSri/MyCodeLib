# Find Median from Data Stream

[LeetCode](https://leetcode.com/problems/find-median-from-data-stream/) · Hard · Pattern: `two_heaps`

- **Trigger Invariant:** Max-heap holds the lower half, min-heap the upper half; invariant: every low ≤ every high and size(low) − size(high) ∈ {0, 1}, so the median is at the roots.
- **Edge Cases:** First element; even count → average of roots (use double to avoid int overflow); negative numbers and duplicates.
- **Complexity:** Time addNum O(log n), findMedian O(1) · Aux space O(n).

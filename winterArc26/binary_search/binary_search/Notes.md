# Binary Search

[LeetCode](https://leetcode.com/problems/binary-search/) · Easy · Pattern: `binary_search`

- **Trigger Invariant:** Sorted array ⇒ the predicate nums[mid] < target is monotone; each comparison discards half while the invariant 'target ∈ [lo, hi] if present' holds.
- **Edge Cases:** Empty / single element; target outside range; compute mid as lo + (hi − lo) / 2 (or >>>) to avoid (lo + hi) overflow.
- **Complexity:** Time O(log n) · Aux space O(1).

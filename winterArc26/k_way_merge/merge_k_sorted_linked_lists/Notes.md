# Merge k Sorted Lists

[LeetCode](https://leetcode.com/problems/merge-k-sorted-lists/) · Hard · Pattern: `k_way_merge`

- **Trigger Invariant:** The next output node is the minimum among k current heads; a min-heap of size ≤ k returns it in O(log k), and only the popped list's next head needs to enter.
- **Edge Cases:** k == 0 or all lists empty; null entries in the array; equal values across lists (compare with Integer.compare, not subtraction).
- **Complexity:** Time O(N log k) · Aux space O(k).

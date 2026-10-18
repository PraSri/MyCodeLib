# 3Sum

[LeetCode](https://leetcode.com/problems/3sum/) · Medium · Pattern: `two_pointers`

- **Trigger Invariant:** Sort, fix the smallest element i, then the remainder is sorted Two Sum II; skipping equal neighbours at i and after a hit guarantees unique triplets.
- **Edge Cases:** Fewer than 3 elements; all zeros [0,0,0]; many duplicates; early exit once nums[i] > 0.
- **Complexity:** Time O(n²) · Aux space O(log n) for the sort (output excluded).

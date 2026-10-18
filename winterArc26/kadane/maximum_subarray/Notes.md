# Maximum Subarray

[LeetCode](https://leetcode.com/problems/maximum-subarray/) · Medium · Pattern: `kadane`

- **Trigger Invariant:** Best subarray ending at i either extends the one ending at i−1 or restarts at i; a negative running sum can only hurt what follows, so drop it.
- **Edge Cases:** All negative → the largest single element (don't init best to 0); single element; sums near int limits.
- **Complexity:** Time O(n) · Aux space O(1).

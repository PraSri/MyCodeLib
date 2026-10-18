# Maximum Product Subarray

[LeetCode](https://leetcode.com/problems/maximum-product-subarray/) · Medium · Pattern: `kadane`

- **Trigger Invariant:** A negative number swaps the roles of max and min, so track both the max and min product ending at i; zero resets both.
- **Edge Cases:** Zeros splitting the array; single negative element; even vs odd count of negatives.
- **Complexity:** Time O(n) · Aux space O(1).

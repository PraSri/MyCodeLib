# Product of Array Except Self

[LeetCode](https://leetcode.com/problems/product-of-array-except-self/) · Medium · Pattern: `prefix_suffix`

- **Trigger Invariant:** answer[i] = (product of everything left of i) × (product of everything right of i); fill prefixes in the output, then sweep a running suffix from the right — no division needed.
- **Edge Cases:** One zero → only that index is non-zero; two+ zeros → all zeros; negatives; problem guarantees products fit in int.
- **Complexity:** Time O(n) · Aux space O(1) (output array excluded).

# Find the Duplicate Number

[LeetCode](https://leetcode.com/problems/find-the-duplicate-number/) · Medium · Pattern: `fast_slow_pointers`

- **Trigger Invariant:** Values in [1, n] make i → nums[i] a functional graph from index 0; the duplicate value is the node with in-degree 2, i.e. the cycle entrance — Floyd phase 2 finds it.
- **Edge Cases:** Duplicate repeated many times ([2,2,2,2]); duplicate at the ends; must not modify the array or use extra space.
- **Complexity:** Time O(n) · Aux space O(1).

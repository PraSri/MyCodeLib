# Two Sum II - Input Array Is Sorted

[LeetCode](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/) · Medium · Pattern: `two_pointers`

- **Trigger Invariant:** Sorted order makes the sum monotonic in each pointer: too small → only moving l right can help; too big → only moving r left can help, so no pair is ever skipped.
- **Edge Cases:** Answer is 1-indexed; negatives; sum of two large ints can overflow (compare using long if bounds are wide).
- **Complexity:** Time O(n) · Aux space O(1).

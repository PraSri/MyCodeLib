# Kth Largest Element in an Array

[LeetCode](https://leetcode.com/problems/kth-largest-element-in-an-array/) · Medium · Pattern: `top_k_heap`

- **Trigger Invariant:** Bounded min-heap of size k: the root is the smallest of the k largest, i.e. the answer. (Quickselect gives O(n) average if asked.)
- **Edge Cases:** k == 1 (max) / k == n (min); duplicates count separately; negative numbers.
- **Complexity:** Time O(n log k) · Aux space O(k).

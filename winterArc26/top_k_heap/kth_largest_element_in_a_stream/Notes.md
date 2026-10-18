# Kth Largest Element in a Stream

[LeetCode](https://leetcode.com/problems/kth-largest-element-in-a-stream/) · Easy · Pattern: `top_k_heap`

- **Trigger Invariant:** Keep a min-heap of the k largest seen so far; its root is by definition the kth largest, and anything smaller than the root can never matter again.
- **Edge Cases:** Initial nums shorter than k (heap fills up through add); duplicates; negative values.
- **Complexity:** Time O(log k) per add · Aux space O(k).

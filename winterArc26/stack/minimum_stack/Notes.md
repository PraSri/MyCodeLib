# Min Stack

[LeetCode](https://leetcode.com/problems/min-stack/) · Medium · Pattern: `stack`

- **Trigger Invariant:** The minimum only changes on push/pop at the top, so store alongside each entry the min of the stack at that depth — popping restores the previous min for free.
- **Edge Cases:** Duplicate minimums (per-entry min handles them); values at Integer.MIN_VALUE; getMin/top on empty stack is undefined by contract.
- **Complexity:** Time O(1) per op · Aux space O(n).

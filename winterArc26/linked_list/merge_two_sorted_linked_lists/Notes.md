# Merge Two Sorted Lists

[LeetCode](https://leetcode.com/problems/merge-two-sorted-lists/) · Easy · Pattern: `linked_list`

- **Trigger Invariant:** The smaller of the two heads is always the next node of the merged list; a dummy head removes the empty-result special case, and the leftover tail is appended wholesale.
- **Edge Cases:** One or both lists empty; equal values (take from list1 first for stability); lists of very different lengths.
- **Complexity:** Time O(m + n) · Aux space O(1).

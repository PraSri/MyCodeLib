# Linked List Cycle

[LeetCode](https://leetcode.com/problems/linked-list-cycle/) · Easy · Pattern: `fast_slow_pointers`

- **Trigger Invariant:** In a cycle the fast pointer gains one node per step on the slow one, so the gap shrinks to 0 within one loop length; without a cycle fast hits null.
- **Edge Cases:** Empty list; single node with/without self-loop; check fast and fast.next before advancing.
- **Complexity:** Time O(n) · Aux space O(1).

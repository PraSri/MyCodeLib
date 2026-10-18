# Reverse Linked List

[LeetCode](https://leetcode.com/problems/reverse-linked-list/) · Easy · Pattern: `in_place_reversal`

- **Trigger Invariant:** Invariant: `prev` is the fully reversed prefix and `cur` the untouched suffix; save next, flip one pointer, advance both — no node is ever lost.
- **Edge Cases:** Empty list → null; single node; long lists make the recursive version stack-overflow (prefer iterative).
- **Complexity:** Time O(n) · Aux space O(1).

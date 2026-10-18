# Remove Nth Node From End of List

[LeetCode](https://leetcode.com/problems/remove-nth-node-from-end-of-list/) · Medium · Pattern: `linked_list`

- **Trigger Invariant:** Advance `fast` n+1 steps from a dummy, then move both; the constant gap leaves `slow` exactly before the node to delete in one pass.
- **Edge Cases:** Removing the head (n == length) — dummy handles it; single-node list → null; n == 1 removes the tail.
- **Complexity:** Time O(n) · Aux space O(1).

# Reverse Nodes in k-Group

[LeetCode](https://leetcode.com/problems/reverse-nodes-in-k-group/) · Hard · Pattern: `in_place_reversal`

- **Trigger Invariant:** Treat each k-block as an isolated reversal bounded by groupPrev and groupNext; reversing with prev = groupNext re-attaches the tail automatically, and the old first node becomes the next groupPrev.
- **Edge Cases:** k == 1 → unchanged; length not a multiple of k (last partial group stays as-is); k == length; use a dummy head so the first group needs no special case.
- **Complexity:** Time O(n) · Aux space O(1).

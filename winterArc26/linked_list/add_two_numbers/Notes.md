# Add Two Numbers

[LeetCode](https://leetcode.com/problems/add-two-numbers/) · Medium · Pattern: `linked_list`

- **Trigger Invariant:** Digits are stored least-significant first, which is exactly the order of grade-school addition; carry is the only state passed between positions.
- **Edge Cases:** Different lengths; final carry creates a new node (5 + 5 → 0 → 1); numbers far beyond long range (never convert to a number).
- **Complexity:** Time O(max(m, n)) · Aux space O(1) besides the output list.

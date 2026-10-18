# Contains Duplicate

[LeetCode](https://leetcode.com/problems/contains-duplicate/) · Easy · Pattern: `hashing`

- **Trigger Invariant:** Membership is the only question asked; a HashSet answers 'seen before?' in O(1), and `Set.add` returning false is the duplicate signal.
- **Edge Cases:** Empty / single-element array → false; values at Integer.MIN_VALUE / MAX_VALUE (no arithmetic, so no overflow).
- **Complexity:** Time O(n) · Aux space O(n).

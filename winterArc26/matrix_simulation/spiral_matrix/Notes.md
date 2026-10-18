# Spiral Matrix

[LeetCode](https://leetcode.com/problems/spiral-matrix/) · Medium · Pattern: `matrix_simulation`

- **Trigger Invariant:** Maintain four shrinking boundaries (top, bottom, left, right); each side of the spiral consumes one boundary. Re-check bounds before the bottom/left passes to avoid double-reading a single remaining row/column.
- **Edge Cases:** Single row or single column; non-square matrices; 1×1.
- **Complexity:** Time O(m · n) · Aux space O(1) besides the output.

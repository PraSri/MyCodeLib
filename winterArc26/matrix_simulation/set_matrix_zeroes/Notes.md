# Set Matrix Zeroes

[LeetCode](https://leetcode.com/problems/set-matrix-zeroes/) · Medium · Pattern: `matrix_simulation`

- **Trigger Invariant:** Use the first row and column as the zero-marker arrays; remember separately whether row 0 / col 0 themselves had a zero, and clear them LAST so markers aren't destroyed early.
- **Edge Cases:** Zero in matrix[0][0]; zero only in the first row or column; 1×n and m×1 matrices.
- **Complexity:** Time O(m · n) · Aux space O(1).

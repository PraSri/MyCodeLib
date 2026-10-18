# Rotate Image

[LeetCode](https://leetcode.com/problems/rotate-image/) · Medium · Pattern: `matrix_simulation`

- **Trigger Invariant:** Clockwise rotation = transpose (swap across the main diagonal) followed by reversing each row — two in-place involutions.
- **Edge Cases:** 1×1 matrix; odd n (center stays); must be in place (no copy).
- **Complexity:** Time O(n²) · Aux space O(1).

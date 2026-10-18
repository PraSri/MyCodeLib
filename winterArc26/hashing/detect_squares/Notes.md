# Detect Squares

[LeetCode](https://leetcode.com/problems/detect-squares/) · Medium · Pattern: `hashing`

- **Trigger Invariant:** Fix the query point and enumerate every stored point as the diagonal corner (|dx| == |dy| ≠ 0); the other two corners are then determined, so multiply their counts.
- **Edge Cases:** Duplicate points multiply counts; zero-area squares (same point) must be excluded; coordinates bounded 0..1000 so a 2D count grid works.
- **Complexity:** Time add O(1), count O(P) distinct points · Aux space O(P + 1001²).

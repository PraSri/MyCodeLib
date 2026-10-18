# Search a 2D Matrix

[LeetCode](https://leetcode.com/problems/search-a-2d-matrix/) · Medium · Pattern: `binary_search`

- **Trigger Invariant:** Rows are sorted and each row starts after the previous ends, so the matrix is one sorted array of m·n; map index k → (k / n, k % n).
- **Edge Cases:** 1×1 matrix; target smaller than first / larger than last; m·n up to 10^4 fits int.
- **Complexity:** Time O(log(m·n)) · Aux space O(1).

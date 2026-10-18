# N-Queens

[LeetCode](https://leetcode.com/problems/n-queens/) · Hard · Pattern: `backtracking`

- **Trigger Invariant:** Place one queen per row; a column, a '\' diagonal (r − c) and a '/' diagonal (r + c) can each hold one queen — three boolean arrays make the safety check O(1).
- **Edge Cases:** n = 1 → [["Q"]]; n = 2 and 3 → []; offset r − c by n − 1 to keep indices non-negative.
- **Complexity:** Time O(n!) · Aux space O(n).

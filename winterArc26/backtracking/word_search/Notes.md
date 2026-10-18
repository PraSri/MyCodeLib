# Word Search

[LeetCode](https://leetcode.com/problems/word-search/) · Medium · Pattern: `backtracking`

- **Trigger Invariant:** DFS from each cell matching word[0]; temporarily overwrite the cell to mark it used on the current path and restore it on the way back (classic choose/explore/un-choose).
- **Edge Cases:** Word longer than m·n → false fast; single cell grid; cells must not be reused within one path; restore the board after every branch.
- **Complexity:** Time O(m · n · 3^L) · Aux space O(L) recursion.

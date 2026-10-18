# Valid Sudoku

[LeetCode](https://leetcode.com/problems/valid-sudoku/) · Medium · Pattern: `hashing`

- **Trigger Invariant:** Each digit may appear once per row, column and 3×3 box; box id = (r/3)*3 + c/3 lets one pass mark three seen-tables.
- **Edge Cases:** '.' cells must be skipped; a board that is valid but unsolvable is still valid; only filled cells are checked.
- **Complexity:** Time O(81) = O(1) · Aux space O(1) (3×9×9 booleans).

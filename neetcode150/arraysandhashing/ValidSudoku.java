package arraysandhashing;

import java.util.HashSet;
import java.util.Set;

class ValidSudoku {

    public boolean isValidSudoku(char[][] board) {
        int n = board.length;
        int m = board[0].length;
        Set<String> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char ch = board[i][j];
                if (ch != '.') {
                    if (
                            !set.add(ch + " in row " + i)
                                    || !set.add(ch + " in col " + j)
                                    || !set.add(ch + " in block " + i / 3 + " - " + j / 3)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // https://leetcode.com/problems/sudoku-solver/
    public static class SudokuSolver {

    }

    // https://leetcode.com/problems/check-if-every-row-and-column-contains-all-numbers/
    public static class CheckIfEveryRowAndColumnContainsAllNumbers {

    }


}

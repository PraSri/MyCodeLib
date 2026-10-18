package backtracking.n_queens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        int[] queenCol = new int[n];
        backtrack(0, n, queenCol, new boolean[n], new boolean[2 * n - 1], new boolean[2 * n - 1], result);
        return result;
    }

    private void backtrack(int r, int n, int[] queenCol, boolean[] cols, boolean[] diag, boolean[] anti,
                           List<List<String>> result) {
        if (r == n) {
            result.add(render(queenCol));
            return;
        }
        for (int c = 0; c < n; c++) {
            int d = r - c + n - 1;
            int a = r + c;
            if (cols[c] || diag[d] || anti[a]) {
                continue;
            }
            cols[c] = diag[d] = anti[a] = true;
            queenCol[r] = c;
            backtrack(r + 1, n, queenCol, cols, diag, anti, result);
            cols[c] = diag[d] = anti[a] = false;
        }
    }

    private List<String> render(int[] queenCol) {
        int n = queenCol.length;
        List<String> board = new ArrayList<>(n);
        for (int col : queenCol) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[col] = 'Q';
            board.add(new String(row));
        }
        return board;
    }
}

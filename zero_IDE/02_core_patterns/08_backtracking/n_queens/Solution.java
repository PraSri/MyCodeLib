import java.util.*;

public class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<>();
        int[] queens = new int[n]; // queens[row] = col
        boolean[] cols = new boolean[n];
        boolean[] diag1 = new boolean[2 * n]; // r - c + n
        boolean[] diag2 = new boolean[2 * n]; // r + c

        backtrack(0, n, queens, cols, diag1, diag2, solutions);
        return solutions;
    }

    private void backtrack(int r, int n, int[] queens, boolean[] cols, boolean[] diag1, boolean[] diag2, List<List<String>> solutions) {
        if (r == n) {
            solutions.add(buildBoard(queens, n));
            return;
        }

        for (int c = 0; c < n; c++) {
            int d1 = r - c + n;
            int d2 = r + c;

            if (cols[c] || diag1[d1] || diag2[d2]) continue;

            queens[r] = c;
            cols[c] = diag1[d1] = diag2[d2] = true;

            backtrack(r + 1, n, queens, cols, diag1, diag2, solutions);

            cols[c] = diag1[d1] = diag2[d2] = false; // Backtrack
        }
    }

    private List<String> buildBoard(int[] queens, int n) {
        List<String> board = new ArrayList<>(n);
        for (int r = 0; r < n; r++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[r]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }
}

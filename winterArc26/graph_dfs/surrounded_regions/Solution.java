package graph_dfs.surrounded_regions;

public class Solution {

    public void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        for (int r = 0; r < m; r++) {
            markSafe(board, r, 0);
            markSafe(board, r, n - 1);
        }
        for (int c = 0; c < n; c++) {
            markSafe(board, 0, c);
            markSafe(board, m - 1, c);
        }
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (board[r][c] == 'O') {
                    board[r][c] = 'X';
                } else if (board[r][c] == 'S') {
                    board[r][c] = 'O';
                }
            }
        }
    }

    private void markSafe(char[][] board, int r, int c) {
        if (r < 0 || c < 0 || r == board.length || c == board[0].length || board[r][c] != 'O') {
            return;
        }
        board[r][c] = 'S';
        markSafe(board, r + 1, c);
        markSafe(board, r - 1, c);
        markSafe(board, r, c + 1);
        markSafe(board, r, c - 1);
    }
}

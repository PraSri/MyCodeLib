package backtracking.word_search;

public class Solution {

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        if (word.length() > m * n) {
            return false;
        }
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (dfs(board, word, 0, r, c)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int i, int r, int c) {
        if (i == word.length()) {
            return true;
        }
        if (r < 0 || c < 0 || r == board.length || c == board[0].length || board[r][c] != word.charAt(i)) {
            return false;
        }
        char saved = board[r][c];
        board[r][c] = '#';
        boolean found = dfs(board, word, i + 1, r + 1, c)
                || dfs(board, word, i + 1, r - 1, c)
                || dfs(board, word, i + 1, r, c + 1)
                || dfs(board, word, i + 1, r, c - 1);
        board[r][c] = saved;
        return found;
    }
}

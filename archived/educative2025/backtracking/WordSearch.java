package backtracking;

public class WordSearch {

    public boolean exist(char[][] board, String word) {

        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(i, j, word, 0, board)) {
                    return true;
                }
            }
        }

        return false;

    }

    private boolean dfs(int row, int col, String word, int index, char[][] board) {
        // base case
        if (word.length() == index)
            return true;
        // check if out of bound, return false
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || word.charAt(index) != board[row][col]) {
            return false;
        }
        boolean result = false;
        char temp = board[row][col];
        board[row][col] = '*';
        int[][] offsets = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        for (int[] offset : offsets) {
            int rowOffset = offset[0];
            int colOffset = offset[1];
            result = dfs(row + rowOffset, col + colOffset, word, index + 1, board);
            if (result)
                break;
        }
        board[row][col] = temp;
        return result;
    }

}

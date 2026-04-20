package microsoft;

public class CandyCrush {

    public int[][] candyCrush(int[][] board) {

        int rows = board.length;
        int cols = board[0].length;
        
        boolean shouldContinue = true;

        while (shouldContinue) {
            shouldContinue = false;

            // Mark horizontal matches (3 or more consecutive same candies in a row)
            for (int row = 0; row < rows; row++) {
                for (int col = 2; col < cols; col++) {
                    // Check if current position and previous 2 positions have same candy type
                    if (board[row][col] != 0 &&
                            Math.abs(board[row][col]) == Math.abs(board[row][col - 1]) &&
                            Math.abs(board[row][col]) == Math.abs(board[row][col - 2])) {

                        shouldContinue = true;
                        int candyValue = Math.abs(board[row][col]);
                        // Mark these positions as negative to indicate they should be crushed
                        board[row][col] = -candyValue;
                        board[row][col - 1] = -candyValue;
                        board[row][col - 2] = -candyValue;
                    }
                }
            }

            // Mark vertical matches (3 or more consecutive same candies in a column)
            for (int col = 0; col < cols; col++) {
                for (int row = 2; row < rows; row++) {
                    // Check if current position and previous 2 positions have same candy type
                    if (board[row][col] != 0 &&
                            Math.abs(board[row][col]) == Math.abs(board[row - 1][col]) &&
                            Math.abs(board[row][col]) == Math.abs(board[row - 2][col])) {

                        shouldContinue = true;
                        int candyValue = Math.abs(board[row][col]);
                        // Mark these positions as negative to indicate they should be crushed
                        board[row][col] = -candyValue;
                        board[row - 1][col] = -candyValue;
                        board[row - 2][col] = -candyValue;
                    }
                }
            }

            // Drop candies down after crushing marked candies
            if (shouldContinue) {
                for (int col = 0; col < cols; col++) {
                    int writePosition = rows - 1;

                    // Move all positive values (uncrushed candies) down
                    for (int row = rows - 1; row >= 0; row--) {
                        if (board[row][col] > 0) {
                            board[writePosition][col] = board[row][col];
                            writePosition--;
                        }
                    }

                    // Fill remaining top positions with 0 (empty spaces)
                    while (writePosition >= 0) {
                        board[writePosition][col] = 0;
                        writePosition--;
                    }
                }
            }
        }

        return board;
    }
}


package wise;

import java.util.Scanner;

/**
 * NxN Tic Tac Toe game with adjustable win condition
 */
public class TicTacToe {
    private final int size;
    private final int winCount;
    private char[][] board;
    private char currentPlayer;

    /**
     * Constructor to initialize the game
     *
     * @param size     Size of the board (NxN)
     * @param winCount Number of consecutive marks needed to win
     */
    public TicTacToe(int size, int winCount) {
        this.size = size;
        this.winCount = Math.min(winCount, size); // Can't win with more than size
        this.board = new char[size][size];
        this.currentPlayer = 'X';
        initializeBoard();
    }

    /**
     * Main game loop
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== NxN Tic Tac Toe with Custom Win Count ===");

        // Get board size
        System.out.print("Enter board size (N): ");
        int size = scanner.nextInt();

        // Get win count
        System.out.print("Enter win count (consecutive marks needed to win): ");
        int winCount = scanner.nextInt();

        // Validate inputs
        if (winCount > size) {
            System.out.println("Warning: Win count cannot be greater than board size.");
            System.out.println("Setting win count to board size (" + size + ").");
            winCount = size;
        }

        TicTacToe game = new TicTacToe(size, winCount);
        boolean gameRunning = true;

        System.out.println("\nGame started! Player X goes first.");
        System.out.println("Enter row and column (0-based indices separated by space)");

        while (gameRunning) {
            game.displayBoard();
            System.out.println("\nPlayer " + game.getCurrentPlayer() + "'s turn");
            System.out.print("Enter row and column: ");

            int row = scanner.nextInt();
            int col = scanner.nextInt();

            // Make the move
            if (game.makeMove(row, col)) {
                // Check for win
                if (game.checkWin()) {
                    game.displayBoard();
                    System.out.println("\n? Player " + game.getCurrentPlayer() + " wins!");
                    gameRunning = false;
                }
                // Check for draw
                else if (game.isBoardFull()) {
                    game.displayBoard();
                    System.out.println("\n? It's a draw!");
                    gameRunning = false;
                }
                // Continue game
                else {
                    game.switchPlayer();
                }
            } else {
                System.out.println("Invalid move! Try again.");
            }
        }

        scanner.close();
        System.out.println("Game over!");
    }

    /**
     * Initialize the board with empty spaces
     */
    private void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = '-';
            }
        }
    }

    /**
     * Display the current board state
     */
    public void displayBoard() {
        System.out.println("\nCurrent Board:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Make a move on the board
     *
     * @param row Row index (0-based)
     * @param col Column index (0-based)
     * @return true if move is valid, false otherwise
     */
    public boolean makeMove(int row, int col) {
        // Check if position is valid and empty
        if (row < 0 || row >= size || col < 0 || col >= size || board[row][col] != '-') {
            return false;
        }

        board[row][col] = currentPlayer;
        return true;
    }

    /**
     * Switch to the other player
     */
    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    /**
     * Check if the current player has won
     *
     * @return true if current player has won, false otherwise
     */
    public boolean checkWin() {
        // Check all rows and columns
        for (int i = 0; i < size; i++) {
            if (checkRow(i) || checkColumn(i)) {
                return true;
            }
        }

        // Check all diagonals (both directions)
        for (int i = 0; i <= size - winCount; i++) {
            for (int j = 0; j <= size - winCount; j++) {
                if (checkDiagonal(i, j, 1, 1) || checkDiagonal(i, j + winCount - 1, 1, -1)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Check for win in a specific row
     *
     * @param row Row index to check
     * @return true if win condition is met in this row
     */
    private boolean checkRow(int row) {
        for (int col = 0; col <= size - winCount; col++) {
            boolean win = true;
            // Check winCount consecutive cells
            for (int k = 0; k < winCount; k++) {
                if (board[row][col + k] != currentPlayer) {
                    win = false;
                    break;
                }
            }
            if (win) return true;
        }
        return false;
    }

    /**
     * Check for win in a specific column
     *
     * @param col Column index to check
     * @return true if win condition is met in this column
     */
    private boolean checkColumn(int col) {
        for (int row = 0; row <= size - winCount; row++) {
            boolean win = true;
            // Check winCount consecutive cells
            for (int k = 0; k < winCount; k++) {
                if (board[row + k][col] != currentPlayer) {
                    win = false;
                    break;
                }
            }
            if (win) return true;
        }
        return false;
    }

    /**
     * Check for win in a diagonal direction
     *
     * @param startRow Starting row index
     * @param startCol Starting column index
     * @param rowDir   Row direction (1 for down, -1 for up)
     * @param colDir   Column direction (1 for right, -1 for left)
     * @return true if win condition is met in this diagonal
     */
    private boolean checkDiagonal(int startRow, int startCol, int rowDir, int colDir) {
        // Check if starting position is valid and within bounds for winCount
        if (startRow < 0 || startRow + (winCount - 1) * rowDir >= size ||
                startCol < 0 || startCol + (winCount - 1) * colDir >= size ||
                startRow + (winCount - 1) * rowDir < 0 ||
                startCol + (winCount - 1) * colDir < 0) {
            return false;
        }

        // Check winCount consecutive cells in diagonal
        for (int k = 0; k < winCount; k++) {
            int row = startRow + k * rowDir;
            int col = startCol + k * colDir;
            if (board[row][col] != currentPlayer) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if the board is completely filled (draw)
     *
     * @return true if board is full, false otherwise
     */
    public boolean isBoardFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Get the current player
     *
     * @return current player (X or O)
     */
    public char getCurrentPlayer() {
        return currentPlayer;
    }
}

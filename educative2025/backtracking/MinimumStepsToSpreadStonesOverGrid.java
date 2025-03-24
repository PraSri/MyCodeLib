package backtracking;

import java.util.ArrayList;
import java.util.List;

public class MinimumStepsToSpreadStonesOverGrid {

    // Backtracking function to explore possible moves
    private static int minMoves;

    public static int minimumMoves(int[][] grid) {

        // Arrays to store coordinates of zeros (empty cells) and extras (cells with excess stones)
        List<int[]> zeros = new ArrayList<>();
        List<int[]> extras = new ArrayList<>();

        // Initialize minimum moves to a very large number
        minMoves = Integer.MAX_VALUE;

        // Calculate the total number of stones
        int totalStones = 0;
        for (int[] row : grid) {
            for (int cell : row) {
                totalStones += cell;
            }
        }

        // Check if the total number of stones is exactly 9
        if (totalStones != 9) {
            return -1; // Return -1 if the total number of stones is not exactly 9
        }

        // Populate zeros and extras arrays
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (grid[x][y] == 0) {

                    // Store the coordinates of empty cells
                    zeros.add(new int[]{x, y});
                } else if (grid[x][y] > 1) {

                    // Store the coordinates and excess stones in cells with more than one stone
                    extras.add(new int[]{x, y, grid[x][y] - 1});
                }
            }
        }

        if (zeros.size() == 0) {
            return 0; // No moves needed if there are no empty cells
        }

        solve(zeros, extras, 0, 0);
        return minMoves;
    }

    // Backtracking function to explore possible moves
    private static void solve(List<int[]> zeros, List<int[]> extras, int i, int count) {

        // If all zeros have been processed
        if (i >= zeros.size()) {

            // Update the minimum moves if a better solution is found
            minMoves = Math.min(minMoves, count);
            return;
        }

        // Try to move stones from all extra cells to the current zero cell
        for (int k = 0; k < extras.size(); k++) {

            // Check if there are stones to move
            if (extras.get(k)[2] != 0) {

                // Move one stone from the extra cell to the zero cell
                extras.get(k)[2]--;

                // Recursively solve for the next zero cell
                solve(zeros, extras, i + 1, Math.abs(extras.get(k)[0] - zeros.get(i)[0]) + Math.abs(extras.get(k)[1] - zeros.get(i)[1]) + count);

                // Backtrack: undo the move
                extras.get(k)[2]++;
            }
        }
    }

    int res = Integer.MAX_VALUE;
    public int minimumMoves2(int[][] grid) {
        dfs (0, grid, 0);
        return res;
    }

    public void dfs(int cur, int[][]grid, int moves) {
        if (cur == 9) {
            res = Math.min(res, moves);
            return;
        }

        int x = cur / 3;
        int y = cur % 3;
        if (grid[x][y] > 0) {
            dfs(cur + 1, grid, moves);
            return;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] <= 1) {
                    continue;
                }
                grid[i][j] -= 1;
                grid[x][y] += 1;
                dfs(cur + 1, grid, moves + Math.abs(x - i) + Math.abs(y - j));
                grid[i][j] += 1;
                grid[x][y] -= 1;
            }
        }
    }

    // Driver code
    public static void main(String[] args) {
        int[][][] grids = {
                {
                        {1, 1, 1},
                        {1, 2, 3},
                        {0, 0, 0}
                },
                {
                        {8, 1, 0},
                        {0, 0, 0},
                        {0, 0, 0}
                },
                {
                        {2, 2, 2},
                        {1, 1, 1},
                        {0, 0, 0}
                },
                {
                        {3, 0, 0},
                        {3, 0, 0},
                        {3, 0, 0}
                },
                {
                        {1, 0, 1},
                        {3, 0, 0},
                        {0, 4, 0}
                }
        };

        for (int i = 0; i < grids.length; i++) {
            System.out.println((i + 1) + ".\tInput grid: ");
            draw2DArray(grids[i]);
            System.out.println("\n\tMinimum number of moves: " + minimumMoves(grids[i]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }

    private static void draw2DArray(int[][] grid) {
        for (int[] row : grid) {
            System.out.print("\t| ");
            for (int cell : row) {
                System.out.print(cell + " | ");
            }
            System.out.println();
        }
    }
}

package graphs;

import java.util.*;

public class MinimumCostToMakeAtLeastOneValidPathInGrid {

    // Helper function to check if the new cell is valid and its cost can be improved
    private boolean isValidAndImprovable(int[][] costGrid, int row, int col) {
        return row >= 0 && row < costGrid.length &&
                col >= 0 && col < costGrid[0].length &&
                costGrid[row][col] != 0;
    }

    public int minCost(int[][] grid) {
        // Store the number of rows and columns of grid
        int n = grid.length, m = grid[0].length;

        // Create a 2D array of size n x m, initializing all cells to the maximum integer value
        int[][] costGrid = new int[n][m];
        for (int[] row : costGrid)
            Arrays.fill(row, Integer.MAX_VALUE);

        // Create a deque and push the starting cell (0, 0) to the front
        Deque<int[]> dq = new ArrayDeque<>();
        dq.offerFirst(new int[]{0, 0});

        // Set its cost in costGrid to 0
        costGrid[0][0] = 0;

        // Define an array representing the four possible movement directions
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        // Enter a loop that continues as long as the deque is not empty
        while (!dq.isEmpty()) {
            // Pop the front cell from the deque and store its coordinates in row and col
            int[] current = dq.pollFirst();
            int row = current[0], col = current[1];

            // Loop through each of the four directions in dirs
            for (int d = 0; d < 4; d++) {
                // Compute the coordinates of the adjacent cell
                int newRow = row + dirs[d][0];
                int newCol = col + dirs[d][1];

                // Check if the new cell is valid and its cost can be improved
                if (isValidAndImprovable(costGrid, newRow, newCol)) {
                    // Calculate the movement cost
                    int cost = (grid[row][col] != (d + 1)) ? 1 : 0;

                    // Check whether the new cost is less than the current cost at the adjacent cell
                    if (costGrid[row][col] + cost < costGrid[newRow][newCol]) {
                        // Update the cost of the adjacent cell
                        costGrid[newRow][newCol] = costGrid[row][col] + cost;

                        if (cost == 1)
                            // Push the new cell to the back
                            dq.offerLast(new int[]{newRow, newCol});
                        else
                            // Push the new cell to the front
                            dq.offerFirst(new int[]{newRow, newCol});
                    }
                }
            }
        }

        // Return the minimum cost stored at the bottom-right cell
        return costGrid[n - 1][m - 1];
    }

    // Driver code
    public static void main(String[] args) {
        int[][][] gridArr = {
                {{1, 1, 3}, {2, 2, 3}, {1, 1, 4}},
                {{1, 1, 3, 1}, {3, 1, 2, 2}, {1, 4, 1, 1}, {4, 1, 1, 3}},
                {{1, 1}, {1, 1}},
                {{4}},
                {{4, 3, 4, 3}, {3, 4, 3, 4}}
        };

        MinimumCostToMakeAtLeastOneValidPathInGrid sol = new MinimumCostToMakeAtLeastOneValidPathInGrid();
        for (int i = 0; i < gridArr.length; ++i) {
            System.out.print((i + 1) + ".\tgrid: [");
            for (int j = 0; j < gridArr[i].length; ++j) {
                System.out.print("[");
                for (int k = 0; k < gridArr[i][j].length; ++k) {
                    System.out.print(gridArr[i][j][k]);
                    if (k < gridArr[i][j].length - 1)
                        System.out.print(", ");
                }
                System.out.print("]");
                if (j < gridArr[i].length - 1)
                    System.out.print(", ");
            }
            System.out.println("]\n");
            System.out.println("\tMinimum cost: " + sol.minCost(gridArr[i]));
            System.out.println("-".repeat(100));
        }
    }
}


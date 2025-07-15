package union_find;

import java.util.*;

public class LastDayWhereYouCanStillCross {

    public static class UnionFind {
        private final int[] reps;

        public UnionFind(int n) {
            reps = new int[n];

            for (int i = 0; i < n; i++) {
                reps[i] = i;
            }
        }

        public int find(int x) {
            if (reps[x] != x) {
                reps[x] = find(reps[x]);
            }
            return reps[x];
        }

        public void union(int v1, int v2) {
            reps[find(v1)] = find(v2);
        }
    }

    public static int lastDayToCross(int rows, int cols, int[][] waterCells) {

        // create a variable to keep track of the number of days
        int day = 0;

        // create the matrix that needs to be crossed
        int[][] matrix = new int[rows][cols];

        // create the two virtual nodes, one before the first column and the other after the last column of the matrix
        int leftNode = 0;
        int rightNode = rows * cols + 1;

        // specify the directions where water can move
        int[][] waterDirections = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

        // convert the waterCells from 1-based to 0-based array for the convenience
        int[][] convertedWaterCells = new int[waterCells.length][2];
        for (int i = 0; i < waterCells.length; i++) {
            convertedWaterCells[i] = new int[]{waterCells[i][0] - 1, waterCells[i][1] - 1};
        }

        // initialize the UnionFind object, this will create the disjoint set union datastructure, an array - reps
        UnionFind uf = new UnionFind(rows * cols + 2);

        // On each day, one cell of the matrix will get flooded
        for (int[] cell : convertedWaterCells) {
            int row = cell[0];
            int col = cell[1];

            // change the matrix's cell from land (0) to water (1)
            matrix[row][col] = 1;

            // check if the recently flooded cell connects with any of the existing water cells
            for (int[] direction : waterDirections) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];

                if (withinBounds(newRow, newCol, rows, cols) && matrix[newRow][newCol] == 1) {
                    uf.union(findIndex(row, col, cols), findIndex(newRow, newCol, cols));
                }
            }

            if (col == 0) {
                uf.union(findIndex(row, col, cols), leftNode);
            }
            if (col == cols - 1) {
                uf.union(findIndex(row, col, cols), rightNode);
            }

            // check if we got a series of connected water cells from the left to the right side of the matrix
            if (uf.find(leftNode) == uf.find(rightNode)) {
                break;
            }
            day++;
        }
        return day;
    }

    // helper functions

    // maps the index of the element in 2-D matrix to an index of the 1-D array (reps)
    public static int findIndex(int currentRow, int currentCol, int cols) {
        return currentRow * cols + (currentCol + 1);
    }

    // checks whether the water cells to be connected are within the bounds of the matrix as per given dimensions
    public static boolean withinBounds(int row, int col, int rows, int cols) {
        return col >= 0 && col < cols && row >= 0 && row < rows;
    }

    // driver code
    public static void main(String[] args) {
        int[][][] allWaterCells = {
                {{1, 2}, {2, 1}, {3, 3}, {2, 2}, {1, 1}, {1, 3}, {2, 3}, {3, 2}, {3, 1}},
                {{1, 1}, {2, 1}, {1, 2}, {2, 2}},
                {{1, 1}, {1, 2}, {1, 3}, {2, 1}, {3, 1}, {2, 2}, {3, 2}, {2, 3}, {3, 3}},
                {{1, 5}, {2, 5}, {2, 4}, {2, 3}, {2, 2}, {3, 2}, {4, 2}, {4, 1}, {3, 1}, {2, 1},
                        {1, 1}, {1, 2}, {1, 3}, {1, 4}, {3, 3}, {3, 5}, {3, 4}, {4, 5}, {4, 3}, {4, 4}},
                {{1, 2}, {1, 3}, {1, 4}, {1, 5}, {1, 6}, {1, 7}, {2, 5}, {2, 6}, {2, 7}, {3, 1},
                        {3, 2}, {3, 3}, {3, 7}, {4, 7}, {4, 5}, {4, 4}, {4, 3}, {4, 2}, {4, 1}, {5, 1}, {5, 2}, {5, 3}, {5, 4},
                        {5, 5}, {5, 7}, {6, 7}, {7, 7}, {7, 6}, {7, 5}, {7, 4}, {7, 3}, {7, 2}, {7, 1}, {6, 1}, {6, 2}, {6, 3},
                        {6, 4}, {6, 5}, {6, 6}, {5, 6}, {4, 6}, {3, 6}, {3, 5}, {3, 4}, {2, 4}, {2, 3}, {2, 2}, {2, 1}, {1, 1}},
                {{3, 2}, {1, 1}, {1, 2}, {3, 3}, {2, 3}, {1, 3}, {2, 1}, {2, 2}, {3, 1}}
        };

        int[] allRows = {3, 2, 3, 4, 7, 3};
        int[] allCols = {3, 2, 3, 5, 7, 3};

        for (int i = 0; i < allWaterCells.length; i++) {
            System.out.println(i + 1 + ". \tNumber of rows: " + allRows[i]);
            System.out.println("\tNumber of columns: " + allCols[i]);
            System.out.println("\n\tCells to be flooded: " + Arrays.deepToString(allWaterCells[i]));
            int lastDay = lastDayToCross(allRows[i], allCols[i], allWaterCells[i]);

            System.out.println("\n\tLast day where you can still cross: " + lastDay);
        }
    }
}

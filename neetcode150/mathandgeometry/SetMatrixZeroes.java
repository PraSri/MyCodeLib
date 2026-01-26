package mathandgeometry;

/**
 * Game of Life
 * https://leetcode.com/problems/game-of-life/
 * <p>
 * Number of Laser Beams in a Bank
 * https://leetcode.com/problems/number-of-laser-beams-in-a-bank/
 * <p>
 * Minimum Operations to Remove Adjacent Ones in Matrix
 * https://leetcode.com/problems/minimum-operations-to-remove-adjacent-ones-in-matrix/
 * <p>
 * Remove All Ones With Row and Column Flips II
 * https://leetcode.com/problems/remove-all-ones-with-row-and-column-flips-ii/
 **/

public class SetMatrixZeroes {

    /***
     *
     * Use the first row to mark which columns should become zero
     * Use the first column to mark which rows should become zero
     *
     *One complication:
     *
     * matrix[0][0] sits at the intersection of the first row and first column, so it can't independently represent both.
     * Also, we must separately track whether the first row originally contained a zero.
     * That's why we keep a boolean rowZero:
     *
     * rowZero = true means the first row must be zeroed at the end.
     *
     * */

    public void setZeroes(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        boolean rowZero = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    if (i > 0) {
                        matrix[i][0] = 0;
                    } else {
                        rowZero = true;
                    }
                }
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (matrix[0][0] == 0) {
            for (int i = 0; i < n; i++) {
                matrix[i][0] = 0;
            }
        }

        if (rowZero) {
            for (int j = 0; j < m; j++) {
                matrix[0][j] = 0;
            }
        }
    }

    /**
     * Game of Life
     * https://leetcode.com/problems/game-of-life
     */
    public static class GameOfLife {
    }

    /**
     * Number of Laser Beams in a Bank
     * https://leetcode.com/problems/number-of-laser-beams-in-a-bank
     */
    public static class NumberOfLaserBeamsInABank {
    }

    /**
     * Minimum Operations to Remove Adjacent Ones in Matrix
     * https://leetcode.com/problems/minimum-operations-to-remove-adjacent-ones-in-matrix
     */
    public static class MinimumOperationsToRemoveAdjacentOnesInMatrix {
    }

    /**
     * Remove All Ones With Row and Column Flips II
     * https://leetcode.com/problems/remove-all-ones-with-row-and-column-flips-ii
     */
    public static class RemoveAllOnesWithRowAndColumnFlipsIi {
    }
}

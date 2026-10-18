package matrix_simulation.set_matrix_zeroes;

public class Solution {

    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean firstRowZero = false;
        boolean firstColZero = false;
        for (int c = 0; c < n; c++) {
            firstRowZero |= matrix[0][c] == 0;
        }
        for (int r = 0; r < m; r++) {
            firstColZero |= matrix[r][0] == 0;
        }
        for (int r = 1; r < m; r++) {
            for (int c = 1; c < n; c++) {
                if (matrix[r][c] == 0) {
                    matrix[r][0] = 0;
                    matrix[0][c] = 0;
                }
            }
        }
        for (int r = 1; r < m; r++) {
            for (int c = 1; c < n; c++) {
                if (matrix[r][0] == 0 || matrix[0][c] == 0) {
                    matrix[r][c] = 0;
                }
            }
        }
        if (firstRowZero) {
            for (int c = 0; c < n; c++) {
                matrix[0][c] = 0;
            }
        }
        if (firstColZero) {
            for (int r = 0; r < m; r++) {
                matrix[r][0] = 0;
            }
        }
    }
}

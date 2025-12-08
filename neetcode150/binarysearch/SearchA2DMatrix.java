package binarysearch;

public class SearchA2DMatrix {
    // staircase search
    // start from top-right corner
    // if curr > target move left
    // if curr < target move down
    // keep till we found target or out of bounds
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int r = 0, c = n - 1;

        while (r < m && c >= 0) {
            if (matrix[r][c] > target) {
                c--;
            } else if (matrix[r][c] < target) {
                r++;
            } else {
                return true;
            }
        }
        return false;
    }

    // solution 2: imagine the matrix as flattened array which is sorted

    public boolean searchMatrixV2(int[][] matrix, int target) {

        int ROWS = matrix.length, COLS = matrix[0].length;

        int l = 0, r = ROWS * COLS - 1;

        while (l <= r) {

            int m = l + (r - l) / 2;

            int row = m / COLS, col = m % COLS;

            if (target > matrix[row][col]) {
                l = m + 1;
            } else if (target < matrix[row][col]) {
                r = m - 1;
            } else {
                return true;
            }
        }
        return false;
    }
}

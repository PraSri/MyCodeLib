package binary_search.search_a_2d_matrix;

public class Solution {

    public boolean searchMatrix(int[][] matrix, int target) {
        int cols = matrix[0].length;
        int lo = 0;
        int hi = matrix.length * cols - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            int value = matrix[mid / cols][mid % cols];
            if (value == target) {
                return true;
            }
            if (value < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return false;
    }
}

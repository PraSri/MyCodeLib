package mathandgeometry;

// Determine Whether Matrix Can Be Obtained By Rotation - https://leetcode.com/problems/determine-whether-matrix-can-be-obtained-by-rotation/

public class RotateImage {

    /**
     * Intuition
     * We want to rotate an n x n matrix 90 degrees clockwise in-place.
     * <p>
     * A very clean way to do this is to break the rotation into two simple operations:
     * <p>
     * Reverse the matrix vertically - the first row becomes the last & the last row becomes the first
     * <p>
     * Transpose the matrix - swap elements across the main diagonal -> for all i < j, swap matrix[i][j] with matrix[j][i]
     * Why this works:
     * <p>
     * Reversing the matrix flips it upside down
     * Transposing swaps rows with columns
     * Doing both together results in a 90 clockwise rotation
     * This method is elegant, easy to remember, and avoids extra space.
     **/

    public void rotate(int[][] matrix) {
        reverse(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix[i].length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    private void reverse(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            int[] temp = matrix[i];
            matrix[i] = matrix[n - i - 1];
            matrix[n - i - 1] = temp;
        }
    }


    /**
     * Determine Whether Matrix Can Be Obtained By Rotation
     * https://leetcode.com/problems/determine-whether-matrix-can-be-obtained-by-rotation/
     */
    public static class DetermineWhetherMatrixCanBeObtainedByRotation {
    }
}

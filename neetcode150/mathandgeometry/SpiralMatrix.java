package mathandgeometry;

import java.util.ArrayList;
import java.util.List;

/***Spiral Matrix II (Medium)
 https://leetcode.com/problems/spiral-matrix-ii/

 Spiral Matrix III (Medium)
 https://leetcode.com/problems/spiral-matrix-iii/

 Spiral Matrix IV (Medium)
 https://leetcode.com/problems/spiral-matrix-iv/*/

public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int left = 0, right = matrix[0].length;
        int top = 0, bottom = matrix.length;
        while (left < right && top < bottom) {
            // iterate left to right
            for (int i = left; i < right; i++) {
                res.add(matrix[top][i]);
            }
            top++;
            // iterate top to bottom
            for (int i = top; i < bottom; i++) {
                res.add(matrix[i][right - 1]);
            }
            right--;
            if (!(left < right && top < bottom)) {
                break;
            }
            // iterate right to left
            for (int i = right - 1; i >= left; i--) {
                res.add(matrix[bottom - 1][i]);
            }
            bottom--;
            // iterate bottom to top
            for (int i = bottom - 1; i >= top; i--) {
                res.add(matrix[i][left]);
            }
            left++;
        }
        return res;
    }

    /**
     * Spiral Matrix II
     * https://leetcode.com/problems/spiral-matrix-ii
     */
    public static class SpiralMatrixIi {
    }

    /**
     * Spiral Matrix III
     * https://leetcode.com/problems/spiral-matrix-iii
     */
    public static class SpiralMatrixIii {
    }

    /**
     * Spiral Matrix IV
     * https://leetcode.com/problems/spiral-matrix-iv
     */
    public static class SpiralMatrixIv {
    }
}
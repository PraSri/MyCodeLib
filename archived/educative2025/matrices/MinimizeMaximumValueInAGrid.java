package matrices;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MinimizeMaximumValueInAGrid {
    public static int[][] minimizeMaxValue(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        //flatten the matrix
        List<int[]> cells = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                cells.add(new int[]{grid[i][j], i, j});
            }
        }
        cells.sort(Comparator.comparing(a -> a[0]));
        int[] rowMax = new int[m];
        int[] colMax = new int[n];

        for (int[] cell : cells) {
            int value = cell[0];
            int i = cell[1];
            int j = cell[2];
            int newValue = Math.max(rowMax[i], colMax[j]) + 1;
            grid[i][j] = newValue;
            rowMax[i] = newValue;
            colMax[j] = newValue;
        }
        return grid;
    }
}

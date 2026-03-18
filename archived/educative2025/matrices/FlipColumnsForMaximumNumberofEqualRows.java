package matrices;

import java.util.*;

public class FlipColumnsForMaximumNumberofEqualRows {

    public int maxEqualRowsAfterFlips(int[][] matrix) {
        // Initialize a map to store the frequency of each pattern
        Map<String, Integer> frequencies = new HashMap<>();

        // Iterate through each row in the matrix
        for (int[] row : matrix) {
            StringBuilder pattern = new StringBuilder();

            // Construct a pattern string by comparing each element in the row with the first element
            for (int col = 0; col < row.length; col++) {
                // If the element matches the first element, append "T"
                if (row[0] == row[col]) pattern.append("T");
                    // Otherwise, append "F"
                else pattern.append("F");
            }

            // Update the frequency of the pattern
            frequencies.put(pattern.toString(), frequencies.getOrDefault(pattern.toString(), 0) + 1);
        }

        // Initialize a variable to track the maximum frequency found
        int res = 0;

        // For each entry in frequencies, update res to the larger value between itself and entry’s frequency
        for (int freq : frequencies.values())
            res = Math.max(freq, res);

        // Return res as the final result
        return res;
    }

    // Driver code
    public static void main(String[] args) {
        int[][][] matrices = {{{0, 1}, {1, 0}}, {{0, 0}, {1, 1}}, {{0}, {1}, {0}}, {{0, 1, 0}, {0, 0, 1}, {1, 1, 1}}, {{1, 0}, {1, 1}, {0, 0}, {1, 0}}};

        for (int i = 0; i < matrices.length; i++) {
            System.out.print((i + 1) + ".\tmatrix: [");
            for (int j = 0; j < matrices[i].length; j++) {
                System.out.print("[");
                for (int k = 0; k < matrices[i][j].length; k++) {
                    System.out.print(matrices[i][j][k]);
                    if (k < matrices[i][j].length - 1) System.out.print(", ");
                }
                System.out.print("]");
                if (j < matrices[i].length - 1) System.out.print(", ");
            }
            System.out.println("]");

            FlipColumnsForMaximumNumberofEqualRows sol = new FlipColumnsForMaximumNumberofEqualRows();
            System.out.println("\tMaximum number of rows: " + sol.maxEqualRowsAfterFlips(matrices[i]));
            System.out.println(String.join("", Collections.nCopies(100, "-")));
        }
    }

}

package matrices;

public class Convert1DArrayInto2DArray {

    public int[][] construct2DArray(int[] original, int m, int n) {
        // Check if reshaping is feasible
        // The total number of elements in the 2D array (m * n) must match the length of the 1D array
        if (m * n != original.length) {
            // If reshaping is not possible, return an empty 2D array
            return new int[0][0];
        }

        // Initialize the 2D array with m rows and n columns, filled with zeros
        int[][] result = new int[m][n];

        // Index to track the current position in the original 1D array
        int index = 0;

        // Populate the 2D array with elements from the 1D array
        for (int i = 0; i < m; i++) {  // Iterate over each row
            for (int j = 0; j < n; j++) {  // Iterate over each column in the row
                // Assign the current element from the original array to the 2D array
                result[i][j] = original[index];
                // Move to the next element in the 1D array
                index++;
            }
        }

        // Return the constructed 2D array
        return result;
    }
}

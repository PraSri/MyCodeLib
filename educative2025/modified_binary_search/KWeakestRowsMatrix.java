import java.util.PriorityQueue;
import java.util.Arrays;

public class KWeakestRowsMatrix {
  
    public static int[] findKWeakestRows(int[][] matrix, int k) {
        // Get the number of rows (m) and columns (n) in the matrix
        int m = matrix.length;
        int n = matrix[0].length;

        // Priority queue (min-heap) to store the k weakest rows
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] != a[0] ? b[0] - a[0] : b[1] - a[1]);

        for (int i = 0; i < m; i++) {
            // Find the strength of the row (number of soldiers)
            int strength = binarySearch(matrix[i], n);
            // Add the row to the heap if we haven't yet found k rows or this row is weaker than the current weakest
            pq.offer(new int[]{strength, i});
            // Remove the strongest row if the heap has more than k rows
            if (pq.size() > k) pq.poll();
        }
        
        // Extract the k weakest rows from the heap
        int[] indexes = new int[k];
        // Reverse to get the order from weakest to strongest
        for (int i = k - 1; i >= 0; i--) {
            indexes[i] = pq.poll()[1];
        }

        return indexes;
    }

    // Helper function to perform binary search on each row to count the number of soldiers (1s)
    private static int binarySearch(int[] row, int n) {
        int low = 0, high = n;
        while (low < high) {
            int mid = low + (high - low) / 2;
            // Move right if we find a soldier (1), indicating more soldiers to the right
            if (row[mid] == 1) low = mid + 1;
            else high = mid;
        }
        // low will indicate the number of soldiers in the row
        return low;
    }

    public static void main(String[] args) {
        int[][][] matrixList = {
            {{1, 1, 0, 0, 0}, {1, 1, 1, 1, 0}, {1, 0, 0, 0, 0}, {1, 1, 0, 0, 0}, {1, 1, 1, 1, 1}},
            {{1, 1, 0, 0}, {1, 0, 0, 0}, {1, 1, 1, 1}, {1, 1, 0, 0}},
            {{1, 1}, {1, 1}, {0, 0}, {1, 0}, {1, 1}},
            {{1, 0, 0, 0}, {1, 1, 0, 0}, {1, 1, 1, 1}, {1, 0, 0, 0}},
            {{1, 0, 0}, {0, 0, 0}, {1, 1, 1}, {1, 1, 0}}
        };
        int[] kValues = {2, 3, 3, 2, 1};

        for (int i = 0; i < matrixList.length; i++) {
            System.out.println((i + 1) + ".\tInput matrix: \n\tmatrix = " + Arrays.deepToString(matrixList[i]) + "\n\tk = " + kValues[i]);
            int[] weakestRows = new Solution().findKWeakestRows(matrixList[i], kValues[i]);
            System.out.println("\n\tIndexes of the " + kValues[i] + " weakest rows: " + Arrays.toString(weakestRows));
            System.out.println(new String(new char[100]).replace("\0", "-"));
        }
    }
}

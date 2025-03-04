import java.util.*;

public class KthSmallestPrimeFraction {
    public static int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int n = arr.length;
        PriorityQueue<double[]> minHeap = new PriorityQueue<>(Comparator.comparingDouble(a -> a[0]));

        // Insert the first fraction from each "list" (using denominator index j) into the minHeap.
        for (int j = 1; j < n; j++) {
            // Each fraction is represented as (fraction value, numerator index, denominator index)
            minHeap.offer(new double[]{(double) arr[0] / arr[j], 0, j});
        }

        // Remove the smallest fraction k-1 times.
        for (int i = 0; i < k - 1; i++) {
            double[] top = minHeap.poll();
            int numIdx = (int) top[1];
            int denIdx = (int) top[2];

            // If there is another valid fraction from the same denominator group,
            // push the next fraction from that group into the minHeap.
            if (numIdx + 1 < denIdx) {
                minHeap.offer(new double[]{(double) arr[numIdx + 1] / arr[denIdx], numIdx + 1, denIdx});
            }
        }

        // The kth smallest fraction is now at the top of the minHeap.
        double[] top = minHeap.poll();
        return new int[]{arr[(int) top[1]], arr[(int) top[2]]};
    }

    public static void main(String[] args) {
        int[][] testCases = {
            {1, 3, 5, 7, 9, 11},
            {1, 7, 23, 29, 47},
            {1, 2, 3, 5},
            {1, 2, 3, 5},
            {1, 13, 17, 19, 23, 29, 31}
        };
        int[] ks = {2, 3, 3, 1, 4};

        for (int i = 0; i < testCases.length; i++) {
            System.out.println((i + 1) + ".\tArray: " + Arrays.toString(testCases[i]) + ", k: " + ks[i]);
            int[] result = kthSmallestPrimeFraction(testCases[i], ks[i]);
            System.out.println("\tKth smallest prime fraction is: [" + result[0] + ", " + result[1] + "]");
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}

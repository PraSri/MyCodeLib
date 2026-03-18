package modified_binary_search;

import java.util.Arrays;

public class MinimizeMaxDistanceToGasStation {

    /**
     * You are given an integer array, stations, representing the positions of
     * existing gas stations along the x-axis. You are also given an integer k,
     * indicating the number of additional gas stations you must add.
     * These new gas stations can be placed at any position along the x-axis, including non-integer locations.
     * A penalty is the maximum distance between two adjacent gas stations
     * after placing the k new stations. Your task is to return the smallest
     * possible value of this penalty. An answer is correct if it is within 10^-6
     * TC: O(n * log(m/e)), n = number of stations
     * m = maximum possible initial gap between adjacent stations
     * e = epsilon precision we used to stop the binary search
     * SC: O(1)
     */

    public static double minimizeGasDistance(int[] stations, int k) {
        int n = stations.length;
        if (n < 1 || k < 1) {
            return -1.0;
        }
        double left = 0.0;
        double right = stations[n - 1] - stations[0];
        // precision level for the answer
        /**Point to ponder: Why do we stop the binary search when the difference between the two ends
         * becomes very small (right - left <= 10-6), instead of using the usual condition
         * where the lower bound is less than the upper bound (left < right)?
         In a standard binary search over a discrete sorted array,
         we stop when left > right (or when we find the exact item) because we’ve either found
         the target or exhausted all fixed positions.
         However, in this gas station problem, we’re searching over a
         continuous range of real numbers (the possible maximum gaps), not discrete slots.
         There are infinitely many decimals between any two bounds, so pointers never “cross”
         in the usual sense. Instead, we maintain a numeric interval [left, right] and
         shrink it until the difference between right and left is below the required precision (10^-6).
         At that point, any number inside that tiny interval is effectively
         the same to six decimal places, so we stop.*/
        double epsilon = 1e-6;

        while (right - left > epsilon) {
            double mid = left + (right - left) / 2;
            // check if its possible to distribute the stations at distance=mid and get required stations under k
            if (isPossible(stations, k, mid)) {
                // try smaller value, so narrow down search in left side
                right = mid;
            } else {
                // mid is too small, try bigger values
                left = mid;
            }
        }
        // after narrow down, return the smallest possible penalty formed
        return left;
    }

    /**
     * Helper function to check if it's possible to add atmost k stations
     * so that no two stations are more than 'distance' apart
     */
    private static boolean isPossible(int[] stations, int k, double distance) {
        int requiredStations = 0;
        for (int i = 1; i < stations.length; i++) {
            int gap = stations[i] - stations[i - 1];
            // calculate how many extra stations are needed in this gap between 2 adjacent stations
            requiredStations += (int) (gap / distance);
            // if we need more operations than allowed, then its not possible and return false
            if (requiredStations > k) return false;
        }
        // if we never encountered the limit, its possible
        return true;
    }

    public static void main(String[] args) {
        int[][] testCases = {{1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                {0, 10, 20, 30, 40, 50, 60, 70, 80, 90},
                {0, 3, 8, 14, 25, 27, 35, 47, 58, 72},
                {0, 8, 15, 37, 45, 52, 68, 95, 123, 150, 178, 221, 260, 321, 389, 398, 412, 464, 531, 600},
                {5, 8, 15, 25, 40, 60, 68, 74, 104, 116, 121, 130, 134, 159, 177, 179, 195, 206, 220, 233}};

        int[] kValues = {9, 5, 3, 19, 5};

        for (int i = 0; i < testCases.length; i++) {
            int[] stations = testCases[i];
            int k = kValues[i];
            double result = minimizeGasDistance(stations, k);
            System.out.print((i + 1) + ":\tStations = " + Arrays.toString(stations) + ", k = " + k);
            System.out.println("\n\tMinimum possible penalty = " + String.format("%.5f", result));
            System.out.println("-".repeat(100));
        }
    }
}

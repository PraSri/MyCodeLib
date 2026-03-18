package sort_and_search;

import java.util.*;

/**
 * For those who do not understand why Long Increasing Subsequence (LIS) can solve the problem,
 * I will try to explain based on my understanding.
 * <p>
 * This problem is asking for LIS in two dimensions, width and height.
 * Sorting the width reduces the problem by one dimension. If width is strictly increasing,
 * the problem is equivalent to finding LIS in only the height dimension.
 * However, when there is a tie in width, a strictly increasing sequence in height
 * may not be a correct solution. For example, [[3,3] cannot fit in [3,4]].
 * Sorting height in descending order when there is a tie prevents such a sequence to be included in the solution.
 * <p>
 * The same idea can be applied to problems of higher dimensions.
 * For example, box fitting is three dimensions, width, height, and length.
 * Sorting width ascending and height descending reduces the problem by one dimension.
 * Finding the LIS by height further reduces the problem by another dimension.
 * When find LIS based on only length, it becomes a standard LIS problem.
 */
public class RussianDollEnvelopes {

    private int findPosition(List<Integer> lis, int height) {
        // Initialize the search boundaries
        int left = 0, right = lis.size() - 1;

        // Perform binary search
        while (left <= right) {
            int mid = (left + right) / 2;

            if (lis.get(mid) < height) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // Return the position where height fits
        return left;
    }

    public int maxEnvelopes(int[][] envelopes) {
        // Sort envelopes by width in ascending order
        // If widths are the same, sort by height in descending order
        Arrays.sort(envelopes, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);

        // Initialize an array to store the heights for LIS
        List<Integer> lis = new ArrayList<>();

        // Iterate through the sorted envelopes
        for (int[] envelope : envelopes) {
            int position = findPosition(lis, envelope[1]);

            // If the position equals the current length of the LIS array, extend the array
            if (position == lis.size()) {
                lis.add(envelope[1]);
            }
            // Otherwise, replace the height at the found position to maintain the sequence
            else {
                lis.set(position, envelope[1]);
            }
        }

        // The length of the LIS array represents the maximum number of Russian-dollable envelopes
        return lis.size();
    }

    // Driver code
    public static void main(String[] args) {
        RussianDollEnvelopes solution = new RussianDollEnvelopes();

        int[][][] envelopes = {{{1, 4}, {6, 4}, {9, 5}, {3, 3}}, {{1, 1}, {2, 2}, {3, 3}, {4, 4}, {5, 5}, {6, 6}}, {{4, 4}, {4, 4}, {4, 4}}, {{3, 1}, {5, 8}, {5, 9}, {3, 1}, {9, 1}}, {{9, 8}, {3, 1}, {4, 5}, {2, 1}, {5, 7}}};

        for (int i = 0; i < envelopes.length; i++) {
            System.out.print(i + 1 + ".\tEnvelopes: [");
            for (int j = 0; j < envelopes[i].length; j++) {
                System.out.print(Arrays.toString(envelopes[i][j]));
                if (j < envelopes[i].length - 1) System.out.print(", ");
            }
            System.out.println("]");
            System.out.println("\n\tMaximum number of envelopes which can be Russian-dolled: " + solution.maxEnvelopes(envelopes[i]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}

package sort_and_search;

import java.util.Arrays;

public class CountPairsInTwoArrays {

    public static int countPairs(int[] nums1, int[] nums2) {
        int n = nums1.length;

        // Difference[i] stores nums1[i] - nums2[i]
        int[] difference = new int[n];
        for (int i = 0; i < n; i++) {
            difference[i] = nums1[i] - nums2[i];
        }
        Arrays.sort(difference);

        // Count the number of valid pairs
        int countOfValidPairs = 0;
        for (int i = 0; i < n; i++) {
            // All indexes j following i make a valid pair
            if (difference[i] > 0) {
                countOfValidPairs += n - i - 1;
            } else {
                // Binary search to find the first index j
                // that makes a valid pair with i
                int left = i + 1, right = n - 1;
                while (left <= right) {
                    int mid = left + (right - left) / 2;
                    // If difference[mid] is a valid pair, search in left half
                    if (difference[i] + difference[mid] > 0) {
                        right = mid - 1;
                    }
                    // If difference[mid] does not make a valid pair, search in right half
                    else {
                        left = mid + 1;
                    }
                }
                // After the search left points to the first index j that makes
                // a valid pair with i so we count that and all following indices
                countOfValidPairs += n - left;
            }
        }

        return countOfValidPairs;
    }

    public static void main(String[] args) {
        int[][] nums1Cases = {
                {10, 20, 30},
                {5, 7, 9, 11, 23},
                {5, 5, 5},
                {1},
                {1, 2},
                {3, 5, 1, 7}
        };
        int[][] nums2Cases = {
                {1, 2, 3},
                {9, 11, 20, 44, 24},
                {5, 5, 5},
                {1},
                {2, 1},
                {1, 6, 2, 3}
        };

        for (int i = 0; i < nums1Cases.length; i++) {
            System.out.println((i + 1) + ".\tnums1: " + Arrays.toString(nums1Cases[i]));
            System.out.println("\tnums2: " + Arrays.toString(nums2Cases[i]));
            int result = countPairs(nums1Cases[i], nums2Cases[i]);
            System.out.println("\n\tNumber of valid pairs: " + result);
            System.out.println(new String(new char[100]).replace("\0", "-"));
        }
    }
}
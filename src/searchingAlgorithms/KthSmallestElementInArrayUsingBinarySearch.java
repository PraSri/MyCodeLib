package searchingAlgorithms;

import java.util.Arrays;

public class KthSmallestElementInArrayUsingBinarySearch {

	public static void main(String[] args) {
		System.out.println(kthSmallestElement(new int[] { 2, 5, 8, 9 }, 5));
	}

	// Time Complexity: O(N log(Max – Min)) where Max and Min are the maximum and
	// minimum elements from the array respectively and N is the size of the array.

	public static int kthSmallestElement(int[] a, int k) {

		// find min and max of given array

		int min = Arrays.stream(a).min().getAsInt();
		int max = Arrays.stream(a).max().getAsInt();

		// use modified binary search where start = min and end = max

		return modifiedBinarySearch(a, min, max, k);

	}

	private static int modifiedBinarySearch(int[] a, int s, int e, int k) {

		int n = a.length;
		while (s <= e) {

			int mid = (s + e) / 2;

			int countless = 0, countequal = 0;

			for (int i = 0; i < n; i++) {
				if (a[i] < mid) {
					countless++;
				} else if (a[i] == mid) {
					countequal++;
				}
			}

			// if mid is kth smallest
			/**
			 * ex : 1 2 3 4 5 k = 3 countless = 2 countequal = 1
			 * 
			 */
			if (countless < k && (countequal + countless >= k)) {
				return mid;
			}

			// if required element is less than mid
			/**
			 * ex : 1 2 3 4 5 6 7 8 9 mid = 5 k = 2 countless = 4 countequal = 1 countless
			 * >= k true hence 2 cannot be greater than 5
			 */
			else if (countless >= k) {
				e = mid - 1;
			}

			// if required element is greater than mid
			/**
			 * ex : 1 2 3 4 5 6 7 8 9 mid = 5 k = 8 countless = 4 countequal = 1 countless <
			 * k and countequal + countless < k hence 2 cannot be greater than 5
			 */
			else if (countless < k && countless + countequal < k) {

				s = mid + 1;

			}

		}

		return Integer.MIN_VALUE;
	}

}

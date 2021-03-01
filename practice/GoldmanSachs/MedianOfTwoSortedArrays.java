package GoldmanSachs;

public class MedianOfTwoSortedArrays {

	/***
	 * Time Complexity: O(m + n). To merge both the arrays O(m+n) time is needed.
	 *****/
	// Function to calculate median
	static int getMedian(int ar1[], int ar2[], int n, int m) {

		// Current index of input array ar1[]
		int i = 0;

		// Current index of input array ar2[]
		int j = 0;

		int count;

		int m1 = -1, m2 = -1;

		// Since there are (n+m) elements,
		// There are following two cases
		// if n+m is odd then the middle
		// index is median i.e. (m+n)/2
		if ((m + n) % 2 == 1) {

			for (count = 0; count <= (n + m) / 2; count++) {

//              To merge the both arrays, keep two indices i and j initially assigned to 0.
//				Compare the ith index of 1st array and jth index of second, increase the index of the smallest element 
//				and increase the count.

				if (i != n && j != m) {
					m1 = (ar1[i] > ar2[j]) ? ar2[j++] : ar1[i++];
				} else if (i < n) {
					m1 = ar1[i++];
				} else {
					// for case when j<m,
					m1 = ar2[j++];
				}
			}
			return m1;
		}

		// median will be average of elements
		// at index ((m+n)/2 - 1) and (m+n)/2
		// in the array obtained after merging
		// ar1 and ar2
		else {
			for (count = 0; count <= (n + m) / 2; count++) {
				m2 = m1;
				if (i != n && j != m) {
					m1 = (ar1[i] > ar2[j]) ? ar2[j++] : ar1[i++];
				} else if (i < n) {
					m1 = ar1[i++];
				}

				// for case when j<m,
				else {
					m1 = ar2[j++];
				}
			}
			return (m1 + m2) / 2;
		}
	}

	/**
	 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
	 * Find the median of the two sorted arrays. The overall run time complexity
	 * should be O(log (m+n)).
	 *
	 * Solution Take minimum size of two array. Possible number of partitions are
	 * from 0 to m in m size array. Try every cut in binary search way. When you cut
	 * first array at i then you cut second array at (m + n + 1)/2 - i Now try to
	 * find the i where a[i-1] <= b[j] and b[j-1] <= a[i]. So this i is partition
	 * around which lies the median.
	 *
	 * Time complexity is O(log(min(x,y)) Space complexity is O(1)
	 *
	 * https://leetcode.com/problems/median-of-two-sorted-arrays/
	 * https://discuss.leetcode.com/topic/4996/share-my-o-log-min-m-n-solution-with-explanation/4
	 */

	public double findMedianSortedArrays(int input1[], int input2[]) {
		// if input1 length is greater than switch them so that input1 is smaller than
		// input2.
		if (input1.length > input2.length) {
			return findMedianSortedArrays(input2, input1);
		}
		int x = input1.length;
		int y = input2.length;

		int low = 0;
		int high = x;
		while (low <= high) {
			int partitionX = (low + high) / 2;
			int partitionY = (x + y + 1) / 2 - partitionX;

			// if partitionX is 0 it means nothing is there on left side. Use -INF for
			// maxLeftX
			// if partitionX is length of input then there is nothing on right side. Use
			// +INF for minRightX
			int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : input1[partitionX - 1];
			int minRightX = (partitionX == x) ? Integer.MAX_VALUE : input1[partitionX];

			int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : input2[partitionY - 1];
			int minRightY = (partitionY == y) ? Integer.MAX_VALUE : input2[partitionY];

			if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
				// We have partitioned array at correct place
				// Now get max of left elements and min of right elements to get the median in
				// case of even length combined array size
				// or get max of left for odd length combined array size.
				if ((x + y) % 2 == 0) {
					return ((double) Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2;
				} else {
					return (double) Math.max(maxLeftX, maxLeftY);
				}
			} else if (maxLeftX > minRightY) { // we are too far on right side for partitionX. Go on left side.
				high = partitionX - 1;
			} else { // we are too far on left side for partitionX. Go on right side.
				low = partitionX + 1;
			}
		}

		// Only we we can come here is if input arrays were not sorted. Throw in that
		// scenario.
		throw new IllegalArgumentException();
	}

	// Driver code
	public static void main(String[] args) {
		int ar1[] = { 900, 950 };
		int ar2[] = { 5, 8, 10, 20 };

		int n1 = ar1.length;
		int n2 = ar2.length;

		System.out.println(getMedian(ar1, ar2, n1, n2));
	}
}

package Amazon;

public class FindRotationCountInRotatedSortedArray {

	public static void main(String[] args) {

		int arr[] = { 3,4,5,1,2 };

		System.out.println(findMin(arr));

	}

	/****************
	 * 
	 * 
	 * Consider an array of distinct numbers sorted in increasing order. The array
	 * has been rotated (clockwise) k number of times. Given such an array, find the
	 * value of k.
	 * 
	 * 
	 ****************/

	/**********
	 * SAME SOLUTION FOR =================================== >>> Find the minimum
	 * element in a sorted and rotated array
	 ***************/

	/********** Minimum element index is the ans *****************/

	/*******
	 * O(n) solution is of linear search , simply find minimum element index
	 *******/

	/******** O(log n) solution is of binary search ******************************/

	/****
	 * Minimum element is the element whose previous element is greater , if no
	 * element is before it then no rotation and k=1
	 ******/

	/**** so take mid and check mid-1 and mid+1 and return mid is true ******/

	/*****
	 * otherwise if mid is not minimum then that means min lies either in left side
	 * or right side of mid
	 ***/

	/*******
	 * now check if mid is lesser than last element then min lies in left side
	 ***********/

	/****** else min lies in right side ***********/

	public static int findMin(int[] a) {
		int n = a.length;

		int start = 0;
		int end = n - 1;

		return binarySearch(a, start, end);
	}

	private static int binarySearch(int[] a, int s, int e) {

		int mid;
		if (s == e)
			return s;
		while (s <= e) {
			mid = s + (e - s) / 2;
			if (mid > s && a[mid] < a[mid - 1]) {
				return mid;
			}
			if (a[mid] < a[e]) {
				e = mid;
			} else {
				s = mid + 1;
			}
		}

		return e;
	}

}

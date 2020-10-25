package searchingAlgorithms;

public class SingleElement_in_SortedArray {

	public static void main(String[] args) {
		System.out.println(solve(new int[] { 1, 1, 2, 2, 3, 3, 4, 5, 5, 6, 6 }));
	}

	/***
	 * Given a sorted array of integers A where every element appears twice except
	 * for one element which appears once, find and return this single element that
	 * appears only once.
	 * 
	 */

	public static int getAns(int[] a) {
		int i = 0;
		int j = 1;
		int n = a.length;
		while (i < n - 1) {
			if (a[i] != a[j]) {
				return a[i];
			}
			i += 2;
			j += 2;
		}
		return a[n - 1];
	}

	public static int solve(int[] A) {
		return getAns(A);
	}

}

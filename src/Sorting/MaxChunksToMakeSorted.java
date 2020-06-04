/**
 * 
 */
package Sorting;

/**
 * @author PrakharGuest
 *
 */
public class MaxChunksToMakeSorted {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int[] A = new int[] { 2, 0, 1, 3 };
		System.out.println(solve(A));

	}

	/*
	 * 
	 * 
	 * [0,1,2,3] [2,0,1,3] [2 0 1] [3]
	 * 
	 * [0-n-1]
	 * 
	 * [0, 1, 2, 3, 4] [1, 2, 3, 4, 0]
	 * 
	 * 
	 * 
	 * 
	 */

	public static int solve(int[] A) {
		int n = A.length;
		int chunk = 0;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			max = Math.max(max, A[i]);
			if (max == i) {
				chunk++;
			}
		}

		return chunk;

	}

}

package Arrays;

import java.util.Arrays;

public class WaveArray {

	/*
	 * Given an array of integers A, sort the array into a wave like array and
	 * return it, In other words, arrange the elements into a sequence such that a1
	 * >= a2 <= a3 >= a4 <= a5.....
	 * 
	 * NOTE : If there are multiple answers possible, return the one that's
	 * lexicographically smallest.
	 * 
	 */

	public int[] wave(int[] A) {
		int n = A.length;
		Arrays.sort(A);
		for (int i = 0; i < n; i += 2) {
			if (i > 0 && A[i - 1] > A[i]) {
				int t = A[i - 1];
				A[i - 1] = A[i];
				A[i] = t;
			}
			if (i <= n - 2 && A[i + 1] > A[i]) {
				int t = A[i + 1];
				A[i + 1] = A[i];
				A[i] = t;
			}
		}
		// for(int i = 0;i<n;i++){
		// if(i&1){

		// }else{

		// }
		// }
		return A;
	}

}

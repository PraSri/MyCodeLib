package BitManipulation;

import java.util.Arrays;

public class MinXORValue {

	public static void main(String[] args) {

	}

	/**
	 * Given an integer array A of N integers, find the pair of integers in the
	 * array which have minimum XOR value. Report the minimum XOR value.
	 **/

	public int findMinXor(int[] A) {
		Arrays.sort(A);
		int n = A.length;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < n - 1; i++) {
			if ((A[i] ^ A[i + 1]) < min) {
				min = A[i] ^ A[i + 1];
			}
		}
		return min;
	}

}

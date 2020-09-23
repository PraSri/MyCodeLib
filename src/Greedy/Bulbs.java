package Greedy;

public class Bulbs {

	public static void main(String[] args) {
	}

	/*
	 * https://www.geeksforgeeks.org/count-minimum-right-flips-to-set-all-values-in-
	 * an-array/
	 */
	public int bulbs(int[] A) {

		int n = A.length;

		int res = 0;
		int c = 0;

		for (int i = 0; i < n; i++) {
			if (A[i] == 1 && c % 2 == 0) {
				continue;
			} else if (A[i] == 1 && c % 2 == 1) {
				c++;
				res++;
			} else if (A[i] == 0 && c % 2 == 0) {
				c++;
				res++;
			} else {
				continue;
			}
		}
		return res;
	}

}

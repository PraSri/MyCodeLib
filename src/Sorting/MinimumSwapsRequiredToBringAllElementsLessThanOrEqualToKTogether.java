package Sorting;

public class MinimumSwapsRequiredToBringAllElementsLessThanOrEqualToKTogether {

	public static void main(String[] args) {

		/*
		 * 
		 * A = [1, 12, 10, 3, 14, 10, 5] B = 8
		 * 
		 * c = 3 bad = 4
		 * 
		 * ans = [1,3,5,...]
		 * 
		 */

		System.out.println(solve(new int[] { 1, 12, 10, 3, 14, 10, 5 }, 8));

	}

	/*
	 * First we will find the number of elements which are less than or equal to B.
	 * Let the count comes out to be X. We know that we need at most X-1 swaps to
	 * make all X elements to be consecutive. Maintain a window of X and check that
	 * how many element we need to swap so that all all X elements comes in that
	 * window. We store the minimum of all that and return that.
	 */

	public static int solve(int[] A, int B) {
		int n = A.length;
		int c = 0;
		for (int i = 0; i < n; i++) {
			if (A[i] <= B) {
				c++;
			}
		}
		int bad = 0;
		for (int i = 0; i < c; i++) {
			if (A[i] > B) {
				bad++;
			}

		}
		int ans = bad;
		for (int i = 0, j = c; j < n; i++, j++) {
			if (A[i] > B) {
				bad--;
			}
			if (A[j] > B) {
				bad++;
			}
			ans = Math.min(ans, bad);
		}
		return ans;
	}

}

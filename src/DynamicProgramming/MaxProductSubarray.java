package DynamicProgramming;

public class MaxProductSubarray {

	public static void main(String[] args) {
		System.out.println(maxProduct(new int[] { 4, 2, -5, 1 }));
	}

	/*
	 * Given an integer array A of size N. Find the contiguous subarray within the
	 * given array (containing at least one number) which has the largest product.
	 * 
	 * Return an integer corresponding to the maximum product possible.
	 * 
	 * NOTE: Answer will fit in 32-bit integer value.
	 * 
	 */
	/*
	 * Best solution :
	 * https://leetcode.com/problems/maximum-product-subarray/discuss/48230/Possibly
	 * -simplest-solution-with-O(n)-time-complexity
	 * 
	 */

	public static int maxProduct(final int[] A) {

		int n = A.length;
		int prevMaxPro, curMaxPro, preMinPro;
		preMinPro = A[0];
		prevMaxPro = A[0];
		curMaxPro = A[0];
		int ans = A[0];
		for (int i = 1; i < n; i++) {
			curMaxPro = Math.max(prevMaxPro * A[i], Math.max(preMinPro * A[i], A[i]));
			int curMinPro = Math.min(prevMaxPro * A[i], Math.min(preMinPro * A[i], A[i]));
			ans = Math.max(ans, curMaxPro);
			prevMaxPro = curMaxPro;
			preMinPro = curMinPro;
		}

		return ans;

	}

}

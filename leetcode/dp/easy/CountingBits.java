/**
 * 
 */
package dp.easy;

import java.util.Arrays;


/**
 * @author PrakharGuest
 *
 */
public class CountingBits {

	/**
	 * @param args
	 * 
	 *             Given an integer n, return an array ans of length n + 1 such that
	 *             for each i (0 <= i <= n), ans[i] is the number of 1's in the
	 *             binary representation of i
	 * 
	 *             1. obvious solution is of O(nlogn), find no. of 1's for each
	 *             binary rep 0 to n integer
	 * 
	 *             2. To do in O(n)=>???
	 * 
	 *             n=2 => 0,1,2 => 000, 001, 010 011, 100, 101
	 * 
	 *             2 => 010 4=> 011 8=>111 16=>1111
	 * 
	 * 
	 * 
	 * 
	 */
	public static void main(String[] args) {
		
		System.out.println(Arrays.toString(countBits(4)));
		
	}

	public static int[] countBits(int n) {

		int[] dp = new int[n + 1];

		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 1;

		for (int i = 3; i <= n; i++) {
			dp[i] = dp[i / 2] + i % 2;
		}

		return dp;
	}

}

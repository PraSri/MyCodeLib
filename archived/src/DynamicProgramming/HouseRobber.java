/**
 * 
 */
package DynamicProgramming;

/**
 * https://leetcode.com/problems/house-robber/
 */
public class HouseRobber implements DynamicProgrammingApproches<Integer> {

	/**
	 * You are a professional robber planning to rob houses along a street. Each
	 * house has a certain amount of money stashed, the only constraint stopping you
	 * from robbing each of them is that adjacent houses have security system
	 * connected and it will automatically contact the police if two adjacent houses
	 * were broken into on the same night.
	 * 
	 * Given a list of non-negative integers representing the amount of money of
	 * each house, determine the maximum amount of money you can rob tonight without
	 * alerting the police.
	 */

	public static void main(String[] args) {

		HouseRobber houseRobber = new HouseRobber();
		houseRobber.recursiveRelation(null);

		System.out.println(houseRobber.recursiveTopDown(new Integer[] { 1, 2, 3, 1 }));
		System.out.println(houseRobber.recursiveTopDownMemo(new Integer[] { 1, 2, 3, 1 }));
		System.out.println(houseRobber.iterativeBottomUpMemo(new Integer[] { 1, 2, 3, 1 }));
		System.out.println(houseRobber.iterativeBottomUpNvariables(new Integer[] { 1, 2, 3, 1 }));
	}

	@Override
	public void recursiveRelation(Integer[] inputArray) {

		System.out.println("Robber has 2 options : ");
		System.out.println("1. Rob current house i");
		System.out.println("2. Don't rob current house");
		System.out.println("If option is selected then it cannot rob i-1 and if option 2 "
				+ "is selected then it can rob i-2 and all cumulative loots that follows");

		System.out.println("Calculate what is more profitable");
		System.out.println("Recuurence relation is :");
		System.out.println("rob(i) = max(rob(i-2)+currentValue,rob(i-1))");

	}

	@Override
	public Integer recursiveTopDown(Integer[] inputArray) {
		return rob(inputArray, inputArray.length - 1);
	}

	private Integer rob(Integer[] a, int i) {

		if (i < 0)
			return 0;

		return Math.max(rob(a, i - 2) + a[i], rob(a, i - 1));
	}

	@Override
	public Integer recursiveTopDownMemo(Integer[] inputArray) {
		int[] dp = new int[inputArray.length];
		for (int i = 0; i < inputArray.length; i++) {
			dp[i] = -1;
		}
		return robTD(inputArray, inputArray.length - 1, dp);
	}

	private Integer robTD(Integer[] a, int i, int[] dp) {
		if (i < 0) {
			return 0;
		}
		if (dp[i] != -1) {
			return dp[i];
		}
		dp[i] = Math.max(robTD(a, i - 2, dp) + a[i], robTD(a, i - 1, dp));
		return dp[i];
	}

	@Override
	public Integer iterativeBottomUpMemo(Integer[] inputArray) {
		return robBU(inputArray);
	}

	private Integer robBU(Integer[] a) {
		int n = a.length;
		int[] dp = new int[n + 1];

		dp[0] = 0;
		dp[1] = a[0];

		for (int i = 2; i <= n; i++) {
			dp[i] = Math.max(dp[i - 2] + a[i - 1], dp[i - 1]);
		}

		return dp[n];
	}

	@Override
	public Integer iterativeBottomUpNvariables(Integer[] inputArray) {
		return robBUN(inputArray);
	}

	private Integer robBUN(Integer[] a) {
		int n = a.length;

		int x = 0;
		int y = 0;
		int t;

		for (int i = 1; i < n; i++) {
			t = x;
			x = Math.max(y + a[i - 1], x);
			y = t;
		}

		return x;
	}

}

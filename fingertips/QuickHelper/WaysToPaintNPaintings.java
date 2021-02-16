package QuickHelper;

public class WaysToPaintNPaintings {

	public static void main(String[] args) {
		System.out.println(ways(5, 5));
	}

	/****
	 * Given two integers n and m, where n represent some paintings numbered from 1
	 * to n and m represent some colours 1 to m with unlimited amount. The task is
	 * to find the number of ways to paint the paintings such that no two
	 * consecutive paintings have the same colors.
	 * 
	 * Note: Answer must be calculated in modulo 10^9 +7 as answer can be very
	 * large.
	 * 
	 * https://www.geeksforgeeks.org/ways-to-paint-n-paintings-such-that-adjacent-paintings-dont-have-same-colors/
	 *******/

	/**** m * (m-1)^(n-1) is the actual answer. *****/

	public static int ways(int m, int n) {

		long a = m - 1, b = n - 1, mod = 1000000007;

		long power = ModularPowerInLogN.fastPower(a, b, mod);

		long ans = (m * power) % mod;

		return (int) ans;

	}

}

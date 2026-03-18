package DynamicProgramming;

public class BurstBalloons {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
	 * dp[i][j]: coins obtained from bursting all the balloons between index i and j
	 * (not including i or j) dp[i][j] = max(nums[i] * nums[k] * nums[j] + dp[i][k]
	 * + dp[k][j]) (k in (i+1,j)) If k is the index of the last balloon burst in (i,
	 * j), the coins that burst will get are nums[i] * nums[k] * nums[j], and to
	 * calculate dp[i][j], we also need to add the coins obtained from bursting
	 * balloons between i and k, and between k and j, i.e., dp[i][k] and dp[k][j]
	 */
	public int solve(int[] A) {
		int[] a = new int[A.length + 2];
		int n = 1;

		for (int i : A) {
			if (i > 0) {
				a[n++] = i;
			}
		}

		a[0] = a[n++] = 1;

		int[][] dp = new int[n][n];

		for (int k = 2; k < n; k++) {
			for (int left = 0; left < n - k; left++) {
				int right = left + k;
				for (int i = left + 1; i < right; i++) {
					dp[left][right] = Math.max(dp[left][right], a[left] * a[i] * a[right] + dp[left][i] + dp[i][right]);
				}
			}
		}

		return dp[0][n - 1];
	}
}

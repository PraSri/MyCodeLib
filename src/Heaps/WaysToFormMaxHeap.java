package Heaps;

import java.util.Arrays;

public class WaysToFormMaxHeap {

	public static void main(String[] args) {
		WaysToFormMaxHeap waysToFormMaxHeap = new WaysToFormMaxHeap();
		int x = waysToFormMaxHeap.getLog2n(20);
		System.out.println(x);
		int y = waysToFormMaxHeap.getnCk(5, 2);
		System.out.println(y);
		int z = waysToFormMaxHeap.solve(3);
		System.out.println(z);
	}

	public int getLog2n(int n) {
		return n > 1 ? 1 + getLog2n(n / 2) : 0;
	}

	long[][] nck = new long[105][105];
	long MOD = 1000000007;

	public void initnck() {
		for (int i = 0; i < 105; i++) {
			Arrays.fill(nck[i], -1);
		}
	}

	public long BCof(int n, int k) {
		if (k > n) {
			return 0;
		}
		if (n <= 1) {
			return 1;
		}
		if (k == 0) {
			return 1;
		}

		if (nck[n][k] != -1) {
			return nck[n][k];
		}
		long l1 = BCof(n - 1, k - 1);
		long l2 = BCof(n - 1, k);
		long ans = (l1 % MOD + l2 % MOD) % MOD;
		nck[n][k] = ans % MOD;
		return ans;

	}

	public int getnCk(int n, int k) {

		int[] c = new int[k + 1];

		c[0] = 1;

		for (int i = 1; i <= n; i++) {
			for (int j = Math.min(i, k); j > 0; j--) {
				c[j] = c[j] + c[j - 1];
			}
		}

		return c[k];
	}

	public int getLeft(int n) {
		if (n == 1) {
			return 0;
		}
		int h = getLog2n(n);
		int maxLast = 1 << h;
		int actualLast = n - (maxLast - 1);

		if (actualLast >= maxLast / 2) {
			return maxLast - 1;
		}

		return (maxLast - 1) - (maxLast / 2) + actualLast;
	}

	public int solve(int A) {

		initnck();

		BCof(A, A);

		int[] dp = new int[A + 1];

		dp[0] = dp[1] = 1;

		for (int i = 2; i <= A; i++) {
			int l = (int) (getLeft(i) % MOD);
			dp[i] = (int) ((((int) BCof(i - 1, l) % MOD * dp[l] % MOD) % MOD * dp[i - 1 - l] % MOD) % MOD);
		}

		return (int) (dp[A] % MOD);

	}

}

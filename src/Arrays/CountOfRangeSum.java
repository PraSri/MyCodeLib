package TestPackage;

public class CountOfRangeSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int solve(int[] A, int B, int C) {
		int n = A.length;
		long[] prefixSum = new long[n + 1];

		for (int i = 0; i < n; i++) {
			prefixSum[i + 1] = prefixSum[i] + A[i];
		}

		long ans = 0;

		for (int i = 0; i < n; i++) {
			for (int j = i+1; j <= n; j++) {
				if (prefixSum[j] - prefixSum[i] >= B && prefixSum[j] - prefixSum[i] <= C) {
					ans++;
				}
			}
		}

		return (int) ans;
	}

}

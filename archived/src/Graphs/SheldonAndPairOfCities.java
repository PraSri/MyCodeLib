package Graphs;

public class SheldonAndPairOfCities {

	public static void main(String[] args) {

	}

	/***
	 * 
	 * Sheldon lives in a country with A cities (numbered from 1 to A) and B
	 * bidirectional roads.
	 * 
	 * Roads are denoted by integer array D, E and F of size M, where D[i] and E[i]
	 * denotes the cities and F[i] denotes the distance between the cities.
	 * 
	 * Now he has many lectures to give in the city and is running short of time, so
	 * he asked you C queries, for each query i, find the shortest distance between
	 * city G[i] and H[i].
	 * 
	 * If the two cities are not connected then the distance between them is assumed
	 * to be -1.
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

	final long inf = 1000000000000L;

	public int[] solve(int A, int B, int C, int[] D, int[] E, int[] F, int[] G, int[] H) {
		long[][] dp = new long[205][205];

		for (int i = 0; i < 205; i++) {
			for (int j = 0; j < 205; j++) {
				if (i != j) {
					dp[i][j] = inf;
				} else {
					dp[i][j] = 0;
				}
			}
		}

		for (int i = 0; i < B; i++) {
			int u = D[i] - 1;
			int v = E[i] - 1;
			dp[u][v] = Math.min(dp[u][v], (long) F[i]);
			dp[v][u] = Math.min(dp[v][u], (long) F[i]);
		}

		int[] ans = new int[C];

		for (int k = 0; k < A; k++) {
			for (int i = 0; i < A; i++) {
				for (int j = 0; j < A; j++) {

					if (dp[i][k] + dp[k][j] < dp[i][j]) {
						dp[i][j] = dp[i][k] + dp[k][j];
					}

				}
			}
		}

		for (int i = 0; i < C; i++) {
			int a = G[i] - 1;
			int b = H[i] - 1;
			if (dp[a][b] == inf) {
				ans[i] = -1;
			} else {
				ans[i] = (int) dp[a][b];
			}
		}

		return ans;

	}

}

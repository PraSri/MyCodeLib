package TestPackage;

import java.util.ArrayList;

public class ValuableNodes {

	public static void main(String[] args) {
		ValuableNodes v = new ValuableNodes();
		int x = v.solve(new int[] { 0 }, new int[] { 1 });
		System.out.println(x);
	}

	// A[i] denotes parent of i+1

	public int solve(int[] A, int[] B) {
		int n = A.length;
		ArrayList<Integer>[] ggcList = new ArrayList[n];
		ArrayList<Integer>[] childList = new ArrayList[n];

		for (int i = 0; i < n; i++) {
			ggcList[i] = new ArrayList<Integer>();
			childList[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < n; i++) {
			ggcList[A[A[A[i]]]].add(i);
		}
		for (int i = 0; i < n; i++) {
			childList[A[A[i]]].add(i);
		}

		int[] dp = new int[n];
		dp[0] = B[0];

		dfs(A[0], dp, B, ggcList, childList);

		return dp[n];

	}

	private void dfs(int root, int[] dp, int[] b, ArrayList<Integer>[] ggcList, ArrayList<Integer>[] childList) {
		int ansWithout = 0;
		for (int c : childList[root]) {
			dfs(c , dp, b, ggcList, childList);
			ansWithout += dp[c];
		}

		int ansWith = b[root];
		for (int ggc : ggcList[root]) {
			ansWith += dp[ggc];
		}
		dp[root] = Math.max(ansWithout, ansWith);
	}

}

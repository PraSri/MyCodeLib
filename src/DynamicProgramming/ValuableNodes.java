//package DynamicProgramming;
//
//import java.util.ArrayList;
//
//public class ValuableNodes {
//
//	public static void main(String[] args) {
//		ValuableNodes v = new ValuableNodes();
//		int x = v.solve(new int[] { 0, 1, 2, 3 }, new int[] { 1, 50, 3, 4 });
//		System.out.println(x);
//	}
//
//	// A[i] denotes parent of i+1
//
//	public int solve(int[] A, int[] B) {
//		int n = A.length;
//		if (n == 1) {
//			return B[0];
//		}
//		ArrayList<Integer>[] ggcList = new ArrayList[n + 1];
//		ArrayList<Integer>[] childList = new ArrayList[n + 1];
//
//		for (int i = 0; i < n + 1; i++) {
//			ggcList[i] = new ArrayList<Integer>();
//			childList[i] = new ArrayList<Integer>();
//		}
//
//		for (int i = 1; i < n; i++) {
//			ggcList[A[A[A[i]]]].add(i + 1);
//		}
//		for (int i = 1; i <= n; i++) {
//			int p = A[i];
//			int c = i + 1;
//			childList[p].add(c);
//		}
//
//		int[] dp = new int[n + 1];
//		for (int i = 0; i <= n; i++) {
//			dp[i] = -1;
//		}
//		dp[1] = B[0];
//
//		dfs(A[0], dp, B, ggcList, childList);
//
//		return dp[1];
//
//	}
//
//	private void dfs(int root, int[] dp, int[] b, ArrayList<Integer>[] ggcList, ArrayList<Integer>[] childList) {
//		int ansWithout = 0;
//		for (int c : childList[root]) {
//			dfs(c, dp, b, ggcList, childList);
//			ansWithout += dp[c];
//		}
//
//		int ansWith = b[root];
//		for (int ggc : ggcList[root]) {
//			ansWith += dp[ggc];
//		}
//		dp[root] = Math.max(ansWithout, ansWith);
//	}
//
//}

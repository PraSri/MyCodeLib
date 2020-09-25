package TestPackage;

import java.util.ArrayList;
import java.util.Arrays;

public class MaximumDifferenceOnTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
	 * Given a tree with N nodes labeled from 1 to N.
	 * 
	 * Each node has a certain weight assigned to it given by an integer array A of
	 * size N.
	 * 
	 * Your task is to find the maximum difference in weights of two nodes where one
	 * node is a descendant of the other.
	 * 
	 * NOTE:
	 * 
	 * The tree is rooted at node labeled with 1.
	 * 
	 * 
	 * 
	 * Problem Constraints
	 * 
	 * 2 <= N <= 105
	 * 
	 * -103 <= A[i] <= 103
	 * 
	 * 
	 * 
	 * Input Format
	 * 
	 * First argument is an integer array A of size N denoting the weight of each
	 * node.
	 * 
	 * Second argument is a 2-D array B of size (N-1) x 2 denoting the edge of the
	 * tree.
	 * 
	 * 
	 * 
	 * Output Format
	 * 
	 * Return an single integer denoting the maximum difference in weights of two
	 * nodes where one node is a descendant of the other.
	 */

	public static int ans = Integer.MIN_VALUE;
	public int[][] dp = new int[100009][2];

	public int solve(int[] A, int[][] B) {
		int n = A.length;
		ArrayList<Integer>[] tree = new ArrayList[n + 1];

		for (int i = 0; i <= n; i++) {
			tree[i] = new ArrayList<Integer>();
			Arrays.fill(dp[i], 0);
		}
		for (int i = 0; i < n - 2; i++) {
			int s = B[i][0];
			int d = B[i][1];
			tree[s].add(d);
			tree[d].add(s);
		}

		ans = 0;
		dfs(1, 0, tree, A);

		return ans;

	}

	private void dfs(int i, int j, ArrayList<Integer>[] tree, int[] a) {
		dp[i][0] = dp[i][1] = a[i - 1];
		for (int n : tree[i]) {
			if (n == j)
				continue;
			dfs(n, i, tree, a);
			dp[i][0] = Math.max(dp[i][0], dp[n][0]);
			dp[i][1] = Math.min(dp[i][1], dp[n][1]);
			ans = Math.max(ans, Math.abs(a[i - 1] - dp[n][0]));
			ans = Math.max(ans, Math.abs(a[i - 1] - dp[n][1]));
		}
	}

}

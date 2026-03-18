package Graphs;

import java.util.LinkedList;

public class FirstDepthFirstSearch {

	public static void main(String[] args) {

		int[] a = new int[] { 1, 1, 1, 3, 3, 4, 6, 5, 3, 3 };

		System.out.println(solve(a, 10, 3));

	}

	/*
	 * You are given n towns (1 to n). All towns are connected via unique directed
	 * path as mentioned in the input.
	 * 
	 * Given 2 towns find whether you can reach the first town from the second
	 * without repeating any edge.
	 * 
	 * x y : query to find whether x is reachable from y.
	 * 
	 * Input contains an integer array A of size n and 2 integers x and y ( 1 <= x,
	 * y <= n ).
	 * 
	 * There exist a directed edge from A[i] to i+1 for every 1 <= i < n. Also, it's
	 * guaranteed that A[i] <= i.
	 * 
	 */

	public static int solve(int[] A, final int B, final int C) {
		int n = A.length;
		LinkedList<Integer> adj[] = new LinkedList[n + 1];

		for (int i = 0; i <= n; i++) {
			adj[i] = new LinkedList<>();
		}

		for (int i = 0; i < n; i++) {
			adj[A[i]].add(i + 1);
		}

		boolean[] visited = new boolean[n + 1];

//		int ans = dfs(adj, visited, B, C);
		dfs(adj, visited, B, C);
		return visited[B] ? 1 : 0;
	}

	// we start from s if we find d then return 1

	private static void dfs(LinkedList<Integer>[] adj, boolean[] visited, int d, int s) {
		visited[s] = true;

		for (Integer neighbour : adj[s]) {
			if (!visited[neighbour]) {
				dfs(adj, visited, d, neighbour);
			}
		}

	}

}

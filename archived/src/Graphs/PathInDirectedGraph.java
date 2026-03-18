package Graphs;

import java.util.LinkedList;
import java.util.Queue;

public class PathInDirectedGraph {

	public static void main(String[] args) {
		int p = solve(5, new int[][] { { 1, 2 }, { 4, 1 }, { 2, 4 }, { 3, 4 }, { 5, 2 }, { 1, 3 } });
		System.out.println(p);
	}

	public static int solve(int A, int[][] B) {

		Queue<Integer> q = new LinkedList<>();

		boolean[] visited = new boolean[A + 1];

		int ans = 0;

		LinkedList<Integer> adj[] = new LinkedList[A + 1];

		for (int i = 1; i <= A; i++) {
			adj[i] = new LinkedList<>();
		}

		for (int i = 0; i < B.length; i++) {
			adj[B[i][0]].add(B[i][1]);
		}
//		int k = 0;
//		for (LinkedList<Integer> l : adj) {
//			System.out.println(k++ + " " + l);
//		}

		ans = bfs(A, adj, q, visited);

		return ans;
	}

	private static int bfs(int a, LinkedList<Integer>[] adj, Queue<Integer> q, boolean[] visited) {

		q.add(1);

		visited[1] = true;

		while (!q.isEmpty()) {
			int curr = q.poll();

			LinkedList<Integer> neighbours = adj[curr];

			for (int i : neighbours) {
				if (i == a) {
					return 1;
				}
				if (!visited[i]) {
					visited[i] = true;
					q.add(i);
				}
			}

		}

		return 0;
	}

}

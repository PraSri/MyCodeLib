package Graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class AnotherBFS {

	/*
	 * Given a weighted undirected graph having A nodes, a source node C and
	 * destination node D.
	 * 
	 * Find the shortest distance from C to D and if it is impossible to reach node
	 * D from C then return -1.
	 * 
	 * You are expected to do it in Time Complexity of O(A + M).
	 * 
	 * Note:
	 * 
	 * There are no self-loops in the graph.
	 * 
	 * No multiple edges between two pair of vertices.
	 * 
	 * The graph may or may not be connected.
	 * 
	 * Nodes are Numbered from 0 to A-1.
	 * 
	 * Your solution will run on multiple testcases. If you are using global
	 * variables make sure to clear them.
	 * 
	 */

	public static void main(String[] args) {

		int[][] B = new int[][] { { 2, 5, 1 }, { 1, 3, 1 }, { 0, 5, 2 }, { 0, 2, 2 }, { 1, 4, 1 }, { 0, 1, 1 } };
		System.out.println(solve(6, B, 3, 2));

	}

	public static int solve(int A, int[][] B, int C, int D) {

		if (C == D) {
			return 0;
		}

		int countWt2edge = getcountWt2edge(B);
		int vertexCount = A + countWt2edge;
		@SuppressWarnings("unchecked")
		LinkedList<Integer> adj[] = new LinkedList[vertexCount];

		for (int i = 0; i < vertexCount; i++) {
			adj[i] = new LinkedList<>();
		}

		int gc = A;

		for (int i = 0; i < B.length; i++) {
			if (B[i][2] == 2) {
				adj[B[i][0]].add(gc);
				adj[gc].add(B[i][0]);
				adj[B[i][1]].add(gc);
				adj[gc].add(B[i][1]);
				gc++;
			} else {
				adj[B[i][0]].add(B[i][1]);
				adj[B[i][1]].add(B[i][0]);
			}
		}

		boolean[] visited = new boolean[gc];

		int ans = 0;

		ans = bfs(adj, C, D, visited);

		return ans;

	}

	private static int getcountWt2edge(int[][] b) {

		int ct = 0;

		for (int i = 0; i < b.length; i++) {
			if (b[i][2] == 2) {
				ct++;
			}
		}

		return ct;
	}

	private static int bfs(LinkedList<Integer>[] adj, int s, int d, boolean[] visited) {

		Queue<Integer> q = new LinkedList<>();
		int[] parent = new int[visited.length];
		Arrays.fill(parent, -1);

		q.add(s);
		visited[s] = true;

		int cost;

		while (!q.isEmpty()) {
			int curr = q.poll();
			for (int neighbour : adj[curr]) {
				if (!visited[neighbour]) {
					visited[neighbour] = true;
					parent[neighbour] = curr;
					q.add(neighbour);
				}
			}
		}

		cost = getCost(parent, s, d);

		return cost;
	}

	private static int getCost(int[] parent, int s, int d) {
		int cost = 0;
		int n = parent.length;
		int i = d;
		while (i < n) {
			if (parent[i] != -1) {
				cost++;
			} else if (parent[i] == -1) {
				if (cost == 0) {
					break;
				}
				return cost;
			}
			i = parent[i];
		}
		return -1;
	}

}

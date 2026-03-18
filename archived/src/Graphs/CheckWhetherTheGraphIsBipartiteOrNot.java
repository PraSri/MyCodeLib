package Graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class CheckWhetherTheGraphIsBipartiteOrNot {

	public static void main(String[] args) {

	}

	public int solve(int A, int[][] B) {

		LinkedList<Integer>[] adjList = new LinkedList[A];
		int[] color = new int[A];
		Arrays.fill(color, -1);

		for (int i = 0; i < A; i++) {
			adjList[i] = new LinkedList<Integer>();
		}

		int m = B.length;

		for (int i = 0; i < m; i++) {
			int s = B[i][0];
			int d = B[i][1];

			adjList[s].add(d);
			adjList[d].add(s);
		}

		boolean[] visited = new boolean[A];

		for (int i = 0; i < A; i++) {
			if (!visited[i]) {
				if (!isBipartite(visited, adjList, i, color)) {
					return 0;
				}
			}
		}

		return 1;

	}

	private boolean isBipartite(boolean[] visited, LinkedList<Integer>[] adjList, int i, int[] color) {

		Queue<Integer> q = new LinkedList<Integer>();

		q.add(i);
		visited[i] = true;
		color[i] = 0;
		while (!q.isEmpty()) {
			int t = q.poll();
			for (int neigh : adjList[t]) {
				visited[neigh] = true;
				if (color[neigh] == -1) {
					color[neigh] = 1 - color[t];
					q.add(neigh);
				} else if (color[neigh] == color[t]) {

					return false;

				}

			}
		}

		return true;
	}

}

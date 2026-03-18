package Graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class DistanceOfNearestCell {

	public static void main(String[] args) {

	}

// 	Given a matrix of integers A of size N x M consisting of 0 or 1.

// For each cell of the matrix find the distance of nearest 1 in the matrix.

// Distance between two cells (x1, y1) and (x2, y2) is defined as |x1 - x2| + |y1 - y2|.

// Find and return a matrix B of size N x M which defines for each cell in A distance of nearest 1 in the matrix A.

// NOTE: There is atleast one 1 is present in the matrix.
	
	// Getting TLE

	public int[][] solve(int[][] A) {
		int n = A.length;
		int m = A[0].length;
		int MAX = 1000001;
		LinkedList<Integer>[] adjList = new LinkedList[MAX];
		for (int i = 0; i < MAX; i++) {
			adjList[i] = new LinkedList<Integer>();
		}
		int k = 1;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {

				if (i == n) {
					if (j != m) {
						adjList[k].add(k + 1);
						adjList[k + 1].add(k);
					}
				} else if (j == m) {
					adjList[k].add(k + m);
					adjList[k + m].add(k);
				} else {
					adjList[k].add(k + 1);
					adjList[k + 1].add(k);
					adjList[k].add(k + m);
					adjList[k + m].add(k);
				}

				k++;

			}
		}

		int[] dist = new int[MAX];
		boolean[] visited = new boolean[MAX];

		Arrays.fill(dist, Integer.MAX_VALUE);
		Arrays.fill(visited, false);

		k = 1;
		Queue<Integer> q = new LinkedList<Integer>();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (A[i][j] == 1) {
					dist[k] = 0;
					visited[k] = true;
					q.add(k);
				}
				k++;
			}
		}

		while (!q.isEmpty()) {
			int t = q.poll();
			for (int i : adjList[t]) {
				if (!visited[i]) {
					dist[i] = Math.min(dist[t] + 1, dist[i]);
					q.add(i);
					visited[i] = true;
				}
			}
		}
		k = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {

				A[i][j] = dist[k++];

			}
		}

		return A;

	}

	// Passing all TCs
	public int[][] solve_v2(int[][] A) {
		int[][] matrix = A;

		int m = matrix.length;
		int n = matrix[0].length;

		Queue<int[]> queue = new LinkedList<>();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] == 1) {
					queue.offer(new int[] { i, j });
					matrix[i][j] = 0;
				} else {
					matrix[i][j] = Integer.MAX_VALUE;
				}
			}
		}

		int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

		while (!queue.isEmpty()) {
			int[] cell = queue.poll();
			for (int[] d : dirs) {
				int r = cell[0] + d[0];
				int c = cell[1] + d[1];
				if (r < 0 || r >= m || c < 0 || c >= n || matrix[r][c] <= matrix[cell[0]][cell[1]] + 1)
					continue;
				queue.add(new int[] { r, c });
				matrix[r][c] = matrix[cell[0]][cell[1]] + 1;
			}
		}

		return matrix;
	}

}

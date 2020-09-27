package Graphs;

import java.util.Arrays;
import java.util.LinkedList;

public class Dijsktra {

	public static void main(String[] args) {

	}

	public class Pair {
		public int v, w;

		public Pair(int v, int w) {
			this.v = v;
			this.w = w;
		}

	}

	public int[] solve_v2(int A, int[][] B, int C) {

		int v = A;
		int src = C;
		int[] res = new int[v];

		LinkedList<Pair>[] adjList = new LinkedList[v];

		for (int i = 0; i < v; i++) {
			adjList[i] = new LinkedList<Pair>();
		}

		for (int i = 0; i < B.length; i++) {
			int s = B[i][0];
			int d = B[i][1];
			int w = B[i][2];
			adjList[s].add(new Pair(d, w));
			adjList[d].add(new Pair(s, w));
		}

		res = dijsktra(adjList, src, v);
		for (int i = 0; i < res.length; i++) {
			if (res[i] == Integer.MAX_VALUE)
				res[i] = -1;
		}
		return res;

	}

	private int[] dijsktra(LinkedList<Pair>[] adjList, int src, int v) {
		int[] dist = new int[v];
		boolean[] sptSet = new boolean[v];
		Arrays.fill(dist, Integer.MAX_VALUE);
		Arrays.fill(sptSet, false);
		dist[src] = 0;
		for (int count = 0; count < v - 1; count++) {
			int u = getMinDist(dist, sptSet);
			sptSet[u] = true;
			for (int i = 0; i < v; i++) {
				int nw = getWeight(adjList, u, i);
				if (!sptSet[i] && adjList[i] != null && dist[u] != Integer.MAX_VALUE && dist[u] + nw < dist[i]) {
					dist[i] = dist[u] + nw;
				}

			}
		}
		return dist;
	}

	private int getWeight(LinkedList<Pair>[] adjList, int u, int i) {

		for (Pair p : adjList[u]) {

			if (p.v == i) {
				return p.w;
			}

		}

		return 0;
	}

	// giving MLE : Exception in thread "main" java.lang.OutOfMemoryError: Java heap
	// space
	public int[] solve(int A, int[][] B, int C) {

		int v = A;
		int src = C;
		int[] res = new int[v];
		int[][] graph = new int[v][v];
		for (int i = 0; i < B.length; i++) {
			int s = B[i][0];
			int d = B[i][1];
			int w = B[i][2];
			graph[s][d] = w;
			graph[d][s] = w;
		}
		res = dijsktra(graph, src, v);
		for (int i = 0; i < res.length; i++) {
			if (res[i] == Integer.MAX_VALUE)
				res[i] = -1;
		}
		return res;

	}

	private int[] dijsktra(int[][] graph, int src, int v) {
		int[] dist = new int[v];
		boolean[] sptSet = new boolean[v];
		Arrays.fill(dist, Integer.MAX_VALUE);
		Arrays.fill(sptSet, false);
		dist[src] = 0;

		for (int count = 0; count < v - 1; count++) {
			int u = getMinDist(dist, sptSet);
			sptSet[u] = true;
			for (int i = 0; i < v; i++) {

				if (!sptSet[i] && graph[u][i] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][i] < dist[i]) {
					dist[i] = dist[u] + graph[u][i];
				}

			}
		}
		return dist;
	}

	private int getMinDist(int[] dist, boolean[] sptSet) {
		int min = Integer.MAX_VALUE;
		int minIndex = -1;

		for (int i = 0; i < dist.length; i++) {
			if (!sptSet[i] && dist[i] <= min) {

				min = dist[i];
				minIndex = i;

			}
		}

		return minIndex;
	}

}

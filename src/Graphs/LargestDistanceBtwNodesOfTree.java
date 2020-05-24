package Graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class LargestDistanceBtwNodesOfTree {

	public static void main(String[] args) {
		System.out.println(solve(new int[] { -1, 0 }));
	}

	/* https://www.geeksforgeeks.org/longest-path-undirected-tree/ */

	public static int solve(int[] A) {

		int v = A.length;
		LinkedList<Integer>[] adj = new LinkedList[v];

		init(adj);

		int root = buildGraph(adj, A);

//		for (LinkedList<Integer> l : adj) {
//			System.out.println(l);
//		}

		int[] dist = new int[v];

		int[] t1 = bfs(root, adj, dist);
		int[] t2 = bfs(t1[0], adj, dist);

		return t2[1];

	}

	private static int[] bfs(int root, LinkedList<Integer>[] adj, int[] dist) {

		Arrays.fill(dist, -1);

		Queue<Integer> q = new LinkedList<>();

		q.add(root);

		dist[root] = 0;

		while (!q.isEmpty()) {
			int curr = q.poll();
			if (curr == -1) {

			}
			for (int neighbours : adj[curr]) {
				if (dist[neighbours] == -1) {
					q.add(neighbours);
					dist[neighbours] = dist[curr] + 1;
				}
			}
		}
		int maxNode = 0;
		int maxDist = Integer.MIN_VALUE;
		for (int i = 0; i < dist.length; i++) {
			if (dist[i] > maxDist) {
				maxDist = dist[i];
				maxNode = i;
			}
		}

		int[] ans = new int[2];
		ans[0] = maxNode;
		ans[1] = maxDist;

		return ans;
	}

	private static int buildGraph(LinkedList<Integer>[] adj, int[] a) {
		int root = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] == -1) {
				a[i] = i;
				root = i;
			}
			adj[a[i]].add(i);
			adj[i].add(a[i]);
		}
		return root;
	}

	private static void init(LinkedList<Integer>[] adj) {
		for (int i = 0; i < adj.length; i++) {
			adj[i] = new LinkedList<Integer>();
		}
	}

}

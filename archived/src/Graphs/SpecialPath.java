package Graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class SpecialPath {

// 	Given a graph with N nodes numbered 1 to N and M weighted edges. Given a binary array A of size N. A[i] = 1 if the ith node is special else 0.

// Find the minimum distance of the special path between the 1st and the Nth node. Distance between two nodes is defined as the sum of the weight of edges in the path.

// A special path is a path which visits alteast C non-special nodes and atleast D special nodes.

// NOTE: A node or edge can occur multiple times in a special path. If no such path exists return -1.


	
	public static void main(String[] args) {

		int[] A = new int[] { 0, 1, 0 };
		int[][] B = new int[][] { { 1, 2, 3 }, { 2, 3, 5 } };
		int C = 2;
		int D = 3;

		SpecialPath sp = new SpecialPath();

		int ans = sp.solve(A, B, C, D);
		System.out.println(ans);

	}

	public class Node {
			public int v, w, k1, k2;

			public Node(int v, int w) {
				this.v = v;
				this.w = w;
			}

			public Node(int v, int w, int k1, int k2) {
				this.v = v;
				this.w = w;
				this.k1 = k1;
				this.k2 = k2;
			}

		}

		public int solve(int[] A, int[][] B, int C, int D) {

			int n = A.length;
			int inf = 100001;
			ArrayList<Node>[] adj = new ArrayList[n + 1];

			for (int i = 0; i <= n; i++) {
				adj[i] = new ArrayList<Node>();
			}
			for (int[] a : B) {
				adj[a[0]].add(new Node(a[1], a[2]));
				adj[a[1]].add(new Node(a[0], a[2]));
			}

			int[][][] dp = new int[10001][11][11];

			for (int i = 0; i < 10001; i++) {
				for (int j = 0; j < 11; j++) {
					Arrays.fill(dp[i][j], -1);
				}
			}

//	         dp[1][0][0]=0;
			Comparator<Node> comparator = new Comparator<Node>() {
				@Override
				public int compare(Node n1, Node n2) {
					if (n1.w > n2.w) {
						return 1;
					}
					return -1;
				}
			};
			PriorityQueue<Node> pq = new PriorityQueue<Node>(comparator);
			if (A[0] == 1) {
				dp[1][0][1] = 0;
				pq.add(new Node(1, 0, 0, 1));
			} else {
				dp[1][1][0] = 0;
				pq.add(new Node(1, 0, 1, 0));
			}

			while (!pq.isEmpty()) {
				Node node = pq.poll();
				if (node.k1 >= C && node.k2 >= D && node.v == n) {
					return node.w;
				}
				if (dp[node.v][node.k1][node.k2] != -1 && dp[node.v][node.k1][node.k2] < node.w) {
					continue;
				}

				for (Node curr : adj[node.v]) {
					if (A[curr.v - 1] == 0) {
						int z = Math.min(node.k1 + 1, C);
						if (dp[curr.v][z][node.k2] == -1 || dp[curr.v][z][node.k2] > node.w + curr.w) {
							dp[curr.v][z][node.k2] = node.w + curr.w;
							pq.add(new Node(curr.v, dp[curr.v][z][node.k2], z, node.k2));
						}

					} else {
						int y = Math.min(node.k2 + 1, D);
						if (dp[curr.v][node.k1][y] == -1 || dp[curr.v][node.k1][y] > node.w + curr.w) {
							dp[curr.v][node.k1][y] = node.w + curr.w;

							pq.add(new Node(curr.v, dp[curr.v][node.k1][y], node.k1, y));
						}
					}
				}

			}

			return -1;
		}
}

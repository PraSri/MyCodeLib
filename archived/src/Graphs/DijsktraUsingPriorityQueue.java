package Graphs;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class DijsktraUsingPriorityQueue {

//	https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-in-java-using-priorityqueue/

	public static void main(String[] args) {
		int[][] B = new int[][] { { 2, 4, 10 }, { 3, 4, 1 }, { 3, 6, 1 }, { 1, 2, 4 }, { 4, 5, 6 } };
		DijsktraUsingPriorityQueue d = new DijsktraUsingPriorityQueue();
		int[] ans = d.solve(7, B, 2);
		for (int i : ans) {
			System.out.print(i + " ");
		}
	}

	public class Node implements Comparator<Node> {

		public int v = 0;
		public int w = 0;

		public Node() {

		}

		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public int compare(Node n1, Node n2) {
			if (n1.w > n2.w)
				return 1;
			if (n1.w < n2.w)
				return -1;
			return 0;
		}

//		@Override
//		public int compareTo(Node n) {
//			// TODO Auto-generated method stub
//			return 0;
//		}

	}

	public int[] solve(int A, int[][] B, int C) {

		List<List<Node>> adj = new ArrayList<List<Node>>();

		for (int i = 0; i < A; i++) {
			List<Node> l = new ArrayList<Node>();
			adj.add(l);
		}

		for (int i = 0; i < B.length; i++) {
			int s = B[i][0];
			int d = B[i][1];
			int w = B[i][2];
			adj.get(s).add(new Node(d, w));
			adj.get(d).add(new Node(s, w));
		}

		return dijsktra(adj, C, A);

	}

	private int[] dijsktra(List<List<Node>> adj, int src, int vertices) {

		int[] dist = new int[vertices];
		Set<Integer> set = new HashSet<Integer>();
		Arrays.fill(dist, Integer.MAX_VALUE);
		PriorityQueue<Node> pq = new PriorityQueue<Node>(vertices, new Node());
		dist[src] = 0;
		pq.add(new Node(src, 0));
		while (!pq.isEmpty() && set.size() != vertices) {
			int u = pq.remove().v;
			set.add(u);
			processNeighbours(u, adj, dist, pq, set);
		}
		for (int i = 0; i < dist.length; i++) {
			if (dist[i] == Integer.MAX_VALUE) {
				dist[i] = -1;
			}
		}
		return dist;
	}

	private void processNeighbours(int u, List<List<Node>> adj, int[] dist, PriorityQueue<Node> pq, Set<Integer> set) {

		int ed = -1;
		int nd = -1;

		for (int i = 0; i < adj.get(u).size(); i++) {
			Node v = adj.get(u).get(i);
			if (!set.contains(v.v)) {
				ed = v.w;
				nd = dist[u] + ed;
				if (nd < dist[v.v]) {
					dist[v.v] = nd;
				}
				pq.add(new Node(v.v, dist[v.v]));
			}
		}

	}

}

package Graphs;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class ShortestDistanceInAMaze {

	public ShortestDistanceInAMaze() {

	}

	public static void main(String[] args) {

	}
	/*
	 * Given a matrix of integers A of size N x M describing a maze. The maze
	 * consists of empty locations and walls.
	 * 
	 * 1 represents a wall in a matrix and 0 represents an empty location in a wall.
	 * 
	 * There is a ball trapped in a maze. The ball can go through empty spaces by
	 * rolling up, down, left or right, but it won't stop rolling until hitting a
	 * wall (maze boundary is also considered as a wall). When the ball stops, it
	 * could choose the next direction.
	 * 
	 * Given two array of integers of size B and C of size 2 denoting the starting
	 * and destination position of the ball.
	 * 
	 * Find the shortest distance for the ball to stop at the destination. The
	 * distance is defined by the number of empty spaces traveled by the ball from
	 * the starting position (excluded) to the destination (included). If the ball
	 * cannot stop at the destination, return -1.
	 */

	/*
	 * https://github.com/black-shadows/InterviewBit-Topicwise-Solutions/blob/master
	 * /Graphs/Shortest%20Distance%20in%20a%20Maze.cpp
	 * 
	 */
	public static class Node {
		public int u, v, w;

		public Node(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}

	}

	final static int[][] dirs = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public int solve(int[][] A, int[] B, int[] C) {

		return findMinDist(A, B, C);

	}

	private int findMinDist(int[][] a, int[] b, int[] c) {

		int sx = b[0];
		int sy = b[1];

		int dx = c[0];
		int dy = c[1];

		int n = a.length;
		int m = a[0].length;

		int[][] visited = new int[n][m];

		for (int i = 0; i < n; i++) {
			Arrays.fill(visited[i], Integer.MAX_VALUE);
		}

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

		Node srcNode = new Node(sx, sy, 0);

		pq.add(srcNode);

		while (!pq.isEmpty() && visited[dx][dy] == Integer.MAX_VALUE) {

			Node minNode = pq.remove();
			int x = minNode.u;
			int y = minNode.v;
			int d = minNode.w;
			if (visited[x][y] != Integer.MAX_VALUE) {
				continue;
			} else {
				visited[x][y] = d;
			}

			for (int i = 0; i < 4; i++) {
				int x1 = x;
				int y1 = y;
				int d1 = 0;
				while (true) {
					int nx = x1 + dirs[i][0];
					int ny = y1 + dirs[i][1];
					if (inside(nx, ny, n, m) && a[nx][ny] == 0) {
						x1 = nx;
						y1 = ny;
						d1++;
					} else {
						break;
					}
				}
				if (d1 > 0 && visited[x1][y1] == Integer.MAX_VALUE) {
					pq.add(new Node(x1, y1, d + d1));
				}
			}

		}

		pq.clear();
		int res = visited[dx][dy] != Integer.MAX_VALUE ? visited[dx][dy] : -1;

		return res;

	}

	private boolean inside(int x, int y, int n, int m) {
		if (x >= 0 && x <= n - 1 && y >= 0 && y <= m - 1)
			return true;
		return false;
	}

}

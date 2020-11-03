package Amazon;

import java.util.LinkedList;
import java.util.Queue;

import Graphs.ShortestDistanceInAMaze;

public class ShortestPathFromSrcToDestInBinaryMatrix {

	static ShortestDistanceInAMaze shortestDistanceInAMaze = new ShortestDistanceInAMaze();

	public static void main(String[] args) {
	}

	/***************
	 * The idea is inspired from Lee algorithm and uses BFS.
	 *****************/

	/******
	 * 
	 * this question can be twisted a bit if we introduce some more elements not
	 * only 0 or 1 but suppose 2 also..and you have to reach 2 with only traversing
	 * 1s......Approach is going to be same for all these question......just keep in
	 * mind this BFS approach.......and how we traverse all the 8 directions in
	 * matrix
	 * 
	 ******/

	public static class Point {
		public int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static class Node {
		public int dist;
		public Point point;

		public Node(Point point, int dist) {
			this.dist = dist;
			this.point = point;
		}
	}

	public static int minPath(int[][] mat, int[] src, int[] dest) {

		return bfs(mat, new Point(src[0], src[1]), new Point(dest[0], dest[1]));

	}

	private static int bfs(int[][] mat, Point src, Point dest) {

		if (mat[src.x][src.y] != 1 || mat[dest.x][dest.y] != 1)
			return -1;

		int n = mat.length;
		int m = mat[0].length;

		boolean[][] visited = new boolean[n][m];

		Queue<Node> q = new LinkedList<>();

		visited[src.x][src.y] = true;

		Node s = new Node(src, 0);
		q.add(s);

		int[] rows = { 0, 0, 1, -1 };
		int[] cols = { 1, -1, 0, 0 };

		while (!q.isEmpty()) {

			Node curr = q.poll();
			Point p = curr.point;
			if (p.x == dest.x || p.y == dest.y)
				return curr.dist;

			for (int i = 0; i < 4; i++) {

				int newx = p.x + rows[i];
				int newy = p.y + cols[i];

				if (isValid(newx, newy, n, m) && mat[newx][newy] == 1 && !visited[newx][newy]) {

					visited[newx][newy] = true;

					Node newNode = new Node(new Point(newx, newy), curr.dist + 1);

					q.add(newNode);

				}

			}

		}
		return -1;
	}

	private static boolean isValid(int newx, int newy, int n, int m) {
		return newx >= 0 && newx < n && newy >= 0 && newy < m;
	}

}

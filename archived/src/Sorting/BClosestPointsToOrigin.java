package Sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class BClosestPointsToOrigin {

	public static class Point {
		int x, y, dist;

		/**
		 * @param x
		 * @param y
		 */
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
			this.dist = (x * x) + (y * y);
		}

	}

	public ArrayList<ArrayList<Integer>> solve(ArrayList<ArrayList<Integer>> A, int B) {

		ArrayList<ArrayList<Integer>> a = new ArrayList<>();
		int n = A.size();
		Point[] points = new Point[n];
		for (int i = 0; i < n; i++) {
			ArrayList<Integer> l = A.get(i);
			points[i] = new Point(l.get(0), l.get(1));
		}

		Arrays.sort(points, new Comparator<Point>() {

			@Override
			public int compare(Point p1, Point p2) {
				return p1.dist - p2.dist;
			}

		});

		for (int i = 0; i < B; i++) {
			ArrayList<Integer> l = new ArrayList<>();
			l.add(points[i].x);
			l.add(points[i].y);
			a.add(l);
		}

		return a;
	}

	public static void main(String[] args) {

	}

}

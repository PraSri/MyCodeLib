package Hashing;

import java.util.HashMap;
import java.util.Map;

public class CountRightTriangles {

	public static void main(String[] args) {

	}

	/*
	 * Given two arrays of integers A and B of size N each, where each pair (A[i],
	 * B[i]) for 0 <= i < N represents a unique point (x, y) in 2D Cartesian plane.
	 * 
	 * Find and return the number of unordered triplets (i, j, k) such that (A[i],
	 * B[i]), (A[j], B[j]) and (A[k], B[k]) form a right angled triangle with the
	 * triangle having one side parallel to the x-axis and one side parallel to the
	 * y-axis.
	 * 
	 * 
	 * l1,l2,l3 l1 || x m = 0 l2 || y m = 1 l1 intersect l2
	 * 
	 * 
	 * 
	 * 
	 */

	/*
	 * APPROACH
	 * 
	 * Try fixing each point as the intersection of perpendicular and base and try
	 * finding other points.
	 * 
	 * Once it is fixed, for the other two points, one point will share the same
	 * x-coordinate and the other point will share the same y-coordinate with the
	 * selected point.
	 * 
	 * For points sharing same x or y coordinate we can use map to store the points.
	 * 
	 */

	public static class Point {
		int x;
		int y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static class Line {
		Point p1, p2;

		public Line(Point p1, Point p2) {
			this.p1 = p1;
			this.p2 = p2;
		}

		public int lengthSquare() {
			return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
		}

	}

	public static int solve(int[] A, int[] B) {
		int n = A.length;
		int m = B.length;
		int i = 0, j = 0, k = 0;
		int ct = 0;
		while (i < n && j < n && k < n) {
			j = i + 1;
			k = j + 1;
			Point p1 = new Point(A[i], B[i]);
			Point p2 = new Point(A[j], B[j]);
			Point p3 = new Point(A[k], B[k]);
			Line l1 = new Line(p1, p2);
			Line l2 = new Line(p1, p3);
			Line l3 = new Line(p2, p3);
//			if (isParallelToX(l1) || isParallelToX(l2) || isParallelToX(l3)) {
//				if (isParallelToY(l2) || isParallelToY(l2) || isParallelToY(l3)) {
//					ct++;
//				}
//			}
			if (l1.lengthSquare() + l2.lengthSquare() == l3.lengthSquare()
					|| l3.lengthSquare() + l2.lengthSquare() == l1.lengthSquare()
					|| l1.lengthSquare() + l3.lengthSquare() == l2.lengthSquare()) {
				ct++;
			}
			i++;
			j++;
			k++;
		}
		return ct;
	}

	public static boolean isParallelToX(Line l) {
		if (l.p2.y == l.p1.y) {
			return true;
		}
		return false;
	}

	public static boolean isParallelToY(Line l) {
		if (l.p2.x == l.p1.x) {
			return true;
		}
		return false;
	}

	/* CORRECT SOLUTION */

	public static int solve_v2(int[] A, int[] B) {
		int n = A.length;
		int m = B.length;
		int mod = 1000000007;
		Map<Integer, Integer> ma = new HashMap<>();
		Map<Integer, Integer> mb = new HashMap<>();
		for (int i = 0; i < n; i++) {
			if (!ma.containsKey(A[i])) {
				ma.put(A[i], 1);
			} else {
				ma.put(A[i], ma.get(A[i]) + 1);
			}
		}
		for (int i = 0; i < m; i++) {
			if (!mb.containsKey(B[i])) {
				mb.put(B[i], 1);
			} else {
				mb.put(B[i], mb.get(B[i]) + 1);
			}
		}

		long c = 0;
		for (int i = 0; i < n; i++) {
			c += (ma.get(A[i]) - 1) * (mb.get(B[i]) - 1);
			c %= mod;
		}
		return (int) c;
	}

}

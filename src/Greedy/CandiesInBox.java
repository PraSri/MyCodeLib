package Greedy;

import java.util.ArrayList;
import java.util.Collections;

public class CandiesInBox {

	public static void main(String[] args) {

		int x = solve(new int[] { 81, 19, 42, 70, 79, 56, 38, 106, 59, 47, 16, 65, 93, 34, 112, 37, 57, 29, 114, 107 });
		System.out.println(x);

	}
	/*
	 * There are 2 * N small boxes containing candies denoted by an integer array A
	 * of size 2 * N.
	 * 
	 * There are N-1 big boxes and each big box can accomodate exactly 2 small
	 * boxes.
	 * 
	 * So, we can use exactly 2 * (N-1) small boxes.
	 * 
	 * The cost of each big box is the absolute difference of candies in that box.
	 * 
	 * For example: A big box contain two small boxes with candies 4 and 6 then the
	 * cost of this big box will be | 4 - 6 | = 2.
	 * 
	 * You have to fill all the big boxes such that the total cost of big boxes are
	 * minimized.
	 * 
	 * Find and return the minimum total cost of big boxes.
	 * 
	 * A = [2, 4, 1, 10, 6, 15] ans = 3 81, 19, 42, 70, 79, 56, 38, 106, 59, 47, 16,
	 * 65, 93, 34, 112, 37, 57, 29, 114, 107 ans = 25
	 */

	public static int solve(int[] A) {

		int ns = A.length;
		int min = Integer.MAX_VALUE;
		int cost = 0;
		int[] flag = new int[ns];
		for (int i = 0; i < ns; i++) {
			for (int j = i + 1; j < ns; j++) {
				flag[i] = 1;
				flag[j] = 1;
				cost = findMinCost(A, flag);
				min = Math.min(min, cost);
				flag[i] = 0;
				flag[j] = 0;
			}
		}
		return min;

	}

	private static int findMinCost(int[] a, int[] flag) {
		ArrayList<Integer> l = new ArrayList<Integer>();
		for (int i = 0; i < a.length; i++) {
			if (flag[i] == 0) {
				l.add(a[i]);
			}
		}
		Collections.sort(l);
		int cost = 0;
		for (int i = 1; i < l.size(); i += 2) {
			cost += Math.abs(l.get(i - 1) - l.get(i));
		}
		return cost;
	}

}

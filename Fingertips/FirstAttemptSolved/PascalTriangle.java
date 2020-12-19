package FirstAttemptSolved;

import java.util.ArrayList;

public class PascalTriangle {

	public static void main(String[] args) {

	}

	/****
	 * 
	 * https://www.interviewbit.com/problems/pascal-triangle/
	 * 
	 * Given numRows, generate the first numRows of Pascal’s triangle.
	 * 
	 * Pascal’s triangle : To generate A[C] in row R, sum up A’[C] and A’[C-1] from
	 * previous row R - 1.
	 * 
	 * Example:
	 * 
	 * Given numRows = 5,
	 * 
	 * Return
	 * 
	 * [ [1], [1,1], [1,2,1], [1,3,3,1], [1,4,6,4,1] ]
	 * 
	 **************/

	public ArrayList<ArrayList<Integer>> mySolution(int A) {
		int n = A;
		ArrayList<ArrayList<Integer>> a = new ArrayList<>();
		if (n == 0)
			return a;
		ArrayList<Integer> l1 = new ArrayList<>();
		l1.add(1);
		a.add(l1);
		if (n == 1)
			return a;
		ArrayList<Integer> l2 = new ArrayList<>();
		l2.add(1);
		l2.add(1);
		a.add(l2);
		if (n == 2)
			return a;
		ArrayList<Integer> l3 = new ArrayList<>();
		l3.add(1);
		l3.add(2);
		l3.add(1);
		a.add(l3);
		if (n == 3)
			return a;
		int p = 3;
		for (int i = 4; i <= n; i++) {// n=4
			ArrayList<Integer> l = new ArrayList<>();
			for (int j = 0; j < i; j++) {
				if (j == 0 || j == i - 1) {
					l.add(1);
				} else {
					int k = a.get(p - 1).get(j) + a.get(p - 1).get(j - 1);
					l.add(k);
				}
			}
			a.add(l);
			p++;
		}
		return a;
	}

	public ArrayList<ArrayList<Integer>> editorialSolution(int A) {
		int numRows = A;
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();

		if (A == 0)
			return res;

		res.add(new ArrayList<>());
		res.get(0).add(1);

		for (int i = 1; i < numRows; i++) {

			res.add(new ArrayList<>());

			res.get(i).add(1);

			for (int j = 0; j < i - 1; j++) {
				int num = res.get(i - 1).get(j) + res.get(i - 1).get(j + 1);
				res.get(i).add(num);
			}

			res.get(i).add(1);

		}

		return res;
	}

}

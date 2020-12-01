package BacktrackingRecursion;

import java.util.ArrayList;

public class Combinations {

	/*
	 * Given two integers A and B, return all possible combinations of B numbers out
	 * of 1 2 3 ... A .
	 * 
	 * Make sure the combinations are sorted.
	 * 
	 * To elaborate,
	 * 
	 * Within every entry, elements should be sorted. [1, 4] is a valid entry while
	 * [4, 1] is not. Entries should be sorted within themselves.
	 */

	public static void main(String[] args) {

		for (ArrayList<Integer> a : combine(5, 3)) {
			System.out.println(a);
		}

	}

	public static ArrayList<ArrayList<Integer>> combine(int A, int B) {

		ArrayList<ArrayList<Integer>> results = new ArrayList<>();

		solve(results, new ArrayList<Integer>(), 1, A, B);

		return results;

	}

	private static void solve(ArrayList<ArrayList<Integer>> results, ArrayList<Integer> comb, int s, int n, int k) {

		if (k == 0) {
			results.add(new ArrayList<>(comb));
			return;
		}

		for (int i = s; i <= n - k + 1; i++) {
			comb.add(i);
			solve(results, comb, i + 1, n, k - 1);
			comb.remove(comb.size() - 1);
		}

	}

}

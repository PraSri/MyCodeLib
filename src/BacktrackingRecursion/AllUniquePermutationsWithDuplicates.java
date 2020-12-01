package BacktrackingRecursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AllUniquePermutationsWithDuplicates {

	/***********
	 * 
	 * 
	 * Given an array A of size N denoting collection of numbers that might contain
	 * duplicates, return all possible unique permutations. NOTE: No 2 entries in
	 * the permutation sequence should be the same.
	 * 
	 *************/

	public static void main(String[] args) {

		for (ArrayList<Integer> a : permute(new ArrayList<>(Arrays.asList(1, 2, 3)))) {
			for (int x : a) {
				System.out.print(x);
			}
			System.out.println();
		}

	}

	public static ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> A) {
		ArrayList<ArrayList<Integer>> l = new ArrayList<>();
		ArrayList<Integer> sl = new ArrayList<>();
		boolean[] visited = new boolean[A.size()];
//		permuteUtil(l, sl, A);
		Collections.sort(A);
		permuteUtil(l, sl, A, visited);
		return l;
	}

	private static void permuteUtil(ArrayList<ArrayList<Integer>> l, List<Integer> sl, ArrayList<Integer> a) {

		if (sl.size() == a.size()) {
			l.add(new ArrayList<>(sl));
		}

		for (int i = 0; i < a.size(); i++) {
			if (sl.contains(a.get(i)))
				continue;
			sl.add(a.get(i));
			permuteUtil(l, sl, a);
			sl.remove(sl.size() - 1);
		}

	}

	private static void permuteUtil(ArrayList<ArrayList<Integer>> l, List<Integer> sl, ArrayList<Integer> a,
			boolean[] visited) {

		if (sl.size() == a.size()) {
			l.add(new ArrayList<>(sl));
		}

		for (int i = 0; i < a.size(); i++) {
			if (visited[i]) {
				continue;
			}
			if (i > 0 && a.get(i - 1) == a.get(i) && !visited[i - 1]) {
				continue;
			}
			visited[i] = true;
			sl.add(a.get(i));
			permuteUtil(l, sl, a, visited);
			visited[i] = false;
			sl.remove(sl.size() - 1);
		}

	}

}

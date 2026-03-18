package BacktrackingRecursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CombinationSumWithDistinctIntegersButDuplicateOutput {

	public static void main(String[] args) {

		for (ArrayList<Integer> a : combinationSum(new ArrayList<>(Arrays.asList(2, 3, 6, 7)), 7)) {
			for (int x : a) {
				System.out.print(x + ", ");
			}
			System.out.println();
		}

	}

	/*****
	 * Given an array of distinct integers candidates and a target integer target,
	 * return a list of all unique combinations of candidates where the chosen
	 * numbers sum to target. You may return the combinations in any order.
	 * 
	 * The same number may be chosen from candidates an unlimited number of times.
	 * Two combinations are unique if the frequency of at least one of the chosen
	 * numbers is different.
	 * 
	 * It is guaranteed that the number of unique combinations that sum up to target
	 * is less than 150 combinations for the given input.
	 * 
	 * 
	 * Input: candidates = [2,3,6,7], target = 7 Output: [[2,2,3],[7]] Explanation:
	 * 2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple
	 * times. 7 is a candidate, and 7 = 7. These are the only two combinations. *
	 * 
	 *******/

	public static ArrayList<ArrayList<Integer>> combinationSum(ArrayList<Integer> a, int b) {

		Collections.sort(a);

		ArrayList<ArrayList<Integer>> res = new ArrayList<>();

		ArrayList<Integer> l = new ArrayList<>();

		combinationSumUtil(a, b, l, res, 0);

		return res;

	}

	private static void combinationSumUtil(ArrayList<Integer> a, int b, ArrayList<Integer> l,
			ArrayList<ArrayList<Integer>> res, int i) {
		if (b == 0) {
			res.add(new ArrayList<>(l));
			return;
		}

		if (b < 0) {
			return;
		}

		for (int cur = i; cur < a.size(); cur++) {

			l.add(a.get(cur));

			// backtrack

			combinationSumUtil(a, b - a.get(cur), l, res, cur); // not cur+1 because we can use same elements

			l.remove(l.size() - 1);

		}

	}

}

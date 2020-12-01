package BacktrackingRecursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CombinationSumWithDuplicatesIntegersButNoDuplicatesInOutput {

	public static void main(String[] args) {

		for (ArrayList<Integer> a : combinationSum(new ArrayList<>(Arrays.asList(1, 1, 2, 5, 6, 7, 10)), 8)) {
			for (int x : a) {
				System.out.print(x);// 10, 1, 2, 7, 6, 1, 5
			}
			System.out.println();// 1, 1, 2, 5, 6, 7, 10
		}

	}

	/*******************************/

	/*****
	 * Given a collection of candidate numbers (candidates) and a target number
	 * (target), find all unique combinations in candidates where the candidate
	 * numbers sum to target.
	 * 
	 * Each number in candidates may only be used once in the combination.
	 * 
	 * Note: The solution set must not contain duplicate combinations.
	 *
	 * 
	 * 
	 * Input: candidates = [2,5,2,1,2], target = 5 Output: [ [1,2,2], [5] ]
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

			// if duplicates don't repeat

			/**
			 * 
			 * i>curr means position curr has been processed and we have found all the
			 * combinations starting from position curr by doing dfs. Therefore if
			 * cand[curr+1]==cand[curr] there is no need to process position curr+1 as it
			 * will provide combinations which have already been found by dfs from position
			 * curr hence we skip the step to avoid duplicate combinations.
			 * 
			 ****/
			if (cur > i && a.get(cur) == a.get(cur - 1)) {
				continue;
			}

			l.add(a.get(cur));

			// backtrack

			combinationSumUtil(a, b - a.get(cur), l, res, cur + 1);

			l.remove(l.size() - 1);

		}

	}

}

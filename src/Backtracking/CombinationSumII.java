package Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CombinationSumII {

	public static void main(String[] args) {

		for (ArrayList<Integer> a : combinationSum(new ArrayList<>(Arrays.asList(10, 1, 2, 7, 6, 1, 5)), 8)) {
			System.out.println(a);
		}
	}

	/*
	 * Given an array of size N of candidate numbers A and a target number B. Return
	 * all unique combinations in A where the candidate numbers sums to B.
	 * 
	 * Each number in A may only be used once in the combination.
	 * 
	 * Note:
	 * 
	 * All numbers (including target) will be positive integers. Elements in a
	 * combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤
	 * … ≤ ak). The solution set must not contain duplicate combinations.
	 * 
	 * *********NEED TO UNDERSTAND SOLUTION*****************
	 */

	/*
	 * *********SOLUTION****************
	 * https://leetcode.com/problems/combination-sum-ii/discuss/16861/Java-solution-
	 * using-dfs-easy-understand
	 */

	public static ArrayList<ArrayList<Integer>> combinationSum(ArrayList<Integer> a, int b) {

		ArrayList<ArrayList<Integer>> res = new ArrayList<>();

		ArrayList<Integer> list = new ArrayList<>();

		int index = 0;

		Collections.sort(a);

		backtrack(a, index, list, res, b);

		return res;

	}

	private static void backtrack(ArrayList<Integer> a, int index, ArrayList<Integer> list,
			ArrayList<ArrayList<Integer>> res, int sum) {

		if (sum == 0) {
			res.add(new ArrayList<>(list));
			return;
		}
		if (sum < 0) {
			return;
		}
		for (int i = index; i < a.size(); i++) {

			if (i > index && a.get(i - 1) == a.get(i)) {
				continue;
			}

			list.add(a.get(i));

			backtrack(a, i + 1, list, res, sum - a.get(i));

			list.remove(list.size() - 1);

		}

	}

}

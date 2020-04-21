package Recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CombinationSum {

	public static void main(String[] args) {

		for (ArrayList<Integer> a : combinationSum(new ArrayList<>(Arrays.asList(1, 1, 2, 5, 6, 7, 10)), 8)) {
			for (int x : a) {
				System.out.print(x);// 10, 1, 2, 7, 6, 1, 5
			}
			System.out.println();// 1, 1, 2, 5, 6, 7, 10
		}

	}

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

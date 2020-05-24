package Backtracking;

import java.util.ArrayList;

public class PalindromePartitioning {

	public static void main(String[] args) {

		for (ArrayList<String> a : partition("aab")) {
			System.out.println(a);
		}

	}

	/*
	 * https://leetcode.com/problems/palindrome-partitioning/discuss/41963/Java%3A-
	 * Backtracking-solution.
	 */

	public static ArrayList<ArrayList<String>> partition(String a) {

		ArrayList<ArrayList<String>> res = new ArrayList<>();

		ArrayList<String> list = new ArrayList<>();

		int index = 0;

		backtrack(a, index, list, res);

		return res;

	}

	private static void backtrack(String s, int index, ArrayList<String> list, ArrayList<ArrayList<String>> res) {

		if (index == s.length()) {
			res.add(new ArrayList<>(list));
		} else {
			for (int i = index; i < s.length(); i++) {

				if (isPalindrome(s, index, i)) {
					list.add(s.substring(index, i + 1));
					backtrack(s, i + 1, list, res);
					list.remove(list.size() - 1);
				}

			}
		}

	}

	private static boolean isPalindrome(String s, int i, int j) {

		while (i < j) {
			if (s.charAt(i++) != s.charAt(j--)) {
				return false;
			}
		}

		return true;
	}

}

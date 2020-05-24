package Backtracking;

import java.util.ArrayList;

public class LetterPhone {

	public static void main(String[] args) {
		System.out.println(letterCombinations("24"));
	}

	private static String[] keys = new String[] { "0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

	public static ArrayList<String> letterCombinations(String A) {

		ArrayList<String> sl = new ArrayList<>();

		if (A == null || A.length() == 0)
			return sl;

		recSolve("", A, 0, sl);

		return sl;

	}

	private static void recSolve(String t, String s, int i, ArrayList<String> sl) {
		// base case
		if (i >= s.length()) {
			sl.add(t);
			return;
		}

		String letters = keys[(s.charAt(i)) - '0'];

		for (int j = 0; j < letters.length(); j++) {
			recSolve(t + letters.charAt(j), s, i + 1, sl);
		}
	}

}

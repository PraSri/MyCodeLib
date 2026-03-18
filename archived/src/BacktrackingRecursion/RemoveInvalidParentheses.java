package BacktrackingRecursion;

import java.util.ArrayList;

public class RemoveInvalidParentheses {

	public static void main(String[] args) {

		System.out.println(solve("()())()"));

	}

	/*
	 * Given a string A consisting of lowercase English alphabets and parentheses
	 * '(' and ')'. Remove the minimum number of invalid parentheses in order to
	 * make the input string valid.
	 * 
	 * Return all possible results.
	 * 
	 * You can return the results in any order.
	 * 
	 * ********NEED TO UNDERSTAND SOLUTION************
	 * https://leetcode.com/problems/remove-invalid-parentheses/discuss/75027/Easy-
	 * Short-Concise-and-Fast-Java-DFS-3-ms-solution
	 * 
	 */

	public static ArrayList<String> solve(String A) {

		ArrayList<String> res = new ArrayList<>();

		char[] c = new char[] { '(', ')' };

		dfs(A, res, c, 0, 0);

		return res;

	}

	private static void dfs(String s, ArrayList<String> res, char[] c, int lasti, int lastj) {
		int ct = 0;
		int i = lasti;
		while (i < s.length() && ct >= 0) {
			if (s.charAt(i) == c[0])
				ct++;
			if (s.charAt(i) == c[1])
				ct--;
			i++;
		}

		if (ct >= 0) {
//			extra ')' so detect by reversing
			String rev = new StringBuffer(s).reverse().toString();
			if (c[0] == '(') {
				dfs(rev, res, new char[] { ')', '(' }, 0, 0);
			} else {
				res.add(rev);
			}
		} else {

//			extra '(' 
			i = i - 1;
			for (int j = lastj; j <= i; j++) {
				if (s.charAt(j) == c[1] && (j == lastj || s.charAt(j - 1) != c[1])) {
					dfs(s.substring(0, j) + s.substring(j + 1, s.length()), res, c, i, j);
				}
			}

		}
	}

}

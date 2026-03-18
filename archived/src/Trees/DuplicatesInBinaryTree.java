package Trees;

import java.util.HashSet;
import java.util.Set;

public class DuplicatesInBinaryTree {

	public static void main(String[] args) {
	}

	static char SPLSYM = '#';

	public int solve(TreeNode A) {

		Set<String> set = new HashSet<String>();
		return helper(A, set) == "" ? 1 : 0;

	}

	private String helper(TreeNode root, Set<String> set) {

		String s = "";

		if (root == null)
			return s + SPLSYM;

		String sl = helper(root.left, set);
		if (sl.equals(s)) {
			return s;
		}
		String sr = helper(root.right, set);
		if (sr.equals(s)) {
			return s;
		}

		s = s + root.val + sl + sr;

		if (s.length() > 3 && set.contains(s)) {
			return "";
		}

		set.add(s);

		return s;
	}

}

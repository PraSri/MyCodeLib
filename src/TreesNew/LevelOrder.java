package TreesNew;

import java.util.ArrayList;

public class LevelOrder {

	public static void main(String[] args) {

	}

	/*** $$$$$$ TRY this using queue $$$$$$$$$$$$ */

	public static int height(TreeNode root) {
		if (root == null) {
			return 0;
		}

		int ls = height(root.left);
		int rs = height(root.right);

		return Math.max(ls, rs) + 1;
	}

	public static void printKthLevel(TreeNode node, int k, ArrayList<Integer> a) {
		if (node == null) {
			return;
		}

		if (k == 1) {
			a.add(node.val);
		}

		printKthLevel(node.left, k - 1, a);
		printKthLevel(node.right, k - 1, a);

		return;

	}

	public static ArrayList<Integer> getprintKthLevel(TreeNode node, int k) {

		ArrayList<Integer> a = new ArrayList<>();
		printKthLevel(node, k, a);

		return a;

	}

	public static ArrayList<ArrayList<Integer>> printAllLevels(TreeNode root) {
		int h = height(root);
		ArrayList<ArrayList<Integer>> aa = new ArrayList<>();
		for (int i = 1; i <= h; i++) {
			aa.add(getprintKthLevel(root, i));
		}
		return aa;
	}

	public ArrayList<ArrayList<Integer>> levelOrder(TreeNode A) {
		return printAllLevels(A);
	}
}

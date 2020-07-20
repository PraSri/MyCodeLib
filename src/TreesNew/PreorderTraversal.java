package Tree;

import java.util.ArrayList;
import java.util.Stack;

public class PreorderTraversal {

	public static void main(String[] args) {

	}

	public static int[] preorderTraversal(TreeNode A) {
		Stack<TreeNode> s = new Stack<>();
		ArrayList<Integer> a = new ArrayList<>();

		TreeNode curr = A;

		while (curr != null || s.size() > 0) {
			if (curr != null) {
				a.add(curr.val);
				s.push(curr);
				curr = curr.left;
			} else {
				TreeNode t = s.pop();
				curr = t.right;
			}
		}
		int i = 0;
		int[] r = new int[a.size()];
		for (int x : a) {
			r[i++] = x;
		}
		return r;
	}

}

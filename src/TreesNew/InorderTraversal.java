package Tree;

import java.util.ArrayList;
import java.util.Stack;

public class InorderTraversal {

	public static void main(String[] args) {

	}

	public static int[] inorderTraversal(TreeNode A) {

		Stack<TreeNode> s = new Stack<>();
		ArrayList<Integer> a = new ArrayList<>();
		/*
		 * put the curr root in stack curr -> curr.left till left then pop if not empty
		 * 
		 */

		TreeNode curr = A;

		while (curr != null || s.size() > 0) {
			while (curr != null) {
				s.push(curr);
				curr = curr.left;
			}
			curr = s.pop();
			a.add(curr.val);
			curr = curr.right;
		}
		int i = 0;
		int[] r = new int[a.size()];
		for (int x : a) {
			r[i++] = x;
		}
		return r;
	}

}

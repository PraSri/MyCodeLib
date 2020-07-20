package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class PostorderTraversal {

	public static void main(String[] args) {
	}

	public static int[] postorderTraversal(TreeNode A) {
		Stack<TreeNode> s = new Stack<>();
		ArrayList<Integer> a = new ArrayList<>();
		TreeNode curr = A;
		while (curr != null || !s.isEmpty()) {
			if (curr != null) {
				s.add(curr);
				curr = curr.left;
			} else {
				TreeNode t = s.peek().right;
				if (t == null) {
					t = s.pop();
					a.add(t.val);
					while (!s.isEmpty() && t == s.peek().right) {
						t = s.pop();
						a.add(t.val);
					}
				} else {
					curr = t;
				}
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

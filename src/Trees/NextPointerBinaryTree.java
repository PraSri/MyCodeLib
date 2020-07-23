package Trees;

import java.util.LinkedList;
import java.util.Queue;

public class NextPointerBinaryTree {

	public static void main(String[] args) {

	}

	public void connect(TreeLinkNode root) {

		Queue<TreeLinkNode> q = new LinkedList<TreeLinkNode>();

		q.add(root);

		TreeLinkNode temp = null;

		while (!q.isEmpty()) {
			int size = q.size();

			for (int i = 0; i < size; i++) {

				TreeLinkNode prev = temp;
				temp = q.poll();

				if (i > 0) {
					prev.next = temp;
				}

				if (temp.left != null) {
					q.add(temp.left);
				}

				if (temp.right != null) {
					q.add(temp.right);
				}

			}

			temp.next = null;

		}

	}

}

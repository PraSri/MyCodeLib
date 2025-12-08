package trees;

import java.util.*;

public class MaximumDepthOfBinaryTree {

    //Time complexity:
    //O(n)
    //Space complexity:
    //O(h)
    //Best Case (balanced tree):
    //O(log(n))
    //Worst Case (degenerate tree):
    //O(n)
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    public int maxDepthBfs(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        if (root != null) {
            q.add(root);
        }
        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }
            level++;
        }
        return level;
    }

}

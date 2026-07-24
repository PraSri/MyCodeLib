import java.util.LinkedList;
import java.util.Queue;

public class Trees {

    // invert a binary tree
    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return root;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        root.left = invertTree(root.left);
        root.right = invertTree(root.right);
        return root;
    }

    // max depth of binary tree
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    public int maxDepthBfs(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            level++;
        }
        return level;
    }

    // diameter of binary tree
    public int diameter(TreeNode root) {
        int[] res = new int[1];
        dfsDia(root, res);
        return res[0];
    }

    private int dfsDia(TreeNode root, int[] res) {
        if (root == null) {
            return 0;
        }
        int left = dfsDia(root.left, res);
        int right = dfsDia(root.right, res);
        res[0] = Math.max(res[0], left + right);
        return 1 + Math.max(left, right);
    }

    // Balanced binary tree
    // int[] : 0th index -> isBalanced(0,1)
    // 1st index -> height
    public boolean isBalanced(TreeNode root) {
        return dfsBal(root)[0] == 1;
    }

    private int[] dfsBal(TreeNode root) {
        if (root == null) {
            return new int[]{1, 0};
        }
        int[] left = dfsBal(root.left);
        int[] right = dfsBal(root.right);
        boolean balanced = (left[0] == 1 && right[0] == 1) && (Math.abs(left[1] - right[1]) <= 1);
        int height = 1 + Math.max(left[1], right[1]);
        return new int[]{balanced ? 1 : 0, height};
    }

    static class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;

        public TreeNode() {

        }

        public TreeNode(TreeNode left, TreeNode right, int val) {
            this.left = left;
            this.right = right;
            this.val = val;
        }

        public TreeNode(int val) {
            this.val = val;
        }
    }
}

package tree_dfs;

public class MaxDepth {
    public static int findMaxDepth(TreeNode<Integer> root) {

        if (root == null) return 0;
        return 1 + Math.max(findMaxDepth(root.left), findMaxDepth(root.right));
    }
}

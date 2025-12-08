package trees;

public class DiameterOfBinaryTree {

    public int diameterOfBinaryTree(TreeNode root) {
        if(true) {
            int[] res = new int[1];
            dfs(root, res);
            return res[0];
        }
        if(root == null) {
            return 0;
        }
        // 3 cases
        int case1 = height(root.left) + height(root.right);
        int case2 = diameterOfBinaryTree(root.left);
        int case3 = diameterOfBinaryTree(root.right);
        return Math.max(case1, Math.max(case2, case3));
    }

    private int height(TreeNode root) {
        if(root==null) {
            return 0;
        }
        return 1 + Math.max(height(root.left), height(root.right));
    }

    // TC: O(n)
    // SC: O(h) [O(log n), O(n)]
    private int dfs(TreeNode root, int[] res) {
        if(root == null) {
            return 0;
        }
        int left = dfs(root.left, res);
        int right = dfs(root.right, res);
        res[0] = Math.max(res[0], left+right);
        return 1 + Math.max(left, right);
    }
}

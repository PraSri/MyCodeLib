package backtracking;

public class HouseRobberIII {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int rob(TreeNode root) {
        // return maximum values for pair [includeRoot, excludeRoot]
        int[] results = heist(root);
        return Math.max(results[0], results[1]);
    }

    private int[] heist(TreeNode root) {
        if(root == null) {
            return new int[]{0,0};
        }

        int[] leftSubTree = heist(root.left);
        int[] rightSubTree = heist(root.right);

        int includeRoot = root.val + leftSubTree[1] + rightSubTree[1];
        int excludeRoot = Math.max(leftSubTree[0], leftSubTree[1]) + Math.max(rightSubTree[0], rightSubTree[1]);

        return new int[] {includeRoot, excludeRoot};
    }
}

package dynamic_programming;

public class BinaryTreeCameras {

// tc: O(n), sc: O(n)
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // 3 types of states possible for each node
    /**
     * The DFS returns one of three integer states for each node:
     * - NOT_MONITORED (0): This node is not covered by any camera.
     * - MONITORED_NOCAM (1): This node is covered by a child’s camera, but it has no camera itself.
     * - MONITORED_WITHCAM (2): This node has a camera, covering itself, its parent, and its children.
     */
    private int NOT_MONITORED = 0;
    private int MONITORED_NOCAM = 1;
    private int MONITORED_WITHCAM = 2;

    private int cameras = 0;

    /**
     * - A node returns NOT_MONITORED when both children are covered but have no cameras.
     * - If any child is NOT_MONITORED, we place a camera at the current node.
     * - Otherwise the node is covered by a child’s camera (MONITORED_NOCAM).
     * - After DFS, if the root remains NOT_MONITORED, we add one more camera.
     */

    public int minCameraCover(TreeNode root) {
        if (root == null)
            return 0;
        int top = dfs(root);
        return cameras + (top == NOT_MONITORED ? 1 : 0);
    }

    private int dfs(TreeNode root) {
        if (root == null)
            return MONITORED_NOCAM;
        int left = dfs(root.left);
        int right = dfs(root.right);
        if (left == MONITORED_NOCAM && right == MONITORED_NOCAM) {
            return NOT_MONITORED;
        } else if (left == NOT_MONITORED || right == NOT_MONITORED) {
            cameras++;
            return MONITORED_WITHCAM;
        } else {
            return MONITORED_NOCAM;
        }
    }
}
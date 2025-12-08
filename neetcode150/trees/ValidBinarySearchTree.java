package trees;

public class ValidBinarySearchTree {

    public boolean isValidBST(TreeNode root) {
        return isValid(root, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    boolean isValid(TreeNode root, int max, int min) {
        if(root == null) {
            return true;
        }
        if(root.val >= max || root.val <= min) {
            return false;
        }
        return isValid(root.left, root.val, min) && isValid(root.right, max, root.val);
    }

}

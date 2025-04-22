package tree_dfs;

public class FlattenBinaryTreeToLinkedList {
    public static TreeNode<Integer> flattenTree(TreeNode<Integer> root) {
        if (root == null) {
            return null;
        }

        TreeNode<Integer> current = root;
        while (current != null) {

            if (current.left != null) {

                TreeNode<Integer> last = current.left;
                while (last.right != null) {
                    last = last.right;
                }

                last.right = current.right;
                current.right = current.left;
                current.left = null;

            }
            current = current.right;
        }
        return root;
    }

}
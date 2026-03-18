package tree_dfs;

public class BinarySearchTree<T extends Comparable<T>> {
    TreeNode<T> root;

    public BinarySearchTree(T[] values) {
        root = createBinaryTree(values);
    }

    private TreeNode<T> createBinaryTree(T[] values) {
        if (values.length == 0) {
            return null;
        }

        // Create the root node of the binary search tree
        TreeNode<T> root = new TreeNode<>(values[0]);

        // Start iterating over the array of values starting from the second value
        for (int i = 1; i < values.length; i++) {
            TreeNode<T> node = new TreeNode<>(values[i]);
            TreeNode<T> curr = root;
            while (true) {
                // If the value is less than or equal to the current node's value, move to the left child
                if (node.data.compareTo(curr.data) <= 0) {
                    if (curr.left == null) {
                        // If the left child is empty, insert the new node here and break the loop
                        curr.left = node;
                        break;
                    } else {
                        // If the left child is not empty, move to the left child and continue the search
                        curr = curr.left;
                    }
                } else {
                    // If the value is greater than the current node's value, move to the right child
                    if (curr.right == null) {
                        // If the right child is empty, insert the new node here and break the loop
                        curr.right = node;
                        break;
                    } else {
                        // If the right child is not empty, move to the right child and continue the search
                        curr = curr.right;
                    }
                }
            }
        }

        // Return the root of the binary search tree
        return root;
    }
}

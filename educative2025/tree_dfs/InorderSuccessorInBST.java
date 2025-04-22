package tree_dfs;

public class InorderSuccessorInBST {

    /**
     * time complexity is O(n) in worst case when tree is skewed, for balanced BST it will be O(log n)
     */
    public TreeNode<Integer> inorderSuccessor(TreeNode<Integer> root, TreeNode<Integer> p) {
        TreeNode<Integer> successor = null;

        while (root != null) {
            // If the given node's value is greater than or equal to the
            // current node's value, move to the right subtree
            if (p.data >= root.data) {
                root = root.right;
            }

            // Otherwise, update the successor and move to the left subtree
            else {
                successor = root;
                root = root.left;
            }
        }

        // Return the in-order successor
        return successor;
    }


    // Driver code
    public static void main(String[] args) {
        Integer[][] inputs = {{18, 15, 13, 19, 5, 14}, {1, 2, 3, 4, 5, 6}, {100, 200, 50, 40, 30, 80, 90}, {10}, {1, 2}, {-10, -20, -30, -40, -50, -60, -70}};
        Integer[] p = {5, 6, 40, 10, 1, -60};

        for (int i = 0; i < inputs.length; i++) {
            BinarySearchTree<Integer> inputTree = new BinarySearchTree<>(inputs[i]);
            System.out.println((i + 1) + ".\tGiven Tree:");

            InorderSuccessorInBST solution = new InorderSuccessorInBST();
            TreeNode<Integer> pNode = new TreeNode<>(p[i]);
            TreeNode<Integer> result = solution.inorderSuccessor(inputTree.root, pNode);

            System.out.println("\n\tp Node: " + p[i]);

            if (result != null) {
                int res = result.data;
                System.out.println("\n\tInorder Successor: " + res);
            } else {
                String res_2 = "Null";
                System.out.println("\n\tInorder Successor: " + res_2);
            }

            System.out.println(new String(new char[100]).replace("\0", "-"));
        }
    }

}


package tree_dfs;

import java.util.*;
import java.util.stream.Collectors;

public class DeleteNodesAndReturnForest {
    public static List<TreeNode<Integer>> returnForest(TreeNode<Integer> root, List<Integer> deleteNodes) {
        // List to store the resulting forest of trees.
        List<TreeNode<Integer>> forest = new ArrayList<>();

        // If the input tree is empty, return an empty forest.
        if (root == null) {
            return forest;
        }

        // Convert the list of nodes to delete into a set for faster lookup.
        Set<Integer> toDelete = new HashSet<>(deleteNodes);

        // Use a stack for iterative depth-first search (DFS).
        Stack<TreeNode<Integer>> stack = new Stack<>();
        stack.push(root); // Start with the root node.

        while (!stack.isEmpty()) {
            // Pop a node from the stack for processing.
            TreeNode<Integer> node = stack.pop();

            // Process the left child of the current node.
            if (node.left != null) {
                stack.push(node.left); // Add the left child to the stack for DFS.
                if (toDelete.contains(node.left.data)) { // If the left child needs to be deleted,
                    node.left = null; // Detach it from the current node.
                }
            }

            // Process the right child of the current node.
            if (node.right != null) {
                stack.push(node.right); // Add the right child to the stack for DFS.
                if (toDelete.contains(node.right.data)) { // If the right child needs to be deleted,
                    node.right = null; // Detach it from the current node.
                }
            }

            // If the current node itself needs to be deleted.
            if (toDelete.contains(node.data)) {
                // Add the left subtree to the forest if it exists.
                if (node.left != null) {
                    forest.add(node.left);
                }
                // Add the right subtree to the forest if it exists.
                if (node.right != null) {
                    forest.add(node.right);
                }
            }
        }

        // If the root node is not deleted, it becomes part of the forest.
        if (!toDelete.contains(root.data)) {
            forest.add(root);
        }

        // Return the resulting forest of trees.
        return forest;
    }

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {

        return returnForest(root, Arrays.stream(to_delete).boxed().collect(Collectors.toList()));

    }
    // Driver code
    public static void main(String[] args) {
        List<List<TreeNode<Integer>>> listOfTrees = Arrays.asList(
                Arrays.asList(new TreeNode<>(3), new TreeNode<>(2), new TreeNode<>(17), new TreeNode<>(1), new TreeNode<>(4), new TreeNode<>(19), new TreeNode<>(5)),
                Arrays.asList(new TreeNode<>(7), new TreeNode<>(6), new TreeNode<>(5), new TreeNode<>(4), new TreeNode<>(3), new TreeNode<>(2), null, new TreeNode<>(1)),
                Arrays.asList(new TreeNode<>(5), new TreeNode<>(4), new TreeNode<>(6), new TreeNode<>(3), new TreeNode<>(2), new TreeNode<>(7), new TreeNode<>(8), new TreeNode<>(1), new TreeNode<>(9)),
                Arrays.asList(new TreeNode<>(5), new TreeNode<>(2), new TreeNode<>(1), new TreeNode<>(6), new TreeNode<>(10), new TreeNode<>(11), new TreeNode<>(44)),
                Arrays.asList(new TreeNode<>(1), new TreeNode<>(2), new TreeNode<>(5), new TreeNode<>(3), new TreeNode<>(4), new TreeNode<>(6))
        );

        List<List<Integer>> deleteNodes = Arrays.asList(
                Arrays.asList(2, 17),
                Arrays.asList(5, 1),
                Arrays.asList(5, 6, 9),
                Arrays.asList(1, 44),
                Arrays.asList(2)
        );

        List<BinaryTree> inputTrees = new ArrayList<>();
        for (List<TreeNode<Integer>> listOfNodes : listOfTrees) {
            BinaryTree tree = new BinaryTree(listOfNodes);
            inputTrees.add(tree);
        }

        int x = 1;
        for (int i = 0; i < inputTrees.size(); i++) {
            System.out.println(x + ".\tInput Tree:");
            System.out.println("\n\tNode(s) to delete: " + deleteNodes.get(i));
            List<TreeNode<Integer>> res = returnForest(inputTrees.get(i).root, deleteNodes.get(i)); // Generate the forest.
            System.out.println("\n\tReturned forest:");
            for (TreeNode<Integer> root : res) {
                System.out.println("\t");
                System.out.println("\n");
            }
            x++;
            System.out.println(new String(new char[100]).replace('\0', '-')); // Print a separator line.
        }
    }
}
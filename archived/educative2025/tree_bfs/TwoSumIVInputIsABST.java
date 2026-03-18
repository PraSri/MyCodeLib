package tree_bfs;

import tree_dfs.TreeNode;

import java.util.*;

public class TwoSumIVInputIsABST {
    public static boolean findTarget(TreeNode<Integer> root, int k) {
        // If the tree is empty
        if (root == null) {
            return false;
        }

        // Set to store values seen so far
        Set<Integer> seen = new HashSet<>();

        // Queue for level-order traversal of the BST
        Queue<TreeNode<Integer>> q = new LinkedList<>();

        // Start the traversal with the root node
        q.add(root);

        // Perform breadth-first search (BFS)
        while (!q.isEmpty()) {
            // Dequeue the front node from the queue
            TreeNode<Integer> curr = q.poll();

            if (curr != null) {
                // Check if the complement of the current node's value exists in the set
                if (seen.contains(k - curr.data)) {
                    return true;
                }

                // Add the current node's value to the set
                seen.add(curr.data);

                // Add the right and left children of the current node to the queue
                q.add(curr.right);
                q.add(curr.left);
            }
        }

        // If no such pair is found, return false
        return false;
    }

}
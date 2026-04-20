package uber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TreeBoundaryTraversal {

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        // Add root value to result
        result.add(root.val);

        // If root is a leaf node, return immediately
        if (root.left == null && root.right == null) {
            return result;
        }

        // Three lists to store different boundary parts
        List<Integer> leftBoundary = new ArrayList<>();
        List<Integer> leaves = new ArrayList<>();
        List<Integer> rightBoundary = new ArrayList<>();

        // Collect left boundary (excluding leaves)
        collectBoundary(leftBoundary, root.left, 0);

        // Collect all leaves
        collectBoundary(leaves, root, 1);

        // Collect right boundary (excluding leaves)
        collectBoundary(rightBoundary, root.right, 2);

        // Add all parts to result in order
        result.addAll(leftBoundary);
        result.addAll(leaves);

        // Reverse right boundary for bottom-up traversal
        Collections.reverse(rightBoundary);
        result.addAll(rightBoundary);

        return result;
    }

    /**
     * DFS helper method to collect boundary nodes
     *
     * @param boundaryList - list to store boundary nodes
     * @param node         - current tree node
     * @param boundaryType - 0: left boundary, 1: leaves, 2: right boundary
     */
    private void collectBoundary(List<Integer> boundaryList, TreeNode node, int boundaryType) {
        if (node == null) {
            return;
        }

        if (boundaryType == 0) { // Left boundary collection
            // Only add non-leaf nodes to left boundary
            if (node.left != null || node.right != null) {
                boundaryList.add(node.val);

                // Prefer left child, fall back to right if left doesn't exist
                if (node.left != null) {
                    collectBoundary(boundaryList, node.left, boundaryType);
                } else {
                    collectBoundary(boundaryList, node.right, boundaryType);
                }
            }
        } else if (boundaryType == 1) { // Leaves collection
            // Add only leaf nodes
            if (node.left == null && node.right == null) {
                boundaryList.add(node.val);
            } else {
                // Continue searching for leaves in both subtrees
                collectBoundary(boundaryList, node.left, boundaryType);
                collectBoundary(boundaryList, node.right, boundaryType);
            }
        } else { // Right boundary collection (boundaryType == 2)
            // Only add non-leaf nodes to right boundary
            if (node.left != null || node.right != null) {
                boundaryList.add(node.val);

                // Prefer right child, fall back to left if right doesn't exist
                if (node.right != null) {
                    collectBoundary(boundaryList, node.right, boundaryType);
                } else {
                    collectBoundary(boundaryList, node.left, boundaryType);
                }
            }
        }
    }

    public static class TreeNode {

        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int x) {
            val = x;
            left = null;
            right = null;
        }
    }
}


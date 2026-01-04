package trees;

import java.util.*;

/**Binary Tree Zigzag Level Order Traversal
https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/

Binary Tree Level Order Traversal II
https://leetcode.com/problems/binary-tree-level-order-traversal-ii/

Minimum Depth of Binary Tree
https://leetcode.com/problems/minimum-depth-of-binary-tree/

Binary Tree Vertical Order Traversal
https://leetcode.com/problems/binary-tree-vertical-order-traversal/

Average of Levels in Binary Tree
https://leetcode.com/problems/average-of-levels-in-binary-tree/

N-ary Tree Level Order Traversal
https://leetcode.com/problems/n-ary-tree-level-order-traversal/

Cousins in Binary Tree
https://leetcode.com/problems/cousins-in-binary-tree/

Minimum Number of Operations to Sort a Binary Tree by Level
https://leetcode.com/problems/minimum-number-of-operations-to-sort-a-binary-tree-by-level/

Divide Nodes Into the Maximum Number of Groups
https://leetcode.com/problems/divide-nodes-into-the-maximum-number-of-groups/* */

public class BinaryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> level = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        if(root == null){
            return res;
        }

        queue.add(root);
        queue.add(null);

        while(!queue.isEmpty()) {

            TreeNode curr = queue.poll();

            if(curr == null && queue.isEmpty()) {

                res.add(new ArrayList<>(level));
                break;

            }

            if(curr == null) {

                res.add(new ArrayList<>(level));
                level.clear();
                queue.add(null);
                continue;

            }

            level.add(curr.val);

            if(curr.left!=null) {
                queue.add(curr.left);
            }

            if(curr.right!=null) {
                queue.add(curr.right);
            }

        }
        return res;
    }

    /**
     * Binary Tree Zigzag Level Order Traversal
     * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
     */
    public static class BinaryTreeZigzagLevelOrderTraversal {
    }

    /**
     * Binary Tree Level Order Traversal II
     * https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
     */
    public static class BinaryTreeLevelOrderTraversalIi {
    }

    /**
     * Minimum Depth of Binary Tree
     * https://leetcode.com/problems/minimum-depth-of-binary-tree/
     */
    public static class MinimumDepthOfBinaryTree {
    }

    /**
     * Binary Tree Vertical Order Traversal
     * https://leetcode.com/problems/binary-tree-vertical-order-traversal/
     */
    public static class BinaryTreeVerticalOrderTraversal {
    }

    /**
     * Average of Levels in Binary Tree
     * https://leetcode.com/problems/average-of-levels-in-binary-tree/
     */
    public static class AverageOfLevelsInBinaryTree {
    }

    /**
     * N-ary Tree Level Order Traversal
     * https://leetcode.com/problems/n-ary-tree-level-order-traversal/
     */
    public static class NAryTreeLevelOrderTraversal {
    }

    /**
     * Cousins in Binary Tree
     * https://leetcode.com/problems/cousins-in-binary-tree/
     */
    public static class CousinsInBinaryTree {
    }

    /**
     * Minimum Number of Operations to Sort a Binary Tree by Level
     * https://leetcode.com/problems/minimum-number-of-operations-to-sort-a-binary-tree-by-level/
     */
    public static class MinimumNumberOfOperationsToSortABinaryTreeByLevel {
    }

    /**
     * Divide Nodes Into the Maximum Number of Groups
     * https://leetcode.com/problems/divide-nodes-into-the-maximum-number-of-groups/*
     */
    public static class DivideNodesIntoTheMaximumNumberOfGroups {
    }
}

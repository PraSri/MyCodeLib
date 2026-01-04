package trees;

import java.util.*;

/**Balanced Binary Tree
https://leetcode.com/problems/balanced-binary-tree/

Minimum Depth of Binary Tree
https://leetcode.com/problems/minimum-depth-of-binary-tree/

Maximum Depth of N-ary Tree
https://leetcode.com/problems/maximum-depth-of-n-ary-tree/

Time Needed to Inform All Employees
https://leetcode.com/problems/time-needed-to-inform-all-employees/

Amount of Time for Binary Tree to Be Infected
https://leetcode.com/problems/amount-of-time-for-binary-tree-to-be-infected/

Height of Binary Tree After Subtree Removal Queries
https://leetcode.com/problems/height-of-binary-tree-after-subtree-removal-queries/*/

public class MaximumDepthOfBinaryTree {

    //Time complexity:
    //O(n)
    //Space complexity:
    //O(h)
    //Best Case (balanced tree):
    //O(log(n))
    //Worst Case (degenerate tree):
    //O(n)
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    public int maxDepthBfs(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        if (root != null) {
            q.add(root);
        }
        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }
            level++;
        }
        return level;
    }


    /**
     * Balanced Binary Tree
     * https://leetcode.com/problems/balanced-binary-tree/
     */
    public static class BalancedBinaryTree {
    }

    /**
     * Minimum Depth of Binary Tree
     * https://leetcode.com/problems/minimum-depth-of-binary-tree/
     */
    public static class MinimumDepthOfBinaryTree {
    }

    /**
     * Maximum Depth of N-ary Tree
     * https://leetcode.com/problems/maximum-depth-of-n-ary-tree/
     */
    public static class MaximumDepthOfNAryTree {
    }

    /**
     * Time Needed to Inform All Employees
     * https://leetcode.com/problems/time-needed-to-inform-all-employees/
     */
    public static class TimeNeededToInformAllEmployees {
    }

    /**
     * Amount of Time for Binary Tree to Be Infected
     * https://leetcode.com/problems/amount-of-time-for-binary-tree-to-be-infected/
     */
    public static class AmountOfTimeForBinaryTreeToBeInfected {
    }

    /**
     * Height of Binary Tree After Subtree Removal Queries
     * https://leetcode.com/problems/height-of-binary-tree-after-subtree-removal-queries/
     */
    public static class HeightOfBinaryTreeAfterSubtreeRemovalQueries {
    }
}

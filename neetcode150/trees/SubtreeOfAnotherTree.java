package trees;

/**Count Univalue Subtrees (Medium)
👉 https://leetcode.com/problems/count-univalue-subtrees/

Most Frequent Subtree Sum (Medium)
👉 https://leetcode.com/problems/most-frequent-subtree-sum/*/

public class SubtreeOfAnotherTree {

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(root==null) {
            return false;
        }
        if(isSame(root, subRoot)) {
            return true;
        }
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private boolean isSame(TreeNode a, TreeNode b) {
        if(a==null && b==null) {
            return true;
        }
        if(a==null || b==null) {
            return false;
        }
        if(a.val!=b.val) {
            return false;
        }
        return isSame(a.left, b.left) && isSame(a.right, b.right);
    }


    /**
     * Count Univalue Subtrees
     * https://leetcode.com/problems/count-univalue-subtrees/
     */
    public static class CountUnivalueSubtrees {
    }

    /**
     * Most Frequent Subtree Sum
     * https://leetcode.com/problems/most-frequent-subtree-sum/
     */
    public static class MostFrequentSubtreeSum {
    }
}

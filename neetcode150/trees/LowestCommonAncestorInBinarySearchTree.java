package trees;

/**Lowest Common Ancestor of a Binary Tree
https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/

Smallest Common Region
https://leetcode.com/problems/smallest-common-region/

Lowest Common Ancestor of a Binary Tree II
https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-ii/

Lowest Common Ancestor of a Binary Tree III
https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iii/

Lowest Common Ancestor of a Binary Tree IV
https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iv/*/

public class LowestCommonAncestorInBinarySearchTree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode cur = root;

        while (cur != null) {

            if (p.val > cur.val && q.val > cur.val) {
                cur = cur.right; // dono right side mein hain
            }
            else if (p.val < cur.val && q.val < cur.val) {
                cur = cur.left; // dono left side mein hain
            }
            else {
                return cur; // split hogaya, yeh hi LCA hai
            }
        }
        return null;
    }


    /**
     * Lowest Common Ancestor of a Binary Tree
     * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
     */
    public static class LowestCommonAncestorOfABinaryTree {
    }

    /**
     * Smallest Common Region
     * https://leetcode.com/problems/smallest-common-region/
     */
    public static class SmallestCommonRegion {
    }

    /**
     * Lowest Common Ancestor of a Binary Tree II
     * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-ii/
     */
    public static class LowestCommonAncestorOfABinaryTreeIi {
    }

    /**
     * Lowest Common Ancestor of a Binary Tree III
     * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iii/
     */
    public static class LowestCommonAncestorOfABinaryTreeIii {
    }

    /**
     * Lowest Common Ancestor of a Binary Tree IV
     * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iv/
     */
    public static class LowestCommonAncestorOfABinaryTreeIv {
    }
}

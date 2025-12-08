package trees;

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

}

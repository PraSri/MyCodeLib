package trees;

import java.util.*;

public class KthSmallestIntegerInBST {
    public int kthSmallest(TreeNode root, int k) {
        if(root==null) {
            return -1;
        }
        Stack<TreeNode> s = new Stack<>();
        TreeNode curr = root;
        while(curr!=null || !s.isEmpty()) {
            while(curr!=null) {
                s.push(curr);
                curr = curr.left;
            }
            curr = s.pop();
            k--;
            if(k==0) {
                return curr.val;
            }
            curr = curr.right;
        }
        return -1;
    }
}

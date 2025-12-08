package trees;

import java.util.*;

public class BinaryTreeRightSideView {

    public List<Integer> rightSideView(TreeNode root) {

        List<Integer> res = new ArrayList<>();

        if(root == null) {
            return res;
        }

        Queue<TreeNode> q = new LinkedList<>();

        q.add(root);

        while(!q.isEmpty()) {

            int n = q.size();

            for(int i = 1; i <= n; i++) {
                TreeNode curr = q.poll();

                if(i==n) {
                    res.add(curr.val);
                }

                if(curr.left!=null) {
                    q.add(curr.left);
                }

                if(curr.right!=null) {
                    q.add(curr.right);
                }
            }
        }
        return res;
    }

}

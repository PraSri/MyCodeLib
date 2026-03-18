package leetcode75;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTreeTraversal {


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> level = new ArrayList<>();

        Queue<TreeNode> q = new LinkedList<>();

        if (root == null) {
            return res;
        }

        q.add(root);
        q.add(null);

        TreeNode curr;

        while (!q.isEmpty()) {
            curr = q.poll();
            if(curr == null && q.isEmpty()) {
                res.add(new ArrayList<>(level));
                break;
            }
            if(curr == null) {
                res.add(new ArrayList<>(level));
                level.clear();
                q.add(null);
                continue;
            }
            level.add(curr.val);
            if (curr.left!=null) {
                q.add(curr.left);
            }
            if (curr.right!=null) {
                q.add(curr.right);
            }
        }

        return res;

    }


}

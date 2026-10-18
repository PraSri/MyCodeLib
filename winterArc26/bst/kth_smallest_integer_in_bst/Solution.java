package bst.kth_smallest_integer_in_bst;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;
        while (true) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            if (--k == 0) {
                return node.val;
            }
            node = node.right;
        }
    }
}

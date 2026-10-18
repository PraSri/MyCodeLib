package tree_dfs.construct_binary_tree_from_preorder_and_inorder_traversal;

import common.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    private int preIdx;
    private int[] preorder;
    private Map<Integer, Integer> inIndex;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.preIdx = 0;
        this.inIndex = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inIndex.put(inorder[i], i);
        }
        return build(0, inorder.length - 1);
    }

    private TreeNode build(int lo, int hi) {
        if (lo > hi) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preIdx++]);
        int mid = inIndex.get(root.val);
        root.left = build(lo, mid - 1);
        root.right = build(mid + 1, hi);
        return root;
    }
}

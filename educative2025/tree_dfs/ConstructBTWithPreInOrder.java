package tree_dfs;

import java.util.*;

public class ConstructBTWithPreInOrder {

    public static TreeNode<Integer> buildTreeHelper(
            int[] pOrder, int left, int right,
            HashMap<Integer, Integer> mapping, int[] pIndex
    ) {
        // If left > right, it means there are no more nodes left to construct
        if (left > right) {
            return null;
        }

        // Pick current node from preorder list
        // using pIndex and increment pIndex
        int curr = pOrder[pIndex[0]];
        pIndex[0]++;
        TreeNode<Integer> root = new TreeNode<>(curr);

        // If this node has no children then return
        if (left == right) {
            return root;
        }

        // Else find the index of this node in inorder list
        int inIndex = mapping.get(curr);

        // Recursively build the left subtree by calling buildTreeHelper
        // on the elements in the inorder list from left to inIndex - 1
        root.left = buildTreeHelper(pOrder, left, inIndex - 1, mapping, pIndex);

        // Recursively build the right subtree by calling buildTreeHelper
        // on the elements in the inorder list from inIndex + 1 to right
        root.right = buildTreeHelper(pOrder, inIndex + 1, right, mapping, pIndex);

        return root;
    }

    public static TreeNode<Integer> buildTree(int[] pOrder, int[] iOrder) {
        // Using HashMap to store the inorder list to reduce time complexity
        // of search to O(1)
        HashMap<Integer, Integer> mapping = new HashMap<>();

        // Iterate through the inorder list and map each value to its index
        for (int i = 0; i < iOrder.length; i++) {
            mapping.put(iOrder[i], i);
        }

        // Explicitly using an array to pass pIndex by reference because
        // in Java, primitive types are passed by value
        int[] pIndex = {0};

        return buildTreeHelper(pOrder, 0, pOrder.length - 1, mapping, pIndex);
    }

}

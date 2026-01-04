package trees;

import java.util.*;

/**Encode and Decode Strings (Medium)
https://leetcode.com/problems/encode-and-decode-strings/

Serialize and Deserialize BST (Medium)
https://leetcode.com/problems/serialize-and-deserialize-bst/

Find Duplicate Subtrees (Medium)
https://leetcode.com/problems/find-duplicate-subtrees/

Serialize and Deserialize N-ary Tree (Hard)
https://leetcode.com/problems/serialize-and-deserialize-n-ary-tree/*/

public class SerializeAndDeserializeBinaryTree {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        List<String> res = new ArrayList<>();
        dfsSerialize(root, res);
        return String.join(",", res);
    }

    void dfsSerialize(TreeNode root, List<String> res) {
        if(root == null) {
            res.add("N");
            return;
        }
        res.add(String.valueOf(root.val));
        dfsSerialize(root.left, res);
        dfsSerialize(root.right, res);
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] vals = data.split(",");
        int[] i = {0};
        return dfsDeserialize(vals, i);
    }

    TreeNode dfsDeserialize(String[] vals, int[] i) {
        if(vals[i[0]].equals("N")) {
            i[0]++;
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(vals[i[0]]));
        i[0]++;
        node.left = dfsDeserialize(vals, i);
        node.right = dfsDeserialize(vals, i);
        return node;
    }


    /**
     * Encode and Decode Strings
     * https://leetcode.com/problems/encode-and-decode-strings/
     */
    public static class EncodeAndDecodeStrings {
    }

    /**
     * Serialize and Deserialize BST
     * https://leetcode.com/problems/serialize-and-deserialize-bst/
     */
    public static class SerializeAndDeserializeBst {
    }

    /**
     * Find Duplicate Subtrees
     * https://leetcode.com/problems/find-duplicate-subtrees/
     */
    public static class FindDuplicateSubtrees {
    }

    /**
     * Serialize and Deserialize N-ary Tree
     * https://leetcode.com/problems/serialize-and-deserialize-n-ary-tree/
     */
    public static class SerializeAndDeserializeNAryTree {
    }
}

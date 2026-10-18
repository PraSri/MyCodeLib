package serialization.serialize_and_deserialize_binary_tree;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Solution {

    public static class Codec {
        private static final String NULL = "N";
        private static final String SEP = ",";

        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            write(root, sb);
            return sb.toString();
        }

        public TreeNode deserialize(String data) {
            Deque<String> tokens = new ArrayDeque<>(Arrays.asList(data.split(SEP)));
            return read(tokens);
        }

        private void write(TreeNode node, StringBuilder sb) {
            if (node == null) {
                sb.append(NULL).append(SEP);
                return;
            }
            sb.append(node.val).append(SEP);
            write(node.left, sb);
            write(node.right, sb);
        }

        private TreeNode read(Deque<String> tokens) {
            String token = tokens.poll();
            if (token == null || token.equals(NULL)) {
                return null;
            }
            TreeNode node = new TreeNode(Integer.parseInt(token));
            node.left = read(tokens);
            node.right = read(tokens);
            return node;
        }
    }
}

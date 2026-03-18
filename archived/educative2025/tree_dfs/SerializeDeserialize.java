package tree_dfs;

import java.util.*;

public class SerializeDeserialize {
    // Initializing our marker as the max possible int value
    private static final String MARKER = "M";
    private static int m = 1;

    private static void serializeRec(TreeNode<Integer> node, List<String> stream) {
        // Adding marker to stream if the node is null
        if (node == null) {
            String s = Integer.toString(m);
            stream.add(MARKER + s);
            m = m + 1;
            return;
        }

        // Adding node to stream
        stream.add(String.valueOf(node.data));

        // Doing a pre-order tree traversal for serialization
        serializeRec(node.left, stream);
        serializeRec(node.right, stream);
    }

    // Function to serialize tree into a list.
    public static List<String> serialize(TreeNode<Integer> root) {
        List<String> stream = new ArrayList<>();
        serializeRec(root, stream);
        return stream;
    }

    public static TreeNode<Integer> deserializeHelper(List<String> stream) {
        // pop last element from list
        String val = stream.remove(stream.size() - 1);

        // Return null when a marker is encountered
        if (val.charAt(0) == MARKER.charAt(0)) {
            return null;
        }

        // Creating new Binary Tree Node from current value from stream
        TreeNode<Integer> node = new TreeNode<>(Integer.parseInt(val));

        // Doing a pre-order tree traversal for deserialization
        node.left = deserializeHelper(stream);
        node.right = deserializeHelper(stream);

        // Return node if it exists
        return node;
    }

    // Function to deserialize list into a binary tree.
    public static TreeNode<Integer> deserialize(List<String> stream) {
        Collections.reverse(stream);
        TreeNode<Integer> node = deserializeHelper(stream);
        return node;
    }

    // Driver code
    public static void main(String[] args) {
        // Create a list of list of TreeNode objects to represent binary trees
        List<List<TreeNode<Integer>>> listOfTrees = Arrays.asList(Arrays.asList(new TreeNode<Integer>(100), new TreeNode<Integer>(50), new TreeNode<Integer>(200), new TreeNode<Integer>(25), new TreeNode<Integer>(75), new TreeNode<Integer>(350)), Arrays.asList(new TreeNode<Integer>(100), new TreeNode<Integer>(200), new TreeNode<Integer>(75), new TreeNode<Integer>(50), new TreeNode<Integer>(25), new TreeNode<Integer>(350)), Arrays.asList(new TreeNode<Integer>(200), new TreeNode<Integer>(350), new TreeNode<Integer>(100), new TreeNode<Integer>(25), new TreeNode<Integer>(50), new TreeNode<Integer>(200), new TreeNode<Integer>(25)), Arrays.asList(new TreeNode<Integer>(25), new TreeNode<Integer>(50), new TreeNode<Integer>(75), new TreeNode<Integer>(100), new TreeNode<Integer>(200), new TreeNode<Integer>(350)), Arrays.asList(new TreeNode<Integer>(350), new TreeNode<Integer>(75), new TreeNode<Integer>(25), new TreeNode<Integer>(200), new TreeNode<Integer>(50), new TreeNode<Integer>(100)), Arrays.asList(new TreeNode<Integer>(1), null, new TreeNode<Integer>(2), null, new TreeNode<Integer>(3), null, new TreeNode<Integer>(4), null, new TreeNode<Integer>(5)), Arrays.asList());

        // Create the binary trees using the BinaryTree class
        List<BinaryTree<Integer>> inputTrees = new ArrayList<>();
        for (List<TreeNode<Integer>> ListOfNodes : listOfTrees) {
            BinaryTree<Integer> tree = new BinaryTree<>(ListOfNodes);
            inputTrees.add(tree);
        }

        int i = 0;
        for (BinaryTree<Integer> tree : inputTrees) {
            if (i > 0) {
                System.out.print("\n");
            }
            System.out.println((i + 1) + ".\tBinary tree:");

            preOrder(tree.root);
            i++;
            System.out.println("\n\tMarker used for NULL nodes in serialization/deserialization: " + MARKER);

            // Serialization
            List<String> ostream = serialize(tree.root);
            System.out.println("\n\tSerialized integer list:");
            System.out.println("\t" + ostream);

            // Deserialization
            TreeNode<Integer> deserializedRoot = deserialize(ostream);
            System.out.println("\n\tDeserialized binary tree:");
            preOrder(deserializedRoot);
            System.out.println(new String(new char[100]).replace('\0', '-'));
            m = 1;
        }
    }

    public static void displayTree(TreeNode root) {
        printTree(root, "", true);
    }

    private static void printTree(TreeNode node, String prefix, boolean isLeft) {
        if (node != null) {
            System.out.println(prefix + (isLeft ? "??? " : "??? ") + node.data);
            printTree(node.left, prefix + (isLeft ? "?   " : "    "), true);
            printTree(node.right, prefix + (isLeft ? "?   " : "    "), false);
        }
    }

    public static void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data + " "); // Print node data
        preOrder(root.left);  // Traverse left subtree
        preOrder(root.right); // Traverse right subtree
    }

}
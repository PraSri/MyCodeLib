public class LowestCommonAncestor {
    public static EduTreeNode LowestCommonAncestor(EduTreeNode p, EduTreeNode q) {
        // Initialize two pointers
        EduTreeNode ptr1 = p;
        EduTreeNode ptr2 = q;
        
        // Traverse until they meet
        while (ptr1 != ptr2) {
            // Move ptr1 to parent node or switch to the other node if reached the root
            if (ptr1.parent != null) {
                ptr1 = ptr1.parent;
            } 
            else {
                ptr1 = q;
            }
            
            // Move ptr2 to parent node or switch to the other node if reached the root
            if (ptr2.parent != null) {
                ptr2 = ptr2.parent;
            } else {
                ptr2 = p;
            }
        }
        
        // Return ptr1 or ptr2, since they are the same at this point
        return ptr1;
    }

    public static void main(String[] args) {
        List<List<Integer>> input_trees = Arrays.asList(
            Arrays.asList(100, 50, 200, 25, 75, 350),
            Arrays.asList(100, 200, 75, 50, 25, 350),
            Arrays.asList(350, 100, 75, 50, 200, 25),
            Arrays.asList(100, 50, 200, 25, 75, 350),
            Arrays.asList(25, 50, 75, 100, 200, 350)
        );
        List<List<Integer>> input_nodes = Arrays.asList(
            Arrays.asList(25, 75),
            Arrays.asList(50, 350),
            Arrays.asList(100, 200),
            Arrays.asList(50, 25),
            Arrays.asList(350, 200)
        );

        for (int i = 0; i < input_trees.size(); i++) {
            EduBinaryTree tree = new EduBinaryTree(input_trees.get(i));
            System.out.println((i + 1) + ".\tBinary tree:");
            Print.displayTree(tree.getRoot());
            System.out.println("\n\tp = " + input_nodes.get(i).get(0));
            System.out.println("\tq = " + input_nodes.get(i).get(1));
            EduTreeNode p = tree.find(tree.getRoot(), input_nodes.get(i).get(0));
            EduTreeNode q = tree.find(tree.getRoot(), input_nodes.get(i).get(1));
            EduTreeNode lca = LowestCommonAncestor(p, q);
            System.out.println("\n\tLowest common ancestor: " + lca.data);
            System.out.println(new String(new char[100]).replace("\0", "-"));
        }
    }
}

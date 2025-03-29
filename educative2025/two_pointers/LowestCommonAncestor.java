package two_pointers;

public class LowestCommonAncestor {

    public static class EduTreeNode {
        int data;
        EduTreeNode left;
        EduTreeNode right;
        EduTreeNode parent;

        EduTreeNode(int value) {
            this.data = value;
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }
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

}

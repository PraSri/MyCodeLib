package common;

/** Linked-list node with a random pointer (LeetCode "Node" for Copy List with Random Pointer). */
public class RandomNode {
    public int val;
    public RandomNode next;
    public RandomNode random;

    public RandomNode(int val) {
        this.val = val;
    }
}

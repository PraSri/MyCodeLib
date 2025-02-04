import java.util.*;

class EduBinaryTree {
    private EduTreeNode root;

    private EduTreeNode createBinaryTree(List<Integer> nodes) {
        if (nodes.isEmpty() || nodes.get(0) == 0) {
            return null;
        }
        EduTreeNode root = new EduTreeNode(nodes.get(0));
        Queue<EduTreeNode> q = new LinkedList<>();
        q.offer(root);
        int i = 1;
        while (i < nodes.size()) {
            EduTreeNode curr = q.poll();
            if (i < nodes.size() && nodes.get(i) != 0) {
                curr.left = new EduTreeNode(nodes.get(i));
                curr.left.parent = curr;
                q.offer(curr.left);
            }
            i++;
            if (i < nodes.size() && nodes.get(i) != 0) {
                curr.right = new EduTreeNode(nodes.get(i));
                curr.right.parent = curr;
                q.offer(curr.right);
            }
            i++;
        }
        return root;
    }

    public EduBinaryTree(List<Integer> nodes) {
        this.root = createBinaryTree(nodes);
    }

    public EduTreeNode find(EduTreeNode root, int value) {
        if (root == null) {
            return null;
        }
        Queue<EduTreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            EduTreeNode currentNode = q.poll();
            if (currentNode.data == value) {
                return currentNode;
            }
            if (currentNode.left != null) {
                q.offer(currentNode.left);
            }
            if (currentNode.right != null) {
                q.offer(currentNode.right);
            }
        }
        return null;
    }

    public EduTreeNode getRoot() {
        return root;
    }
}

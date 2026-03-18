package tree_bfs;

import java.util.LinkedList;
import java.util.Queue;

public class ConnectAllSiblingsOfBT {

    public static class EduTreeNode<T> {
        T data;
        EduTreeNode<T> left;
        EduTreeNode<T> right;
        EduTreeNode<T> next;

        EduTreeNode(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.next = null;
        }
    }

    public static EduTreeNode<Integer> connectAllSiblings(EduTreeNode<Integer> root) {

        Queue<EduTreeNode<Integer>> q = new LinkedList<>();

        q.add(root);

        while (!q.isEmpty()) {
            EduTreeNode<Integer> curr = q.poll();
            if(curr!=null) {
                q.add(curr.left);
                q.add(curr.right);
                curr.next = q.peek();
            }
        }

        return root;
    }

    }

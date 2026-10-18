package common;

import java.util.ArrayList;
import java.util.List;

/** Undirected graph node (LeetCode "Node" for Clone Graph). */
public class GraphNode {
    public int val;
    public List<GraphNode> neighbors;

    public GraphNode() {
        this(0);
    }

    public GraphNode(int val) {
        this.val = val;
        this.neighbors = new ArrayList<>();
    }
}

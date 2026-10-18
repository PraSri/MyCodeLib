package graph_dfs.clone_graph;

import common.GraphNode;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public GraphNode cloneGraph(GraphNode node) {
        return clone(node, new HashMap<>());
    }

    private GraphNode clone(GraphNode node, Map<GraphNode, GraphNode> clones) {
        if (node == null) {
            return null;
        }
        GraphNode existing = clones.get(node);
        if (existing != null) {
            return existing;
        }
        GraphNode copy = new GraphNode(node.val);
        clones.put(node, copy);
        for (GraphNode neighbor : node.neighbors) {
            copy.neighbors.add(clone(neighbor, clones));
        }
        return copy;
    }
}

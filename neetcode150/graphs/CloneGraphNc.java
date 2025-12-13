package graphs;

import java.util.*;

public class CloneGraphNc {

    public Node cloneGraph(Node node) {
        Map<Integer, Node> map = new HashMap<>();
        return dfs(node, map);
    }

    private Node dfs(Node src, Map<Integer, Node> map) {

        if(src == null) {
            return src;
        }

        if(map.containsKey(src.val)) {
            return map.get(src.val);
        }

        Node node = new Node(src.val);
        map.put(node.val, node);

        for(Node neighbour: src.neighbors) {
            node.neighbors.add(dfs(neighbour, map));
        }
        return node;
    }
}
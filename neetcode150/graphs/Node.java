package graphs;

import java.util.*;

public class Node {

        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }
        public Node(int _val, ArrayList<graphs.Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
}

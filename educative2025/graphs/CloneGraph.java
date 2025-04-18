package graphs;

import java.util.*;

public class CloneGraph {

    public static class Node {
        int data;
        List<Node> neighbors;

        public Node(int data) {
            this.data = data;
            this.neighbors = new ArrayList<>();
        }
    }

    public static class GraphUtility {

        public static Node createGraph(int[][] data) {
            if (data.length == 0) {
                return new Node(1);
            }
            Node[] nodes = new Node[data.length];
            for (int i = 0; i < data.length; i++) {
                nodes[i] = new Node(i + 1);
            }

            for (int i = 0; i < data.length; i++) {
                for (int neighbor : data[i]) {
                    nodes[i].neighbors.add(nodes[neighbor - 1]);
                }
            }
            return nodes[0];
        }

        public static List<List<Integer>> create2DList(Node root) {
            // Initialize a queue for breadth-first traversal
            Queue<Node> queue = new LinkedList<Node>();
            queue.add(root);
            // Initialize a HashMap to keep track of visited nodes
            HashMap<Node, Node> visited = new HashMap<Node, Node>();
            // Initialize a 2D List to store the graph
            List<List<Integer>> graph = new ArrayList<List<Integer>>();
            // Initialize a HashMap to store the index of each node
            HashMap<Node, Integer> nodeIndex = new HashMap<Node, Integer>();
            // Perform breadth-first traversal
            while (!queue.isEmpty()) {
                // Get the next node in the queue
                Node node = queue.remove();
                // Create a new List to store the neighbors of the current node
                List<Integer> neighbors = new ArrayList<Integer>();
                // Iterate through the neighbors of the current node
                for (Node neighbor : node.neighbors) {
                    // Append the neighbor's value to the List of neighbors
                    neighbors.add(visited.getOrDefault(neighbor, neighbor).data);
                    // Add the neighbor to the queue if it hasn't been visited yet
                    if (!visited.containsKey(neighbor) && !queue.contains(neighbor)) {
                        visited.put(neighbor, neighbor);
                        queue.add(neighbor);
                    }
                }
                // Sort the List of neighbors
                Collections.sort(neighbors);
                // Append the current node's value and its neighbors to the 2D List
                if (!nodeIndex.containsKey(node)) {
                    int index = graph.size();
                    nodeIndex.put(node, index);
                    List<Integer> sublist = new ArrayList<Integer>();
                    sublist.add(node.data);
                    sublist.addAll(neighbors);
                    graph.add(sublist);
                }
            }
            // Sort the graph by node value
            Collections.sort(graph, new Comparator<List<Integer>>() {
                public int compare(List<Integer> a, List<Integer> b) {
                    return a.get(0) - b.get(0);
                }
            });
            List<List<Integer>> result = new ArrayList<List<Integer>>();
            for (List<Integer> sublist : graph) {
                List<Integer> sub = new ArrayList<>(sublist.subList(1, sublist.size()));
                result.add(sub);
            }
            return result;
        }

        public static void printGraphRec(Node root, Set<Node> visitedNodes) {
            if (root == null || visitedNodes.contains(root)) {
                return;
            }

            visitedNodes.add(root);
            System.out.print("\t" + root.data + ": {");
            for (Node n : root.neighbors) {
                System.out.print(n.data + " ");
            }
            System.out.println("}");

            for (Node n : root.neighbors) {
                printGraphRec(n, visitedNodes);
            }
        }

        public static void printGraph(Node root) {
            Set<Node> visitedNodes = new HashSet<>();
            printGraphRec(root, visitedNodes);
        }
    }

    public static Node cloneHelper(Node root, Map<Node, Node> nodesCompleted) {
        // If the root node is None, return None
        if (root == null) {
            return null;
        }
        // Create a new Node with the same data as the root node
        Node clonedNode = new Node(root.data);
        // Add the root node and its clone to the nodesCompleted hash map
        nodesCompleted.put(root, clonedNode);
        // Iterate through the neighbors of the root node
        for (Node p : root.neighbors) {
            // Retrieve the value of key p in nodes_completed hash map.
            // If it exists, assign the corresponding cloned node to x.
            // This checks if neighbor node p has already been cloned.
            Node x = nodesCompleted.get(p);
            // If the neighbor is not cloned yet, recursively clone it
            if (x == null) {
                clonedNode.neighbors.add(cloneHelper(p, nodesCompleted));
            }
            // If the neighbor is already cloned, add the cloned neighbor to the new
            // node's neighbors
            else {
                clonedNode.neighbors.add(x);
            }
        }
        return clonedNode;
    }

    public static Node clone(Node root) {
        Map<Node, Node> nodesCompleted = new HashMap<>();
        return cloneHelper(root, nodesCompleted);
    }


    public static void main(String[] args) {
        int[][][] data = {
                {{2, 3}, {1, 3}, {1, 2}},
                {{2, 4}, {1, 3}, {2, 4}, {1, 3}},
                {{2, 5}, {1, 3}, {2, 4}, {3, 5}, {1, 4}},
                {{2}, {1}},
                {{2, 6}, {1, 3}, {2, 4}, {3, 5}, {4, 6}, {1, 5}},
                {{}}
        };

        for (int i = 0; i < data.length; i++) {
            Node node1 = GraphUtility.createGraph(data[i]);
            System.out.println((i + 1) + ".\t Original Graph: " + GraphUtility.create2DList(node1) + "\n");
            GraphUtility.printGraph(node1);
            System.out.println();
            Node clonedRoot = clone(node1);
            System.out.println("\t Cloned Graph: " + GraphUtility.create2DList(clonedRoot) + "\n");
            GraphUtility.printGraph(node1);
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}

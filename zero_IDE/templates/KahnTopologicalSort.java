import java.util.*;

/**
 * Kahn's Algorithm (In-Degree BFS) Topological Sort Template
 *
 * Invariants:
 * 1. Only vertices with in-degree == 0 can be executed/visited next.
 * 2. When a node is processed, decrement in-degree for all its neighbors.
 * 3. If processed count != total vertices, the graph contains at least one cycle.
 */
public class KahnTopologicalSort {

    public static int[] topologicalSort(int numNodes, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>(numNodes);
        for (int i = 0; i < numNodes; i++) graph.add(new ArrayList<>());
        int[] inDegree = new int[numNodes];

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph.get(u).add(v);
            inDegree[v]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numNodes; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        int[] order = new int[numNodes];
        int index = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            order[index++] = curr;

            for (int neighbor : graph.get(curr)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // Cycle detected if index != numNodes
        return (index == numNodes) ? order : new int[0];
    }
}

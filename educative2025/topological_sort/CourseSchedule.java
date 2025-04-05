package topological_sort;

import java.util.*;

public class CourseSchedule {

    List<Integer> sortedOrder = new LinkedList<>();

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        int counter = 0;
        if (numCourses <= 0) return false;

        // build graph of prerequisites
        // build indegree of prerequisites
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> indegree = new HashMap<>();

        for (int i = 0; i < numCourses; i++) {
            indegree.put(i, 0);
            graph.put(i, new ArrayList<>());
        }

        for (int i = 0; i < prerequisites.length; i++) {
            int parent = prerequisites[i][0];
            int child = prerequisites[i][1];
            indegree.put(child, indegree.get(child) + 1);
            graph.get(parent).add(child);
        }

        // bfs
        Queue<Integer> sources = new LinkedList<>();

        for (Map.Entry<Integer, Integer> entry : indegree.entrySet()) {
            if (entry.getValue() == 0) {
                sources.add(entry.getKey());
            }
        }

        while (!sources.isEmpty()) {
            int node = sources.poll();
            counter++;
            sortedOrder.add(node);
            for (Integer neighbour : graph.get(node)) {
                indegree.put(neighbour, indegree.get(neighbour) - 1);
                if (indegree.get(neighbour) == 0) {
                    sources.add(neighbour);
                }
            }
        }

        // topological sort is not possible if counter is not equal to numCourses, signifying there is a cycle in graph
        return counter == numCourses;

    }

    // CourseSchedule II -> return order of courses
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        canFinish(numCourses, prerequisites);
        return sortedOrder.stream().mapToInt(Integer::intValue).toArray();
    }


    static class CourseScheduleII {

        public static List<Integer> findOrder(int n, int[][] prerequisites) {
            List<Integer> sortedOrder = new ArrayList<>();
            // if n is smaller than or equal to zero we will return the empty array
            if (n <= 0)
                return sortedOrder;

            // Store the count of incoming prerequisites in a hashmap
            HashMap<Integer, Integer> inDegree = new HashMap<>();
            // a. Initialize the graph
            HashMap<Integer, List<Integer>> graph = new HashMap<>();
            for (int i = 0; i < n; i++) {
                inDegree.put(i, 0);
                graph.put(i, new ArrayList<>());
            }

            // b. Build the graph
            for (int i = 0; i < prerequisites.length; i++) {
                int parent = prerequisites[i][1], child = prerequisites[i][0];
                graph.get(parent).add(child); // put the child into it's parent's list
                inDegree.put(child, inDegree.get(child) + 1); // increment child's inDegree
            }

            // c. Find all sources i.e., all n with 0 in-degrees
            Queue<Integer> sources = new LinkedList<>();
            // traverse in inDegree using key
            for (Map.Entry<Integer, Integer> entry : inDegree.entrySet()) {
                if (entry.getValue() == 0)
                    sources.add(entry.getKey());
            }

            // d. For each source, add it to the sortedOrder and subtract one from all of its children's in-degrees
            // if a child's in-degree becomes zero, add it to the sources queue
            while (!sources.isEmpty()) {
                // pop an element from the start of the sources and store
                // the element in vertex
                int vertex = sources.poll();
                // append the vertex at the end of the sorted_order
                sortedOrder.add(vertex);
                List<Integer> children = graph.get(vertex); // get the node's children to decrement their in-degrees
                // traverse in graph[vertex] using child
                // get the node's children to decrement
                // their in-degrees
                for (int child : children) {
                    inDegree.put(child, inDegree.get(child) - 1);
                    // if inDegree[child] is 0 append the child in the deque sources
                    if (inDegree.get(child) == 0)
                        sources.add(child);
                }
            }

            if (sortedOrder.size() != n) // topological sort is not possible as the graph has a cycle
                return new ArrayList<>();

            return sortedOrder;
        }

        public static void main(String[] args) {
            // Driver code

            int[] n = {4, 5, 2, 4, 7};
            int[][][] prerequisites = {
                    {{1, 0}, {2, 0}, {3, 1}, {3, 2}},
                    {{1, 0}, {2, 0}, {3, 1}, {4, 3}},
                    {{1, 0}}, {{1, 0}, {2, 0}, {3, 1}, {3, 2}},
                    {{1, 0}, {0, 3}, {0, 2}, {3, 2}, {2, 5}, {4, 5}, {5, 6}, {2, 4}}};
            for (int i = 0; i < n.length; i++) {
                System.out.print(i + 1);
                System.out.println(".\tPrerequisites: " + Arrays.deepToString(prerequisites[i]) + "\n\tTotal number of courses, n = " + n[i]);
                List<Integer> result = findOrder(n[i], prerequisites[i]);
                System.out.println("\tValid courses order: " + result);
                System.out.println(new String(new char[100]).replace('\0', '-'));
            }

        }
    }

}

package graphs;

import java.util.*;

/***Course Schedule II (Medium)
 https://leetcode.com/problems/course-schedule-ii/

 Graph Valid Tree (Medium)
 https://leetcode.com/problems/graph-valid-tree/

 Minimum Height Trees (Medium)
 https://leetcode.com/problems/minimum-height-trees/

 Course Schedule III (Hard)
 https://leetcode.com/problems/course-schedule-iii/

 Build a Matrix With Conditions (Hard)
 https://leetcode.com/problems/build-a-matrix-with-conditions/

 Course Schedule (Medium)
 https://leetcode.com/problems/course-schedule/

 Alien Dictionary (Hard)
 https://leetcode.com/problems/alien-dictionary/

 Minimum Height Trees (Medium)
 https://leetcode.com/problems/minimum-height-trees/

 Sequence Reconstruction (Medium)
 https://leetcode.com/problems/sequence-reconstruction/

 Course Schedule III (Hard)
 https://leetcode.com/problems/course-schedule-iii/

 Parallel Courses (Medium)
 https://leetcode.com/problems/parallel-courses/

 Find All Possible Recipes from Given Supplies (Medium)
 https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies/

 Build a Matrix With Conditions (Hard)
 https://leetcode.com/problems/build-a-matrix-with-conditions/

 Sort Array by Moving Items to Empty Space (Hard)
 https://leetcode.com/problems/sort-array-by-moving-items-to-empty-space/
 ***/

// topological sort is a liner ordering of vertices in
// DAG such that for every directed edge between u to v,
// u comes before v in the ordering.

// Kahn's algorithm (BFS)
// DFS

public class CourseSchedule {

    List<Integer> sortedOrder = new LinkedList<>();

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        // topological sort
        // indegree 0 wale queue me daalo
        // phir unke neighbours ki indegree reduce krke queue me daalo
        // jab sab visit ho jae to order print krdo

        int n = numCourses;

        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }

        int[] indegree = new int[n + 1];

        for (int[] edge : prerequisites) {
            int parent = edge[0];
            int child = edge[1];
            indegree[child]++;
            graph.get(parent).add(child);
        }

        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        int counter = 0;
        while (!q.isEmpty()) {
            int curr = q.poll();
            counter++;
            sortedOrder.add(curr);
            for (Integer neigh : graph.get(curr)) {
                indegree[neigh]--;
                if (indegree[neigh] == 0) {
                    q.add(neigh);
                }
            }
        }
        return counter == n;
    }

    // CourseSchedule II -> return order of courses
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (canFinish(numCourses, prerequisites))
            return sortedOrder.stream().mapToInt(Integer::intValue).toArray();
        else
            return new int[]{};
    }


    /**
     * Course Schedule II (Medium)
     * https://leetcode.com/problems/course-schedule-ii
     */
    public static class CourseScheduleIi {
        public int[] findOrder(int numCourses, int[][] prerequisites) {

            int n = numCourses;
            LinkedList<Integer> sortedOrder = new LinkedList<>();
            Map<Integer, List<Integer>> graph = new HashMap<>();

            for (int i = 0; i < n; i++) {
                graph.put(i, new ArrayList<>());
            }

            int[] indegree = new int[n + 1];

            for (int[] edge : prerequisites) {
                int parent = edge[0];
                int child = edge[1];
                indegree[child]++;
                graph.get(parent).add(child);
            }

            Queue<Integer> q = new LinkedList<>();

            for (int i = 0; i < n; i++) {
                if (indegree[i] == 0) {
                    q.add(i);
                }
            }

            int counter = 0;
            while (!q.isEmpty()) {
                int curr = q.poll();
                counter++;
                sortedOrder.add(curr);
                for (Integer neigh : graph.get(curr)) {
                    indegree[neigh]--;
                    if (indegree[neigh] == 0) {
                        q.add(neigh);
                    }
                }
            }
            return counter == n ? sortedOrder.stream().mapToInt(Integer::intValue).toArray() : new int[]{};

        }
    }

    /**
     * Graph Valid Tree (Medium)
     * https://leetcode.com/problems/graph-valid-tree
     */
    public static class GraphValidTree {

        // represent a graph

        // do dfs

        // if dfs represent that cycle exist or not

        // all components should be connected to single component
        List<List<Integer>> adj = new ArrayList<>();

        public boolean validTree(int n, int[][] edges) {
            if (edges.length > n - 1) {
                return false;
            }

            for (int i = 0; i < n; i++) {
                adj.add(new ArrayList<>());
            }

            for (int[] edge : edges) {
                int parent = edge[0];
                int child = edge[1];
                adj.get(parent).add(child);
                adj.get(child).add(parent);
            }

            Set<Integer> visited = new HashSet<>();

            if (!dfs(0, -1, visited)) {
                return false;
            }
            return visited.size() == n;
        }

        private boolean dfs(int src, int parent, Set<Integer> visited) {
            if (visited.contains(src)) {
                return false;
            }
            visited.add(src);
            //the nei == parent check skips the edge you came from when doing DFS on an
            // undirected graph. Without it, the neighbor that is the parent is already
            // visited and would be mistaken for a cycle.
            // Skipping the parent prevents a false positive cycle
            // detection and allows DFS to correctly explore other neighbors and verify connectivity.

            for (int nei : adj.get(src)) {
                if (nei == parent) {
                    continue;
                }
                if (!dfs(nei, src, visited)) {
                    return false;
                }
            }
            return true;
        }

    }

    /**
     * Minimum Height Trees (Medium)
     * https://leetcode.com/problems/minimum-height-trees
     */
    public static class MinimumHeightTrees {
        public List<Integer> findMinHeightTrees(int n, int[][] edges) {

            if (n < 2) {
                return Collections.singletonList(0);
            }

            // graph
            List<Set<Integer>> adj = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                adj.add(new HashSet<>());
            }
            for (int[] edge : edges) {
                adj.get(edge[0]).add(edge[1]);
                adj.get(edge[1]).add(edge[0]);
            }

            List<Integer> leaves = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (adj.get(i).size() == 1) {
                    leaves.add(i);
                }
            }

            while (n > 2) {
                n = n - leaves.size();
                List<Integer> newLeaves = new ArrayList<>();
                for (int i : leaves) {
                    int j = adj.get(i).iterator().next();
                    adj.get(j).remove(i);
                    if (adj.get(j).size() == 1) {
                        newLeaves.add(j);
                    }
                }
                leaves = newLeaves;
            }

            return leaves;
        }
    }

    /**
     * Course Schedule III (Hard)
     * https://leetcode.com/problems/course-schedule-iii
     */
    public static class CourseScheduleIii {
        public int scheduleCourse(int[][] courses) {

            // sort by deadline
            Arrays.sort(courses, (a, b) -> (a[1] - b[1]));

            PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> (b - a));

            int time = 0;
            for (int[] c : courses) {
                time += c[0];
                maxHeap.add(c[0]);
                if (!maxHeap.isEmpty() && time > c[1]) {
                    time -= maxHeap.poll();
                }
            }

            return maxHeap.size();
        }
    }

    /**
     * Build a Matrix With Conditions (Hard)
     * https://leetcode.com/problems/build-a-matrix-with-conditions
     */
    public static class BuildAMatrixWithConditions {

        // get topological order for rows and columns based on conditions

        // if either order is invalid, return empty matrix

        // store row & column positions for each number

        // place each number in matrix based on row & column positions


        public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
            List<Integer> rowOrder = topologicalSort(k, rowConditions);
            List<Integer> colOrder = topologicalSort(k, colConditions);

            if (rowOrder.isEmpty() || colOrder.isEmpty()) {
                return new int[0][0];
            }

            int[][] matrix = new int[k][k];
            Map<Integer, Integer> posRow = new HashMap<>();
            Map<Integer, Integer> posCol = new HashMap<>();

            for (int i = 0; i < rowOrder.size(); i++) {
                posRow.put(rowOrder.get(i), i);
            }

            for (int i = 0; i < colOrder.size(); i++) {
                posCol.put(colOrder.get(i), i);
            }
            for (int num = 1; num <= k; num++) {
                if (posRow.containsKey(num) && posCol.containsKey(num)) {
                    matrix[posRow.get(num)][posCol.get(num)] = num;
                }
            }
            return matrix;
        }

        private List<Integer> topologicalSort(int n, int[][] edges) {
            Map<Integer, List<Integer>> adj = new HashMap<>();
            List<Integer> order = new ArrayList<>();
            int[] visited = new int[n + 1];
            for (int[] edge : edges) {
                adj.computeIfAbsent(edge[0], k -> new ArrayList<>())
                        .add(edge[1]);
            }
            for (int i = 1; i <= n; i++) {
                if (visited[i] == 0 && dfs(i, adj, visited, order)) {
                    return Collections.emptyList();
                }
            }
            // dfs puts in stack
            Collections.reverse(order);
            return order;
        }

        private boolean dfs(int src, Map<Integer,
                List<Integer>> graph, int[] visited, List<Integer> order) {
            visited[src] = 1;
            if (graph.containsKey(src)) {
                for (int neigh : graph.get(src)) {
                    if (visited[neigh] == 0) {
                        if (dfs(neigh, graph, visited, order)) {
                            return true;
                        }
                    } else if (visited[neigh] == 1) {
                        return true;
                    }
                }
            }
            visited[src] = 2;
            order.add(src);
            return false;
        }

    }

    /**
     * Alien Dictionary (Hard)
     * https://leetcode.com/problems/alien-dictionary
     */
    public static class AlienDictionary {
        private final Map<Character, Set<Character>> adjList = new HashMap<>();
        private final Map<Character, Integer> inDegree = new HashMap<>();
        private final Set<Character> uniqueCharacters = new HashSet<>();

        public String alienOrder(List<String> words) {
            if (words == null || words.isEmpty()) {
                return "";
            }
            // initialize all unique characters
            for (String word : words) {
                for (char c : word.toCharArray()) {
                    uniqueCharacters.add(c);
                    inDegree.putIfAbsent(c, 0);
                    adjList.putIfAbsent(c, new HashSet<>());
                }
            }
            // build graph from word pair comparisons
            for (int i = 0; i < words.size() - 1; i++) {
                String curr = words.get(i);
                String next = words.get(i + 1);
                if (curr.length() > next.length() && curr.startsWith(next)) {
                    return "";
                }
                int minLen = Math.min(curr.length(), next.length());
                for (int j = 0; j < minLen; j++) {
                    char from = curr.charAt(j);
                    char to = next.charAt(j);
                    if (from != to) {
                        if (adjList.get(from).add(to)) {
                            inDegree.put(to, inDegree.getOrDefault(to, 0) + 1);
                        }
                        break;
                    }
                }
            }
            // apply topological sort to get character ordering
            Queue<Character> queue = new ArrayDeque<>();
            StringBuilder result = new StringBuilder();
            for (char c : uniqueCharacters) {
                if (inDegree.get(c) == 0) {
                    queue.add(c);
                }
            }
            while (!queue.isEmpty()) {
                char curr = queue.poll();
                result.append(curr);
                for (char neighbour : adjList.getOrDefault(curr, Collections.emptySet())) {
                    inDegree.put(neighbour, inDegree.getOrDefault(neighbour, 0) - 1);
                    if (inDegree.getOrDefault(neighbour, 0) == 0) {
                        queue.add(neighbour);
                    }
                }
            }
            if (result.length() < uniqueCharacters.size()) {
                return "";
            }
            return result.toString();
        }
    }

    /**
     * Sequence Reconstruction (Medium)
     * https://leetcode.com/problems/sequence-reconstruction
     */
    public static class SequenceReconstruction {
        public boolean sequenceReconstruction(int[] nums, List<List<Integer>> sequences) {

            int n = nums.length;

            int[] indegree = new int[n];
            List<Integer>[] adjList = new List[n];

            Arrays.setAll(adjList, HIndex -> new ArrayList<>());

            for (List<Integer> sequence : sequences) {
                for (int i = 1; i < sequence.size(); i++) {
                    int from = sequence.get(i - 1) - 1;
                    int to = sequence.get(i) - 1;
                    adjList[from].add(to);
                    indegree[to]++;
                }
            }

            Deque<Integer> queue = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                if (indegree[i] == 0) {
                    queue.add(i);
                }
            }

            while (queue.size() == 1) {
                int curr = queue.poll();
                for (int neighbor : adjList[curr]) {
                    indegree[neighbor]--;
                    if (indegree[neighbor] == 0) {
                        queue.add(neighbor);
                    }
                }
            }

            return queue.isEmpty();
        }
    }

    /**
     * Parallel Courses (Medium)
     * https://leetcode.com/problems/parallel-courses
     */
    public static class ParallelCourses {
        // TC: O(n+m)
        // building graph & indegree of m relations - O(m)
        // zero indegree scanning of n nodes - O(n)
        // BFS -> visit each node exactly once - O(n+m)
        public int minimumSemesters(int n, int[][] relations) {
            // build adjacency list for graph
            List<Integer>[] adj = new List[n];
            Arrays.setAll(adj, index -> new ArrayList<>());
            // indegree array
            int[] indegree = new int[n];
            for (int[] relation : relations) {
                int pre = relation[0] - 1;
                int next = relation[1] - 1;
                adj[pre].add(next);
                indegree[next]++;
            }
            // topological sort using BFS kahn's algorithm
            Deque<Integer> queue = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                if (indegree[i] == 0) {
                    queue.offer(i);
                }
            }
            int remaining = n;
            int semesters = 0;
            while (!queue.isEmpty()) {
                semesters++;
                int currSize = queue.size();
                for (int i = 0; i < currSize; i++) {
                    remaining--;
                    int curr = queue.poll();
                    for (int dependent : adj[curr]) {
                        indegree[dependent]--;
                        if (indegree[dependent] == 0) {
                            queue.offer(dependent);
                        }
                    }
                }
            }
            return remaining == 0 ? semesters : -1;
        }
    }

    /**
     * Find All Possible Recipes from Given Supplies (Medium)
     * https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies
     */
    public static class FindAllPossibleRecipesFromGivenSupplies {

        public List<String> findAllRecipes(String[] recipes,
                                           List<List<String>> ingredients,
                                           String[] supplies) {
            Map<String, List<String>> graph = new HashMap<>();
            Map<String, Integer> indegree = new HashMap<>();
            Set<String> recipeSet = new HashSet<>();

            // build graph
            // put supply in graph
            for (String supply : supplies) {
                graph.putIfAbsent(supply, new ArrayList<>());
                indegree.putIfAbsent(supply, 0);
            }

            // put recipe in graph
            for (String recipe : recipes) {
                graph.putIfAbsent(recipe, new ArrayList<>());
                indegree.putIfAbsent(recipe, 0);
                recipeSet.add(recipe);
            }

            // put ingredients in graph
            for (int i = 0; i < ingredients.size(); i++) {
                for (String curr : ingredients.get(i)) {
                    graph.putIfAbsent(curr, new ArrayList<>());
                    graph.get(curr).add(recipes[i]);
                    indegree.put(recipes[i], indegree.getOrDefault(recipes[i], 0) + 1);
                }
            }

            // topological sort
            Queue<String> queue = new ArrayDeque<>();
            List<String> result = new ArrayList<>();

            for (String curr : indegree.keySet()) {
                if (indegree.get(curr) == 0) {
                    queue.add(curr);
                }
            }

            while (!queue.isEmpty()) {
                String curr = queue.poll();
                if (recipeSet.contains(curr)) {
                    result.add(curr);
                }
                for (String neighbour : graph.get(curr)) {
                    indegree.put(neighbour, indegree.getOrDefault(neighbour, 0) - 1);
                    if (indegree.getOrDefault(neighbour, 0) == 0) {
                        queue.add(neighbour);
                    }
                }
            }

            return result;
        }

    }

    /**
     * Sort Array by Moving Items to Empty Space (Hard)
     * https://leetcode.com/problems/sort-array-by-moving-items-to-empty-space
     */
    public static class SortArrayByMovingItemsToEmptySpace {
        public int sortArray(int[] nums) {
            int n = nums.length;
            int[] shiftedArray = new int[n];
            for (int i = 0; i < n; i++) {
                shiftedArray[i] = (nums[i] - 1 + n) % n;
            }
            int directSortOps = sortOps(nums, 0);
            int cyclicSortOps = sortOps(shiftedArray, n - 1);
            return Math.min(directSortOps, cyclicSortOps);
        }

        private int sortOps(int[] nums, int k) {
            boolean[] visited = new boolean[nums.length];
            int swap = 0;
            for (int i = 0; i < nums.length; i++) {
                if (i == nums[i] || visited[i]) {
                    continue;
                }
                swap++;
                int currPos = nums[i];
                while (!visited[currPos]) {
                    visited[currPos] = true;
                    swap++;
                    currPos = nums[currPos];
                }
            }
            if (nums[k] != k) {
                swap -= 2;
            }
            return swap;
        }
    }
}

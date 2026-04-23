import java.util.*;

public class AdvancedGraphs {

    // alien dictionary

    // words are given in lexicographically sorted order in alien language
    // you have to give the alphabets order for alien language

    // input: ["hrn","hrf","er","enn","rfnn"]
    // output: "hernf"

    public String alienDictionary(List<String> words) {
        // grpah
        // unique chars
        // indegree

        Map<Character, Set<Character>> adjList = new HashMap<>();
        Set<Character> uniqueCharacters = new HashSet<>();
        Map<Character, Integer> indegree = new HashMap<>();

        if (words == null || words.isEmpty()) return "";

        for (String w : words) {
            for (char c : w.toCharArray()) {
                uniqueCharacters.add(c);
                indegree.putIfAbsent(c, 0);
                adjList.putIfAbsent(c, new HashSet<>());
            }
        }

        for (int i = 0; i < words.size() - 1; i++) {
            String curr = words.get(i);
            String next = words.get(i + 1);
            if (curr.length() > next.length() && curr.startsWith(next)) {
                return "";
            }
            int minLen = Math.min(curr.length(), next.length());

            for (int j = 0; j < minLen; j++) {
                char u = curr.charAt(j);
                char v = curr.charAt(j);
                if (u != v) {
                    if (adjList.get(u).add(v)) {
                        indegree.put(v, indegree.getOrDefault(v, 0) + 1);
                    }
                    break;
                }
            }
        }

        Queue<Character> queue = new LinkedList<>();

        StringBuilder res = new StringBuilder();
        for (char c : uniqueCharacters) {
            if (indegree.get(c) == 0) {
                queue.add(c);
            }
        }

        while (!queue.isEmpty()) {
            char curr = queue.poll();
            res.append(curr);
            for (char neigh : adjList.getOrDefault(curr, Collections.emptySet())) {
                indegree.put(neigh, indegree.getOrDefault(neigh, 0) - 1);
                if (indegree.get(neigh) == 0) {
                    queue.add(neigh);
                }
            }
        }

        if (res.length() < uniqueCharacters.size()) {
            return "";
        }

        return res.toString();
    }

    // Cheapest Flights Within K Stops
    public int findCheapestPrice(int n,
                                 int[][] flights,
                                 int src,
                                 int dest,
                                 int k) {

        int[] prices = new int[n];
        Arrays.fill(prices, Integer.MAX_VALUE);

        prices[src] = 0;

        List<int[]>[] adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] flight : flights) {
            adj[flight[0]].add(new int[]{flight[1], flight[2]});
        }

        Queue<int[]> queue = new LinkedList<>();

        // {cost, src, stops}
        queue.offer(new int[]{0, src, 0});

        while (!queue.isEmpty()) {

            int[] curr = queue.poll();
            int cost = curr[0];
            int node = curr[1];
            int stops = curr[2];

            if (stops > k) {
                continue;
            }

            for (int[] neigh : adj[node]) {
                int nei = neigh[0];
                int w = neigh[1];
                int newCost = cost + w;
                if (newCost < prices[nei]) {
                    prices[nei] = newCost;
                    queue.offer(new int[]{newCost, nei, stops + 1});
                }
            }
        }

        return prices[dest] == Integer.MAX_VALUE ? -1 : prices[dest];

    }

    // Minimum cost to connect pairs
    // this is a minimum spanning problem solve using Prim's Algorithm
    public int minCostConnectPoints(int[][] points) {

        int n = points.length;
        boolean[] visited = new boolean[n];

        PriorityQueue<Edge> minHeap = new PriorityQueue<>(Comparator.comparingInt(x -> x.cost));
        int[] p1 = points[0];

        for (int i = 0; i < n; i++) {
            int[] p2 = points[i];
            int cost = getCost(p1[0], p2[0], p1[1], p2[1]);
            minHeap.add(new Edge(0, i, cost));
        }

        visited[0] = true;
        int minCost = 0;
        int c = n - 1;

        while (!minHeap.isEmpty() && c > 0) {
            Edge curr = minHeap.poll();
            int y = curr.y;
            int cost = curr.cost;
            if (!visited[y]) {
                minCost += cost;
                visited[y] = true;
                int[] np1 = points[y];
                for (int i = 0; i < n; i++) {
                    if (!visited[i]) {
                        int[] np2 = points[i];
                        int d = getCost(np1[0], np2[0], np1[1], np2[1]);
                        minHeap.add(new Edge(y, i, d));
                    }
                }
                c--;
            }
        }
        return minCost;
    }

    public int getCost(int x1, int x2, int y1, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    // Network delay time
    // positive weights -> single source -> Dijkstra
    public int networkDelayTime(int[][] times, int n, int k) {

        // graph
        Map<Integer, List<int[]>> adjList = new HashMap<>();

        // priority queue - min heap - element - {timeSoFar, node}
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        Set<Integer> visited = new HashSet<>();

        for (int[] time : times) {
            int src = time[0];
            int dest = time[1];
            int w = time[2];
            adjList.computeIfAbsent(src, x -> new ArrayList<>()).add(new int[]{dest, w});
        }

        minHeap.add(new int[]{0, k});

        int delay = 0;

        while (!minHeap.isEmpty()) {
            int[] curr = minHeap.poll();
            int t = curr[0];
            int node = curr[1];
            if (visited.contains(node)) {
                continue;
            }
            visited.add(node);
            delay = Math.max(delay, t);
            List<int[]> neighbours = adjList.getOrDefault(node, new ArrayList<>());
            for (int[] neighbour : neighbours) {
                int neighbourNode = neighbour[0];
                int neighbourTime = neighbour[1];
                if (!visited.contains(neighbourNode)) {
                    int newTime = t + neighbourTime;
                    minHeap.offer(new int[]{newTime, neighbourNode});
                }
            }
        }

        if (visited.size() == n) {
            return delay;
        }

        return -1;

    }

    // Reconstruct Flight Path
    // Eulerian path - a path that travels along every edge exactly once
    public List<String> findItinerary(List<List<String>> tickets) {

        List<String> res = new ArrayList<>();
        Map<String, List<String>> adj = new HashMap<>();

        for (List<String> ticket : tickets) {
            adj.putIfAbsent(ticket.get(0), new ArrayList<>());
            adj.get(ticket.get(0)).add(ticket.get(1));
        }

        for (List<String> destinations : adj.values()) {
            destinations.sort(Collections.reverseOrder());
        }

        dfsItr("JFK", adj, res);
        Collections.reverse(res);
        return res;
    }

    private void dfsItr(String src, Map<String, List<String>> adj, List<String> res) {
        List<String> destinations = adj.getOrDefault(src, new ArrayList<>());
        while (destinations != null && !destinations.isEmpty()) {
            String nextDest = destinations.remove(destinations.size() - 1);
            dfsItr(nextDest, adj, res);
        }
        res.add(src);
    }

    // Swim In Rising Water
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        boolean[][] visited = new boolean[n][n];
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(t -> t.time));
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        pq.add(new Node(0, 0, grid[0][0]));
        visited[0][0] = true;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int x = node.x;
            int y = node.y;
            int time = node.time;
            if (x == n - 1 && y == n - 1) {
                return time;
            }
            for (int[] dir : directions) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n || visited[nx][ny]) {
                    continue;
                }
                visited[nx][ny] = true;
                pq.offer(new Node(nx, ny, Math.max(time, grid[nx][ny])));
            }
        }
        return n * n;
    }

    public static class Node {
        int x, y, time;

        public Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public static class Edge {
        int x, y, cost;

        public Edge(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }
}

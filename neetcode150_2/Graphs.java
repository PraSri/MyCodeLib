import java.util.*;

public class Graphs {

    // number of islands - done in 5 mins


    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    // Course schedule I & II
    List<Integer> sortedOrder = new LinkedList<>();

    public int numIslands(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, i, j, n, m);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(int[][] grid, int i, int j, int n, int m) {
        if (i < 0 || j < 0 || i >= n || j >= m || grid[i][j] == 0) {
            return;
        }
        grid[i][j] = 0;
        dfs(grid, i - 1, j, n, m);
        dfs(grid, i, j - 1, n, m);
        dfs(grid, i + 1, j, n, m);
        dfs(grid, i, j + 1, n, m);
    }

    // max area of island - done in 5 mins
    public int maxAreaIsland(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int maxArea = 0;
        int area = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, i, j, n, m, area);
                    maxArea = Math.max(maxArea, area);
                    area = 0;
                }
            }
        }
        return maxArea;
    }

    private void dfs(int[][] grid, int i, int j, int n, int m, int area) {
        if (i < 0 || j < 0 || i >= n || j >= m || grid[i][j] == 0) {
            return;
        }
        if (grid[i][j] == 1) {
            area++;
        }
        grid[i][j] = 0;
        dfs(grid, i - 1, j, n, m, area);
        dfs(grid, i, j - 1, n, m, area);
        dfs(grid, i + 1, j, n, m, area);
        dfs(grid, i, j + 1, n, m, area);
    }

    // clone a graph
    // need attention!!! -> spend more than 8 mins -> without solving
    public Node cloneGraph(Node node) {
        Map<Integer, Node> map = new HashMap<>();
        return dfs(node, map);
    }

    private Node dfs(Node src, Map<Integer, Node> map) {
        if (src == null) {
            return src;
        }
        if (map.containsKey(src.val)) {
            return map.get(src.val);
        }
        Node newNode = new Node(src.val);
        map.put(src.val, newNode);
        for (Node neighbour : src.neighbours) {
            newNode.neighbours.add(dfs(neighbour, map));
        }
        return newNode;
    }

    // Island and Treasure -> took around 9 mins - poor - practice more
    public void islandsAndTreasure(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    queue.add(new int[]{i, j});
                }
            }
        }
        if (queue.isEmpty()) {
            return;
        }
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int[] dir : dirs) {
                int nx = curr[0] + dir[0];
                int ny = curr[1] + dir[1];
                if (nx < 0 || ny < 0 || nx >= n || ny >= m || grid[nx][ny] != 2147483647) {
                    continue;
                }
                queue.add(new int[]{nx, ny});
                grid[nx][ny] = grid[curr[0]][curr[1]] + 1;
            }
        }
    }

    // Rotting oranges - 7 mins - one critical bug,
    // while polling from queue, need to iterate over
    // all the elements of queue
    public int orangesRotting(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int t = 0;
        int fo = 0;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    fo++;
                } else if (grid[i][j] == 2) {
                    queue.add(new int[]{i, j});
                }
            }
        }
        if (fo == 0) {
            return 0;
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int x = curr[0];
                int y = curr[1];
                for (int[] dir : dirs) {
                    int nx = x + dir[0];
                    int ny = y + dir[1];
                    if (nx < 0 || ny < 0 || nx >= n || ny >= m || grid[nx][ny] != 1) {
                        continue;
                    }
                    grid[nx][ny] = 2; // remember to mark it 2 otherwise it will keep picking in each iteration and give infinite loop
                    queue.add(new int[]{nx, ny});
                    fo--;
                }
            }
            t++;
        }

        return fo == 0 ? t - 1 : -1;

    }

    // Pacific Atlantic Water Flow - > 20 mins after knowing the solution --- Practice it
    public List<List<Integer>> pacificAtlantic(int[][] heights) {

        boolean[][] visitedP, visitedA;
        int n = heights.length;
        int m = heights[0].length;
        visitedA = new boolean[n][m];
        visitedP = new boolean[n][m];
        Queue<int[]> queueA = new LinkedList<>();
        Queue<int[]> queueP = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            queueA.add(new int[]{i, m - 1});
            visitedA[i][m - 1] = true;
            queueP.add(new int[]{i, 0});
            visitedP[i][0] = true;
        }

        for (int j = 0; j < m; j++) {
            queueA.add(new int[]{n - 1, j});
            visitedA[n - 1][0] = true;
            queueP.add(new int[]{0, j});
            visitedP[0][j] = true;
        }

        bfs(heights, queueA, n, m, visitedA);
        bfs(heights, queueP, n, m, visitedP);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visitedA[i][j] && visitedP[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }
        return res;
    }

    private void bfs(int[][] heights, Queue<int[]> queue, int n, int m, boolean[][] visited) {
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int x = curr[0];
                int y = curr[1];
                for (int[] dir : dirs) {
                    int nx = x + dir[0];
                    int ny = y + dir[1];
                    if (nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny] || heights[nx][ny] < heights[x][y]) {
                        continue;
                    }
                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny});
                }
            }
        }
    }

    // surrounded regions - not able to think of solution - after knowing the solution took 11 mins to implement
    public void solve(char[][] board) {

        int n = board.length;
        int m = board[0].length;

        for (int i = 0; i < n; i++) {
            dfsRegions(board, i, 0);
            dfsRegions(board, i, m - 1);
        }

        for (int j = 0; j < m; j++) {
            dfsRegions(board, 0, j);
            dfsRegions(board, n - 1, j);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '*') {
                    board[i][j] = 'O';
                }
            }
        }


    }

    private void dfsRegions(char[][] board, int i, int j) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] == '*') {
            return;
        }

        board[i][j] = '*';
        dfsRegions(board, i, j - 1);
        dfsRegions(board, i, j + 1);
        dfsRegions(board, i - 1, j);
        dfsRegions(board, i + 1, j);
    }

    public boolean canFinish(int n, int[][] a) {
        // topological sort - DAG
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
        int[] indegree = new int[n + 1];

        for (int[] edge : a) {
            int parent = edge[0];
            int child = edge[1];
            indegree[child]++;
            graph.get(parent).add(child);
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        int counter = 0;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            counter++;
            sortedOrder.add(curr);
            for (int neigh : graph.get(curr)) {
                indegree[neigh]--;
                if (indegree[neigh] == 0) {
                    queue.add(neigh);
                }
            }
        }
        return counter == n;

    }

    // course schedule II -> valid ordering
    public int[] validOrdering(int n, int[][] pre) {
        return sortedOrder.stream().mapToInt(Integer::intValue).toArray();
    }

    // Graph Valid Tree
    public boolean validTree(int n, int[][] edges) {
        if (edges.length > n - 1) {
            return false;
        }
        List<List<Integer>> adj = new ArrayList<>();
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
        if (!dfs(0, -1, visited, adj)) {
            return false;
        }
        return visited.size() == n;

    }

    private boolean dfs(int node, int parent, Set<Integer> visited, List<List<Integer>> adj) {
        if (visited.contains(node)) {
            return false;
        }
        visited.add(node);
        for (int neigh : adj.get(node)) {
            if (neigh == parent) {
                continue;
            }
            if (!dfs(neigh, node, visited, adj)) {
                return false;
            }
        }
        return true;
    }

    // Number of connected graphs in undirected graph

    public int countComponents(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfsConnect(adj, visited, i);
                res++;
            }
        }
        return res;
    }

    private void dfsConnect(List<List<Integer>> adj, boolean[] visited, int node) {
        visited[node] = true;
        for (int neigh : adj.get(node)) {
            if (!visited[neigh]) {
                dfsConnect(adj, visited, neigh);
            }
        }
    }

    // word ladder
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        if (!set.contains(endWord)) {
            return 0;
        }
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int len = 0;

        while (!queue.isEmpty()) {
            len++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                for (int j = 0; j < curr.length(); j++) {
                    String alpha = "abcdefghijklmnopqrstuvwxyz";
                    for (int k = 0; k < alpha.length(); k++) {
                        char temp[] = curr.toCharArray();
                        temp[j] = alpha.charAt(k);
                        String newWord = new String(temp);
                        if (newWord.equals(endWord)) {
                            return len + 1;
                        }
                        if (set.contains(newWord)) {
                            queue.add(newWord);
                            set.remove(newWord);
                        }
                    }
                }
            }
        }

        return len;
    }

    public static class UnionFind {
        int[] parent;
        int[] rank;

        public UnionFind(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int v) {
            if (parent[v] != v) {
                parent[v] = find(parent[v]);
            }
            return parent[v];
        }

        public boolean union(int v1, int v2) {
            int p1 = find(v1);
            int p2 = find(v2);
            if (p1 == p2) {
                return false;
            } else if (rank[p1] > rank[p2]) {
                parent[p2] = p1;
                rank[p1] = rank[p1] + rank[p2];
            } else {
                parent[p1] = p2;
                rank[p2] = rank[p1] + rank[p2];
            }
            return true;
        }

        // redundant connections
        public int[] findRedundantConnections(int[][] edges) {
            UnionFind unionFind = new UnionFind(edges.length);
            for (int[] edge : edges) {
                if (!unionFind.union(edge[0], edge[1])) {
                    return edge;
                }
            }
            return new int[0];
        }
    }

    class Node {
        public int val;
        public List<Node> neighbours;

        public Node() {
            val = 0;
            neighbours = new ArrayList<>();
        }

        public Node(int val) {
            this.val = val;
            neighbours = new ArrayList<>();
        }

        public Node(int val, List<Node> neighbours) {
            this.val = val;
            this.neighbours = neighbours;
        }
    }

}

package graphs;

import java.util.ArrayList;
import java.util.List;

public class ReorderRoutesToMakeAllPathsLeadToTheCityZero {

    public int minReorder(int n, int[][] connections) {

        // build a graph
        List<List<int[]>> graph = new ArrayList<>();
        for(int i =0;i<n;i++) {
            graph.add(new ArrayList<>());
        }

        for(int[] conn: connections) {
            int a = conn[0];
            int b = conn[1];
            graph.get(a).add(new int[]{b, 1});
            graph.get(b).add(new int[]{a, 0});
        }

        boolean[] visited = new boolean[n];

        int result = dfs(0, graph, visited);

        return result;

    }

    private int dfs(int city, List<List<int[]>> graph, boolean[] visited) {
        visited[city] = true;
        int reversals = 0;
        for(int[] connection : graph.get(city)) {
            int neighbour = connection[0];
            int needReversal = connection[1];
            if(!visited[neighbour]) {
                reversals += needReversal;
                reversals += dfs(neighbour, graph, visited);
            }
        }
        return reversals;
    }

}

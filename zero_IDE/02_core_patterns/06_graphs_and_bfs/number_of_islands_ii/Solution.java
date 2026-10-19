import java.util.*;

public class Solution {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> result = new ArrayList<>();
        if (m <= 0 || n <= 0 || positions == null) return result;

        int[] parent = new int[m * n];
        Arrays.fill(parent, -1); // -1 indicates water

        int count = 0;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int[] pos : positions) {
            int r = pos[0], c = pos[1];
            int idx = r * n + c;

            // If already land, count does not change
            if (parent[idx] != -1) {
                result.add(count);
                continue;
            }

            parent[idx] = idx;
            count++;

            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                int neighborIdx = nr * n + nc;

                if (nr >= 0 && nr < m && nc >= 0 && nc < n && parent[neighborIdx] != -1) {
                    int rootA = find(parent, idx);
                    int rootB = find(parent, neighborIdx);
                    if (rootA != rootB) {
                        parent[rootA] = rootB;
                        count--;
                    }
                }
            }

            result.add(count);
        }

        return result;
    }

    private int find(int[] parent, int i) {
        while (i != parent[i]) {
            parent[i] = parent[parent[i]]; // Path halving
            i = parent[i];
        }
        return i;
    }
}

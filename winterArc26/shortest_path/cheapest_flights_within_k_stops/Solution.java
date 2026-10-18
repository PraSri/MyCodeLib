package shortest_path.cheapest_flights_within_k_stops;

import java.util.Arrays;

public class Solution {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] cost = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[src] = 0;
        for (int round = 0; round <= k; round++) {
            int[] next = cost.clone();
            for (int[] f : flights) {
                if (cost[f[0]] != Integer.MAX_VALUE && cost[f[0]] + f[2] < next[f[1]]) {
                    next[f[1]] = cost[f[0]] + f[2];
                }
            }
            cost = next;
        }
        return cost[dst] == Integer.MAX_VALUE ? -1 : cost[dst];
    }
}

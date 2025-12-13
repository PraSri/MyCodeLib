package advancedgraphs;

import java.util.*;

public class CheapestFlightsWithinKStops {
        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            // initialize with price with INF
            // set src price as 0
            // initialize graph as adjacency list
            // fill the graph
            // take a queue with type int[]
            // put the tuple {cost=0, src, stops=0} in queue
            // iterate the queue till not empty
            int[] prices = new int[n];
            Arrays.fill(prices, Integer.MAX_VALUE);
            prices[src] = 0;
            List<int[]>[] adj = new ArrayList[n];
            for(int i = 0;i<n;i++) {
                adj[i] = new ArrayList<>();
            }
            for(int[] flight: flights) {
                adj[flight[0]].add(new int[]{flight[1], flight[2]});
            }
            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[]{0, src, 0});
            while(!q.isEmpty()) {
                int[] curr = q.poll();
                int cost = curr[0];
                int node = curr[1];
                int stops = curr[2];
                if(stops > k) {
                    continue;
                }
                for(int[] neigh: adj[node]) {
                    int nei = neigh[0];
                    int w = neigh[1];
                    int newCost = cost + w;
                    if(newCost < prices[nei]) {
                        prices[nei] = newCost;
                        q.offer(new int[]{newCost, nei, stops + 1});
                    }
                }
            }
            return prices[dst] == Integer.MAX_VALUE ? -1 : prices[dst];
        }
}

package graphs;

import java.util.*;
import java.util.Map;

public class NetworkDelayTime {

    public int networkDelayTime(int[][] times, int n, int k) {

        Map<Integer, List<int[]>> adjacency = new HashMap<>();
        for (int[] time : times) {
            int source = time[0];
            int dest = time[1];
            int travelTime = time[2];
            adjacency.computeIfAbsent(source, key -> new ArrayList<>()).add(new int[]{dest, travelTime});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, k});

        Set<Integer> visited = new HashSet<>();

        int delays = 0;

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int time = current[0];
            int node = current[1];
            if (visited.contains(node)) continue;
            visited.add(node);
            delays = Math.max(delays, time);
            List<int[]> neighbours = adjacency.getOrDefault(node, new ArrayList<>());
            for (int[] neighbour : neighbours) {
                int neighbourNode = neighbour[0];
                int neighbourTime = neighbour[1];
                if (!visited.contains(neighbourNode)) {
                    int newTime = time + neighbourTime;
                    pq.offer(new int[]{newTime, neighbourNode});
                }
            }

        }

        if (visited.size() == n) return delays;

        return -1;

    }

}

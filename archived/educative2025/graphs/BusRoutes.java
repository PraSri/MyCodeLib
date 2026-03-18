package graphs;

import java.util.*;

public class BusRoutes {

    public int numBusesToDestination(int[][] routes, int source, int target) {

        Map<Integer, List<Integer>> adjList = new HashMap<>();
        Set<Integer> visitedBus = new HashSet<>();
        Deque<int[]> queue = new ArrayDeque<>();

        // fill adjList - each station -- bus passing
        for (int i = 0; i < routes.length; i++) {
            for (int station : routes[i]) {
                if (!adjList.containsKey(station)) {
                    adjList.put(station, new ArrayList<>());
                }
                adjList.get(station).add(i);
            }
        }

        // initialize queue with src and bus count = 0
        queue.add(new int[]{source, 0});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int station = current[0];
            int busCount = current[1];
            if (station == target)
                return busCount;
            if (adjList.containsKey(station)) {
                for (int bus : adjList.get(station)) {
                    if (!visitedBus.contains(bus)) {
                        for (int s : routes[bus]) {
                            queue.offer(new int[]{
                                    s, busCount + 1
                            });
                        }
                    }
                    visitedBus.add(bus);
                }
            }
        }

        return -1;

    }
}

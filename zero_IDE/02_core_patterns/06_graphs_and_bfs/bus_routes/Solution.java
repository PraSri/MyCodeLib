import java.util.*;

public class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) return 0;

        // Map each stop to the list of bus routes passing through it
        Map<Integer, List<Integer>> stopToRoutes = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            for (int stop : routes[i]) {
                stopToRoutes.computeIfAbsent(stop, k -> new ArrayList<>()).add(i);
            }
        }

        if (!stopToRoutes.containsKey(source) || !stopToRoutes.containsKey(target)) {
            return -1;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visitedRoutes = new boolean[routes.length];
        Set<Integer> visitedStops = new HashSet<>();

        // Initialize BFS from all routes that stop at source
        for (int route : stopToRoutes.get(source)) {
            queue.offer(route);
            visitedRoutes[route] = true;
        }
        visitedStops.add(source);

        int busesTaken = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int routeId = queue.poll();

                for (int stop : routes[routeId]) {
                    if (stop == target) return busesTaken;

                    if (!visitedStops.contains(stop)) {
                        visitedStops.add(stop);
                        for (int nextRoute : stopToRoutes.getOrDefault(stop, Collections.emptyList())) {
                            if (!visitedRoutes[nextRoute]) {
                                visitedRoutes[nextRoute] = true;
                                queue.offer(nextRoute);
                            }
                        }
                    }
                }
            }
            busesTaken++;
        }

        return -1;
    }
}

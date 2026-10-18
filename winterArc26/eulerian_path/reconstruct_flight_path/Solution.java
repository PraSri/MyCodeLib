package eulerian_path.reconstruct_flight_path;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution {

    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        for (List<String> t : tickets) {
            graph.computeIfAbsent(t.get(0), k -> new PriorityQueue<>()).offer(t.get(1));
        }
        LinkedList<String> route = new LinkedList<>();
        visit("JFK", graph, route);
        return route;
    }

    private void visit(String airport, Map<String, PriorityQueue<String>> graph, LinkedList<String> route) {
        PriorityQueue<String> destinations = graph.get(airport);
        while (destinations != null && !destinations.isEmpty()) {
            visit(destinations.poll(), graph, route);
        }
        route.addFirst(airport);
    }
}

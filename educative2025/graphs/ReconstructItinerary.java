package graphs;

import java.util.*;

public class ReconstructItinerary {

    public static List<String> findItinerary(List<List<String>> tickets) {

        Map<String, List<String>> flightMap = new HashMap<>();
        List<String> result = new ArrayList<>();

        // Populate the flight map with each departure and arrival
        for (List<String> ticket : tickets) {
            flightMap.putIfAbsent(ticket.get(0), new ArrayList<>());
            flightMap.get(ticket.get(0)).add(ticket.get(1));
        }

        // Sort each list of destinations in reverse lexicographical order
        for (List<String> destinations : flightMap.values()) {
            destinations.sort(Collections.reverseOrder());
        }

        dfsTraversal("JFK", flightMap, result);

        Collections.reverse(result);
        return result;
    }

    private static void dfsTraversal(String current, Map<String, List<String>> flightMap, List<String> result) {
        List<String> destinations = flightMap.get(current);

        // Traverse all destinations in the order of their lexicographical sorting
        while (destinations != null && !destinations.isEmpty()) {
            // Pop the last destination from the list (smallest lexicographical order due to reverse sorting)
            String nextDestination = destinations.remove(destinations.size() - 1);

            // Recursively perform DFS on the next destination
            dfsTraversal(nextDestination, flightMap, result);
        }

        // Append the current airport to the result after all destinations are visited
        result.add(current);
    }

    public static void main(String[] args) {
        List<List<List<String>>> ticketsList = Arrays.asList(List.of(Arrays.asList("JFK", "REP")), Arrays.asList(Arrays.asList("JFK", "DOC"), Arrays.asList("DOC", "ABT"), Arrays.asList("ABT", "JFK")), Arrays.asList(Arrays.asList("JFK", "SFO"), Arrays.asList("LAK", "SFO"), Arrays.asList("SFO", "ATL"), Arrays.asList("SFO", "LAK")), Arrays.asList(Arrays.asList("JFK", "YUR"), Arrays.asList("YUR", "JFK"), Arrays.asList("JFK", "ATL"), Arrays.asList("ATL", "JFK")), Arrays.asList(Arrays.asList("JFK", "ABC"), Arrays.asList("JFK", "ABM"), Arrays.asList("JFK", "ABX"), Arrays.asList("ABX", "WXY"), Arrays.asList("WXY", "OPT")));

        for (int i = 0; i < ticketsList.size(); i++) {
            List<List<String>> tickets = ticketsList.get(i);
            System.out.print((i + 1) + ".\tTickets: [");
            for (int j = 0; j < tickets.size(); j++) {
                List<String> ticket = tickets.get(j);
                System.out.print("['" + ticket.get(0) + "', '" + ticket.get(1) + "']");
                if (j < tickets.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");

            System.out.print("\tFlight Itinerary: [");
            List<String> itinerary = findItinerary(tickets);
            for (int k = 0; k < itinerary.size(); k++) {
                System.out.print("'" + itinerary.get(k) + "'");
                if (k < itinerary.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}

package advancedgraphs;

import java.util.*;

/**
 * Longest Common Subpath (Hard)
 * https://leetcode.com/problems/longest-common-subpath/
 * <p>
 * Valid Arrangement of Pairs (Hard)
 * https://leetcode.com/problems/valid-arrangement-of-pairs/
 */

public class ReconstructFlightPath {

    // classic Eulerian path - Eulerian path in a graph is a path that travels along every edge exactly once

    // Exists if and only if the graph is connected and has 0 or 2 vertices of odd degree

    // If 0 odd vertices, the Eulerian path is a cycle (Eulerian circuit).

    // Main idea yeh hota hai ki har airport se lexicographically smallest destination choose karni hai
    //  lekin code reverse-order sorting + backtracking trick se karta hai.


    public List<String> findItinerary(List<List<String>> tickets) {
        List<String> res = new ArrayList<>();
        Map<String, List<String>> adj = new HashMap<>();
        //tickets = [["HOU","JFK"],["SEA","JFK"],["JFK","SEA"],["JFK","HOU"]]
        for (List<String> ticket : tickets) {
            adj.putIfAbsent(ticket.get(0), new ArrayList<>());
            adj.get(ticket.get(0)).add(ticket.get(1));
        }
        for (List<String> destinations : adj.values()) {
            // Yeh kyun?
            //Taaki jab hum .remove(destinations.size() - 1)
            // karein tab hume lexicographically smallest destination mile.
            // Matlab: smallest last mein rakho ? pop from end ? always smallest milta hai.
            destinations.sort(Collections.reverseOrder());
        }
        dfs("JFK", adj, res);
        Collections.reverse(res);
        return res;
    }

    private void dfs(String src, Map<String, List<String>> adj, List<String> res) {
        List<String> destinations = adj.getOrDefault(src, new ArrayList<>());
        while (destinations != null && !destinations.isEmpty()) {
            String nextDest = destinations.remove(destinations.size() - 1);
            dfs(nextDest, adj, res);
        }
        res.add(src);
    }



    /**
     * Longest Common Subpath
     * https://leetcode.com/problems/longest-common-subpath
     */
    public static class LongestCommonSubpath {
    }

    /**
     * Valid Arrangement of Pairs
     * https://leetcode.com/problems/valid-arrangement-of-pairs
     */
    public static class ValidArrangementOfPairs {
    }
}

package microsoft;

import java.util.*;

public class EvaluateDivision {

    //O(E+V)*queries.size()

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        // build undirected graph
        Map<String, Map<String, Double>> graph = buildGraph(equations, values);

        // for each query do dfs to get path weight
        double[] result = new double[queries.size()];

        for (int i = 0; i < queries.size(); i++) {
            String src = queries.get(i).get(0);
            String dest = queries.get(i).get(1);
            result[i] = getPathWeight(src, dest, new HashSet<>(), graph);
        }

        return result;

    }

    private double getPathWeight(String src, String dest, Set<String> visited, Map<String, Map<String, Double>> graph) {

        // rejection case
        if (!graph.containsKey(src)) {
            return -1.0;
        }

        // acceptance case
        if (graph.get(src).containsKey(dest)) {
            return graph.get(src).get(dest);
        }

        visited.add(src);
        for (Map.Entry<String, Double> neighbours : graph.get(src).entrySet()) {
            if (!visited.contains(neighbours.getKey())) {
                double productWeight = getPathWeight(neighbours.getKey(), dest, visited, graph);
                if (productWeight != -1.0) {
                    return neighbours.getValue() * productWeight;
                }
            }
        }
        return -1.0;
    }

    private Map<String, Map<String, Double>> buildGraph(List<List<String>> equations, double[] values) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        int i = 0;
        for (List<String> equation : equations) {
            String src = equation.get(0);
            String dest = equation.get(1);
            double value = values[i++];
            graph.putIfAbsent(src, new HashMap<>());
            graph.get(src).put(dest, value);
            graph.putIfAbsent(dest, new HashMap<>());
            graph.get(dest).put(src, 1 / value);
        }
        return graph;
    }
}

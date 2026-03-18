package topological_sort;

import java.util.*;

public class BuildMatrixWithConditions {

    public static int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
        // Get topological order for rows and columns based on conditions
        List<Integer> orderRows = topologicalSort(k, rowConditions);
        List<Integer> orderColumns = topologicalSort(k, colConditions);

        // If either order is invalid, return empty matrix
        if (orderRows.isEmpty() || orderColumns.isEmpty()) return new int[0][0];

        int[][] matrix = new int[k][k];
        Map<Integer, Integer> posRow = new HashMap<>();
        Map<Integer, Integer> posCol = new HashMap<>();

        // Store row and column positions for each number
        for (int i = 0; i < orderRows.size(); i++) {
            posRow.put(orderRows.get(i), i);
        }

        for (int i = 0; i < orderColumns.size(); i++) {
            posCol.put(orderColumns.get(i), i);
        }

        // Place each number in matrix based on row and column positions
        for (int num = 1; num <= k; num++) {
            if (posRow.containsKey(num) && posCol.containsKey(num)) {
                matrix[posRow.get(num)][posCol.get(num)] = num;
            }
        }

        return matrix;
    }

    public static List<Integer> topologicalSort(int n, int[][] edges) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        List<Integer> order = new ArrayList<>();
        int[] visited = new int[n + 1];

        // Build adjacency list for graph
        for (int[] edge : edges) {
            adj.computeIfAbsent(edge[0], x -> new ArrayList<>()).add(edge[1]);
        }

        for (int i = 1; i <= n; i++) {
            if (visited[i] == 0 && dfs(i, adj, visited, order)) {
                return Collections.emptyList();  // Return empty if a cycle is detected
            }
        }

        Collections.reverse(order);  // Reverse to get correct topological order
        return order;
    }

    public static boolean dfs(int node, Map<Integer, List<Integer>> adj, int[] visited, List<Integer> order) {
        visited[node] = 1;  // Mark node as visiting

        if (adj.containsKey(node)) {
            for (int neighbor : adj.get(node)) {
                if (visited[neighbor] == 0) {
                    if (dfs(neighbor, adj, visited, order)) return true;
                } else if (visited[neighbor] == 1) {
                    return true;  // Cycle detected
                }
            }
        }

        visited[node] = 2;  // Mark node as fully processed
        order.add(node);
        return false;
    }

    public static void main(String[] args) {
        int[] inputK = {3, 3, 2, 3, 4};

        List<int[][]> inputRowConditions = Arrays.asList(
                new int[][]{{1, 2}, {2, 3}},
                new int[][]{{1, 2}, {2, 3}, {3, 1}},
                new int[][]{{1, 2}},
                new int[][]{{1, 2}},
                new int[][]{{1, 2}, {2, 3}, {3, 4}}
        );

        List<int[][]> inputColConditions = Arrays.asList(
                new int[][]{{2, 1}, {3, 2}},
                new int[][]{{1, 2}},
                new int[][]{{2, 1}},
                new int[][]{{2, 1}},
                new int[][]{{1, 3}, {3, 4}, {2, 1}}
        );

        for (int i = 0; i < inputK.length; i++) {
            System.out.println((i + 1) + ". Input:");
            System.out.println("\t- k = " + inputK[i]);

            // Print Row Conditions
            System.out.print("\t- Row Conditions = {");
            int[][] rowConditions = inputRowConditions.get(i);
            for (int j = 0; j < rowConditions.length; j++) {
                System.out.print("{" + rowConditions[j][0] + ", " + rowConditions[j][1] + "}");
                if (j < rowConditions.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("}");

            // Print Column Conditions
            System.out.print("\t- Column Conditions = {");
            int[][] colConditions = inputColConditions.get(i);
            for (int j = 0; j < colConditions.length; j++) {
                System.out.print("{" + colConditions[j][0] + ", " + colConditions[j][1] + "}");
                if (j < colConditions.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("}");

            int[][] output = buildMatrix(inputK[i], inputRowConditions.get(i), inputColConditions.get(i));
            System.out.println("\n   Matrix:");
            if (output.length > 0) {
                for (int[] row : output) {
                    System.out.println("\t" + Arrays.toString(row));
                }
            } else {
                System.out.println("\t[]");
            }
            System.out.println(new String(new char[100]).replace("\0", "-"));
        }
    }

}
package hash_maps;

import java.util.*;

public class NumberOfDistinctIslands {

    public static int numDistinctIslands(int[][] grid) {
        // Initialize a set to keep track of visited cells
        Set<String> visited = new HashSet<>();

        // Use a HashMap to count the distinct island shapes
        Map<String, Integer> uniqueIslands = new HashMap<>();

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                // Start DFS if it's a land cell and not yet visited
                if (grid[row][col] == 1 && !visited.contains(row + "," + col)) {
                    List<int[]> path = new ArrayList<>();

                    // Perform DFS to collect the relative positions (shape) of the island
                    dfs(grid, row, col, row, col, path, visited);

                    // Convert the path to a string and use it as the key in the HashMap
                    StringBuilder key = new StringBuilder();
                    for (int[] pos : path) {
                        key.append(pos[0]).append(",").append(pos[1]).append(";");
                    }
                    uniqueIslands.put(key.toString(), uniqueIslands.getOrDefault(key.toString(), 0) + 1);
                }
            }
        }

        // The number of unique keys in the HashMap gives the number of distinct islands
        return uniqueIslands.size();
    }

    private static void dfs(int[][] grid, int row, int col, int rowOrigin, int colOrigin, List<int[]> path, Set<String> visited) {
        // Base case: out of bounds or already visited or water
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length ||
                visited.contains(row + "," + col) || grid[row][col] == 0) {
            return;
        }

        // Mark the current cell as visited
        visited.add(row + "," + col);

        // Record the relative position of this cell from the origin of this island
        path.add(new int[]{row - rowOrigin, col - colOrigin});

        // Explore 4-directionally (up, down, left, right)
        dfs(grid, row + 1, col, rowOrigin, colOrigin, path, visited);
        dfs(grid, row - 1, col, rowOrigin, colOrigin, path, visited);
        dfs(grid, row, col + 1, rowOrigin, colOrigin, path, visited);
        dfs(grid, row, col - 1, rowOrigin, colOrigin, path, visited);
    }

    public static void main(String[] args) {
        int[][][] grids = {
                {{1, 1, 0, 0, 1}, {1, 0, 0, 0, 0}, {0, 0, 0, 1, 1}, {1, 1, 0, 1, 1}},
                {{1, 1, 0, 1, 1}, {1, 0, 0, 0, 1}, {0, 0, 1, 0, 0}, {1, 1, 1, 1, 1}},
                {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}},
                {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}},
                {{1, 0, 0, 1}, {1, 1, 0, 1}, {0, 0, 0, 0}, {1, 0, 0, 1}, {1, 0, 0, 1}}
        };

        for (int i = 0; i < grids.length; i++) {
            System.out.println(i + 1 + ".\tGrid:");
            for (int[] row : grids[i]) {
                System.out.println("\t  " + Arrays.toString(row));
            }
            System.out.println();
            System.out.println("\tNumber of distinct island(s): " + numDistinctIslands(grids[i]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}


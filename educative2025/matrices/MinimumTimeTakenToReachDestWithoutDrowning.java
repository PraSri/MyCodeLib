package matrices;

import java.util.*;

public class MinimumTimeTakenToReachDestWithoutDrowning {

    public static int minimumSeconds(List<List<String>> land) {
        int m = land.size();
        int n = land.get(0).size();

        // Queue to hold flooded cells
        Queue<int[]> flood = new LinkedList<>();

        // Queue to hold player's move positions
        Queue<int[]> moves = new LinkedList<>();

        // Directions for movement (down, up, right, left)
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        // Track the number of seconds elapsed
        int seconds = 0;

        // Initialize the starting positions for both flood and source
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                String cell = land.get(i).get(j);
                if (cell.equals("S")) { // Start position for source
                    moves.offer(new int[]{i, j});
                }
                if (cell.equals("*")) { // Start positions for flooded cells
                    flood.offer(new int[]{i, j});
                }
            }
        }

        // Run the BFS starting from the source S
        while (!moves.isEmpty()) {
            int spread = flood.size();
            int move = moves.size();

            // Process all flooded cells and expand flooding
            for (int i = 0; i < spread; i++) {
                int[] floodPos = flood.poll();
                int flood_x = floodPos[0], flood_y = floodPos[1];

                for (int[] dir : directions) {
                    int new_x = flood_x + dir[0], new_y = flood_y + dir[1];

                    // Flood any empty cells adjacent to current flood cell
                    if (0 <= new_x && new_x < m && 0 <= new_y && new_y < n && land.get(new_x).get(new_y).equals(".")) {
                        // Mark as flooded
                        land.get(new_x).set(new_y, "*");
                        flood.offer(new int[]{new_x, new_y});
                    }
                }
            }

            // Process all moves from the source S
            for (int i = 0; i < move; i++) {
                int[] movePos = moves.poll();
                int move_x = movePos[0], move_y = movePos[1];

                // If the source reaches the destination 'D', return the time taken
                if (land.get(move_x).get(move_y).equals("D")) {
                    return seconds;
                }

                // Move the source to adjacent cells
                for (int[] dir : directions) {
                    int new_x = move_x + dir[0], new_y = move_y + dir[1];

                    // Move only if the cell is empty or the destination
                    if (0 <= new_x && new_x < m && 0 <= new_y && new_y < n && (land.get(new_x).get(new_y).equals(".") || land.get(new_x).get(new_y).equals("D"))) {
                        if (!land.get(new_x).get(new_y).equals("D")) {
                            // Mark as flooded
                            land.get(new_x).set(new_y, "*");
                        }
                        moves.offer(new int[]{new_x, new_y});
                    }
                }
            }

            // Increment the seconds after processing both flood and source moves
            seconds++;
        }

        // Return -1 if there is no valid path to the destination
        return -1;
    }

    // Driver code
    public static void main(String[] args) {
        List<List<List<String>>> lands = Arrays.asList(Arrays.asList(Arrays.asList(".", ".", ".", "X", "*"), Arrays.asList("S", "X", ".", ".", "X"), Arrays.asList(".", ".", "X", ".", "*"), Arrays.asList("D", ".", "*", ".", "*")), Arrays.asList(Arrays.asList("X", ".", "S", "X", ".", "D"), Arrays.asList(".", "*", ".", ".", "*", "."), Arrays.asList(".", ".", ".", "*", ".", ".")), Arrays.asList(Arrays.asList("S", ".", ".", "X", "X", ".", "X", "X"), Arrays.asList(".", ".", "X", ".", ".", ".", "X", "*"), Arrays.asList(".", ".", ".", ".", "D", ".", ".", "X")), Arrays.asList(Arrays.asList("*", "S", ".", "*", ".", "."), Arrays.asList(".", "X", "*", "*", ".", "."), Arrays.asList("D", ".", ".", ".", ".", ".")), Arrays.asList(Arrays.asList("X", ".", "*", "X", ".", "D"), Arrays.asList(".", "X", "*", ".", ".", "."), Arrays.asList("X", ".", "*", ".", ".", "."), Arrays.asList(".", "X", "*", ".", ".", "S")));

        for (int i = 0; i < lands.size(); i++) {
            System.out.println((i + 1) + ".\tLand:");
            for (List<String> row : lands.get(i)) {
                System.out.println("\t  " + row);
            }
            System.out.println("\n\tNumber of second(s) to reach the destination: " + minimumSeconds(lands.get(i)));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}
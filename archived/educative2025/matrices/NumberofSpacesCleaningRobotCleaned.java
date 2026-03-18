package matrices;

import java.util.Arrays;

public class NumberofSpacesCleaningRobotCleaned {
    public static int numberOfCleanRooms(int[][] room) {

        // Directions represent movement in order: Right, Down, Left, Up
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        // Get dimensions of the room
        int rows = room.length, cols = room[0].length;

        // Counter for the number of unique tiles cleaned
        int cleaned = 0;

        // Start position at (0,0), i.e., row = 0 and col = 0, and facing right, i.e., index 0 in directions
        int currRow = 0, currCol = 0, direction = 0;

        // Continue until the robot encounters a previously visited tile in the same direction
        while ((room[currRow][currCol] >> (direction + 1) & 1) == 0) {

            // If the tile is cleanable (not an obstacle), count it
            if (room[currRow][currCol] == 0) {
                cleaned++;
            }

            // Mark this tile as visited in the current direction
            room[currRow][currCol] |= 1 << (direction + 1);

            // Calculate the next position based on the current direction
            int nextRow = currRow + directions[direction][0];
            int nextCol = currCol + directions[direction][1];

            // Check if the next position is within bounds and not an obstacle
            if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols && room[nextRow][nextCol] != 1) {

                // Move to the next position
                currRow = nextRow;
                currCol = nextCol;
            } else {

                // If movement is blocked, rotate direction clockwise (Right ? Down ? Left ? Up)
                direction = (direction + 1) % 4;
            }
        }

        // Return the total number of unique cleanable tiles visited
        return cleaned;
    }

    private boolean[][][] visited;
    private int[][] room;
    private int cleanRoomsCount;

    // Counts the number of clean rooms that the robot cleans
    public int numberOfCleanRooms2(int[][] room) {
        // The visited array tracks the cells visited for each of the 4 directions
        visited = new boolean[room.length][room[0].length][4];
        this.room = room; // Initialize room reference
        cleanRoomsCount = 0; // Initialize clean rooms count

        dfs(0, 0, 0); // Start the DFS from the top-left corner in direction '0' (right)
        return cleanRoomsCount; // Return the total number of clean rooms visited
    }

    // Performs DFS to navigate the room based on the given rules
    private void dfs(int i, int j, int dir) {
        // If current position and direction is already visited, do nothing
        if (visited[i][j][dir]) {
            return;
        }

        // Relative directions: right (0), down (1), left (2), up (3)
        int[] directions = {0, 1, 0, -1, 0};

        // Mark the current position and direction as visited
        visited[i][j][dir] = true;

        // If current room is clean (i.e., not a wall or already counted), increment cleanRoomsCount
        if (room[i][j] == 0) {
            cleanRoomsCount++;
            // Mark the room as cleaned by setting it to a distinct value (-1)
            // to avoid recounting when visited from a different direction
            room[i][j] = -1;
        }

        // Compute next position based on the current direction
        int nextX = i + directions[dir];
        int nextY = j + directions[dir + 1];

        // Move to next position if it's within bounds and is not a wall
        if (nextX >= 0 && nextX < room.length && nextY >= 0 && nextY < room[0].length && room[nextX][nextY] != 1) {
            dfs(nextX, nextY, dir); // Continue in the same direction
        } else {
            // If hitting a wall or out of bounds, turn right (increment direction)
            // and continue DFS with the new direction. Use modulo to wrap direction.
            dfs(i, j, (dir + 1) % 4);
        }
    }

    // Driver code
    public static void main(String[] args) {
        int[][][] testCases = {{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}}, {{0, 1}, {0, 0}}, {{0, 0, 0, 0}, {1, 1, 1, 0}}, {{0, 0}, {0, 0}}, {{0, 1, 1}, {1, 0, 1}, {1, 1, 1}}, {{0, 0, 1, 0, 0, 0, 1, 0}, {0, 1, 0, 0, 1, 0, 0, 0}, {0, 0, 0, 1, 0, 0, 1, 0}, {1, 0, 0, 0, 0, 1, 0, 0}, {0, 0, 1, 0, 0, 0, 0, 1}}, {{0, 1, 0, 0, 1, 0, 0, 0, 1, 0}, {0, 1, 1, 0, 1, 1, 0, 1, 0, 0}, {0, 0, 0, 0, 0, 1, 0, 0, 1, 0}, {0, 1, 0, 1, 0, 0, 1, 0, 0, 0}, {0, 0, 0, 1, 1, 0, 0, 1, 1, 0}, {0, 1, 0, 0, 0, 1, 0, 0, 0, 0}}};

        for (int i = 0; i < testCases.length; i++) {
            System.out.println((i + 1) + ".\tInput room:");
            for (int[] row : testCases[i]) {
                System.out.println("\t" + Arrays.toString(row));
            }
            int[][] roomCopy = Arrays.stream(testCases[i]).map(int[]::clone).toArray(int[][]::new);
            int result = numberOfCleanRooms(roomCopy);
            System.out.println("\n\tOutput: " + result);
            System.out.println("-" + new String(new char[100]).replace('\0', '-') + "\n");
        }
    }
}

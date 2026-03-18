package challenge_yourself;

// Leetcode premium

import java.util.*;

/**
 * In this problem, we are presented with a grid representing a series of rooms,
 * each of which could be an empty room, a gate, or a blocked wall.
 * The value -1 identifies a wall or obstacle, which means the room cannot be passed through.
 * A value of 0 indicates a gate, serving as a possible destination for other rooms.
 * Any room with a value of INF, which stands for infinity and is given the numerical value of 2147483647,
 * represents an empty room needing the distance filled to its nearest gate.
 * <p>
 * The task is to update the grid so that all empty rooms have their values changed from
 * INF to the shortest distance to a gate. If there's no way to get to a gate,
 * the value of INF should remain unchanged. The update should be done in place,
 * meaning no additional grid should be constructed but instead, the rooms grid itself should be modified.
 */
public class WallsAndGates {

    public void wallsAndGates(int[][] rooms) {
        // get the number of rows and columns in the rooms grid
        int numRows = rooms.length;
        int numCols = rooms[0].length;

        // create a queue to hold the gate positions
        Deque<int[]> queue = new LinkedList<>();

        // find all gates (represented by 0) and add their positions to the queue
        for (int i = 0; i < numRows; ++i) {
            for (int j = 0; j < numCols; ++j) {
                if (rooms[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        // distance from the gate
        int distance = 0;
        // array to facilitate exploration in 4 directions: up, right, down, left
        int[] directions = {-1, 0, 1, 0, -1};

        // perform BFS starting from each gate
        while (!queue.isEmpty()) {
            ++distance;
            for (int i = queue.size(); i > 0; --i) {
                // get and remove the position from the front of the queue
                int[] position = queue.poll();
                for (int j = 0; j < 4; ++j) { // explore all 4 directions
                    int newRow = position[0] + directions[j];
                    int newCol = position[1] + directions[j + 1];
                    // if the new position is within bounds and is an empty room
                    // (denoted by Integer.MAX_VALUE), then update the distance
                    // and add the new position to the queue for further BFS
                    if (newRow >= 0 && newRow < numRows && newCol >= 0 && newCol < numCols && rooms[newRow][newCol] == Integer.MAX_VALUE) {
                        rooms[newRow][newCol] = distance;
                        queue.offer(new int[]{newRow, newCol});
                    }
                }
            }
        }
    }

}

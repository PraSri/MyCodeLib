package uber;

import java.util.ArrayList;
import java.util.List;

/**
 * Solve in java : Inputs:
 * <p>
 * | O | E | E | E | X |
 * | E | O | X | X | X |
 * | E | E | E | E | E |
 * | X | E | O | E | E |
 * | X | E | X | E | X |
 * <p>
 * Location Map: A 2D grid where:
 * <p>
 * 'O' represents a robot
 * 'E' represents an empty space
 * 'X' represents a blocker
 * Query Array: [2, 2, 4, 1]
 * <p>
 * These values represent the exact distances a robot must be able to move before hitting a blocker:
 * Left: 2 spaces
 * Top: 2 spaces
 * Bottom: 4 spaces
 * Right: 1 space
 * <p>
 * Notes:
 * The boundaries of the map are also considered blockers. Need to find all robot positions that satisfy these exact distance requirements in all four directions
 * <p>
 * Task:
 * Return the coordinates of all robots that meet these movement constraints.
 *
 **/


public class RobotMovement {
    public static void main(String[] args) {
        char[][] grid = {
                {'O', 'E', 'E', 'E', 'X'},
                {'E', 'O', 'X', 'X', 'X'},
                {'E', 'E', 'E', 'E', 'E'},
                {'X', 'E', 'O', 'E', 'E'},
                {'X', 'E', 'X', 'E', 'X'}
        };
        int[] query = {2, 2, 4, 1}; // left, top, bottom, right

        List<int[]> result = findRobotsMeetingConstraints(grid, query);
        System.out.println("Robots satisfying constraints:");
        for (int[] pos : result) {
            System.out.println("[" + pos[0] + ", " + pos[1] + "]");
        }
    }

    public static List<int[]> findRobotsMeetingConstraints(char[][] grid, int[] query) {
        List<int[]> robots = new ArrayList<>();
        int rows = grid.length;
        int cols = grid[0].length;

        // Directions: left, top, bottom, right
        int[][] dirs = {{0, -1}, {-1, 0}, {1, 0}, {0, 1}};

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 'O') {
                    boolean meetsAll = true;
                    for (int i = 0; i < 4; i++) {
                        int dr = dirs[i][0];
                        int dc = dirs[i][1];
                        int distance = computeDistanceToBlocker(grid, r, c, dr, dc);
                        if (distance != query[i]) {
                            meetsAll = false;
                            break;
                        }
                    }
                    if (meetsAll) {
                        robots.add(new int[]{r, c});
                    }
                }
            }
        }
        return robots;
    }

    private static int computeDistanceToBlocker(char[][] grid, int startRow, int startCol, int dr, int dc) {
        int rows = grid.length;
        int cols = grid[0].length;
        int step = 1;
        while (true) {
            int nr = startRow + dr * step;
            int nc = startCol + dc * step;
            if (nr < 0 || nr >= rows || nc < 0 || nc >= cols || grid[nr][nc] == 'X') {
                return step; // blocker or boundary reached at this step
            }
            step++;
        }
    }

    public static class RobotMovementOptimized {
        public static void main(String[] args) {
            char[][] grid = {
                    {'O', 'E', 'E', 'E', 'X'},
                    {'E', 'O', 'X', 'X', 'X'},
                    {'E', 'E', 'E', 'E', 'E'},
                    {'X', 'E', 'O', 'E', 'E'},
                    {'X', 'E', 'X', 'E', 'X'}
            };
            int[] query = {2, 2, 4, 1}; // left, top, bottom, right

            List<int[]> result = findRobotsMeetingConstraints(grid, query);
            System.out.println("Robots satisfying constraints:");
            for (int[] pos : result) {
                System.out.println("[" + pos[0] + ", " + pos[1] + "]");
            }
        }

        public static List<int[]> findRobotsMeetingConstraints(char[][] grid, int[] query) {
            int rows = grid.length;
            int cols = grid[0].length;

            // Precompute distances in four directions
            int[][] leftDist = new int[rows][cols];
            int[][] rightDist = new int[rows][cols];
            int[][] upDist = new int[rows][cols];
            int[][] downDist = new int[rows][cols];

            // 1. Left distance (number of consecutive empty cells to the left)
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    if (grid[r][c] == 'X') {
                        leftDist[r][c] = 0; // blocker has no meaning, but we set 0
                    } else {
                        if (c == 0 || grid[r][c - 1] == 'X') {
                            leftDist[r][c] = 0;
                        } else {
                            leftDist[r][c] = 1 + leftDist[r][c - 1];
                        }
                    }
                }
            }

            // 2. Right distance (number of consecutive empty cells to the right)
            for (int r = 0; r < rows; r++) {
                for (int c = cols - 1; c >= 0; c--) {
                    if (grid[r][c] == 'X') {
                        rightDist[r][c] = 0;
                    } else {
                        if (c == cols - 1 || grid[r][c + 1] == 'X') {
                            rightDist[r][c] = 0;
                        } else {
                            rightDist[r][c] = 1 + rightDist[r][c + 1];
                        }
                    }
                }
            }

            // 3. Up distance (number of consecutive empty cells upward)
            for (int c = 0; c < cols; c++) {
                for (int r = 0; r < rows; r++) {
                    if (grid[r][c] == 'X') {
                        upDist[r][c] = 0;
                    } else {
                        if (r == 0 || grid[r - 1][c] == 'X') {
                            upDist[r][c] = 0;
                        } else {
                            upDist[r][c] = 1 + upDist[r - 1][c];
                        }
                    }
                }
            }

            // 4. Down distance (number of consecutive empty cells downward)
            for (int c = 0; c < cols; c++) {
                for (int r = rows - 1; r >= 0; r--) {
                    if (grid[r][c] == 'X') {
                        downDist[r][c] = 0;
                    } else {
                        if (r == rows - 1 || grid[r + 1][c] == 'X') {
                            downDist[r][c] = 0;
                        } else {
                            downDist[r][c] = 1 + downDist[r + 1][c];
                        }
                    }
                }
            }

            // Now check all robots
            List<int[]> result = new ArrayList<>();
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    if (grid[r][c] == 'O') {
                        if (leftDist[r][c] == query[0] &&
                                upDist[r][c] == query[1] &&
                                downDist[r][c] == query[2] &&
                                rightDist[r][c] == query[3]) {
                            result.add(new int[]{r, c});
                        }
                    }
                }
            }
            return result;
        }
    }
}

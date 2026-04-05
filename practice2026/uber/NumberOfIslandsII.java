package uber;

import java.util.ArrayList;
import java.util.List;

public class NumberOfIslandsII {

    /**
     * key insights:
     * its dynamic connectivity problem
     * naive approach - run a full island counting dfs/bfs after each land addition
     * but that would be too slow
     *
     * use Union-Find/Disjoint Set Union data structure. designed for tracking
     * connect components that can merge over time.
     * this ensures near constant time operations
     * */

    /**
     *
     * Given a grid initially filled with water, add land at given positions
     * and return the number of islands after each addition
     * Dynamically adds land positions and tracks the number of islands
     *
     * @param m         number of rows in the grid
     * @param n         number of columns in the grid
     * @param positions array of [row, col] positions where land is added
     * @return list containing number of islands after each position is added
     */

    /**
     * Time complexity - (k * alpha(m*n))
     * k = positions length
     * alpha = inverse Ackermann function
     * <p>
     * find operation = O(alpha(m*n))
     * union operation = O(1)
     * <p>
     * Space complexity = O(m*n)
     *
     */

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        int[][] grid = new int[m][n];

        // union find to manage connected components
        // each cell (i,j) is mapped to index i*n + j
        // each cell is numbered from 0,1,2,...
        UnionFind unionFind = new UnionFind(m * n);
        int[] directions = {-1, 0, 1, 0, -1};
        int islandCount = 0;
        List<Integer> result = new ArrayList<>();
        for (int[] position : positions) {
            int row = position[0];
            int col = position[1];

            // skip if this position already has land
            if (grid[row][col] == 1) {
                result.add(islandCount);
                continue;
            }

            //  add land at current position
            grid[row][col] = 1;
            islandCount++; // initially, this forms a new island

            for (int k = 0; k < 4; k++) {
                int newRow = row + directions[k];
                int newCol = col + directions[k + 1];
                // check if adjacent cell is valid and has land
                if (newRow >= 0 && newCol >= 0 && newRow < m && newCol < n && grid[newRow][newCol] == 1) {
                    // try to union current cell with adjacent land cell
                    // convert 2d coordinates to 1d index for union find
                    int currentIndex = row * n + col;
                    int adjacentIndex = newRow * n + newCol;
                    // if union successfull, they were in different components
                    // decrease island count
                    if (unionFind.union(currentIndex, adjacentIndex)) {
                        islandCount--;
                    }
                }
            }
            result.add(islandCount);
        }
        return result;
    }

    /**
     * Disjoint Set Union - with path compression and union by size
     *
     */
    static class UnionFind {
        private int[] parent;
        private int[] size;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        // find root of component containing element x
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        // union two components - returns true if union was performed
        // means components are different, otherwise false
        public boolean union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);

            // already in same component
            if (rootA == rootB) {
                return false;
            }
            // union by size, attach smaller under larger
            if (size[rootA] > size[rootB]) {
                parent[rootB] = rootA;
                size[rootA] += size[rootB];
            } else {
                parent[rootA] = rootB;
                size[rootA] += size[rootB];
            }
            return true;
        }

    }
}

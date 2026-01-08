package graphs;

public class NumberOfIslands {

    /**
     * Surrounded Regions (Medium)
     * https://leetcode.com/problems/surrounded-regions/
     * <p>
     * Walls and Gates (Medium)
     * https://leetcode.com/problems/walls-and-gates/
     * <p>
     * Number of Islands II (Hard)
     * https://leetcode.com/problems/number-of-islands-ii/
     * <p>
     * Number of Connected Components in an Undirected Graph (Medium)
     * https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
     * <p>
     * Battleships in a Board (Medium)
     * https://leetcode.com/problems/battleships-in-a-board/
     * <p>
     * Number of Distinct Islands (Medium)
     * https://leetcode.com/problems/number-of-distinct-islands/
     * <p>
     * Max Area of Island (Medium)
     * https://leetcode.com/problems/max-area-of-island/
     * <p>
     * Count Sub Islands (Medium)
     * https://leetcode.com/problems/count-sub-islands/
     * <p>
     * Find All Groups of Farmland (Medium)
     * https://leetcode.com/problems/find-all-groups-of-farmland/
     * <p>
     * Count Unreachable Pairs of Nodes in an Undirected Graph (Medium)
     * https://leetcode.com/problems/count-unreachable-pairs-of-nodes-in-an-undirected-graph/
     * <p>
     * Maximum Number of Fish in a Grid (Medium)
     * https://leetcode.com/problems/maximum-number-of-fish-in-a-grid/
     * <p>
     * Count Islands With Total Value Divisible by K (Medium)
     * https://leetcode.com/problems/count-islands-with-total-value-divisible-by-k/
     */

    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    private void dfs(char[][] grid, int i, int j) {
        // boundary cases
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0') {
            return;
        }

        grid[i][j] = '0';

        // call dfs in all 4 directions
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }


    /**
     * Surrounded Regions
     * https://leetcode.com/problems/surrounded-regions
     */
    public static class SurroundedRegions {
    }

    /**
     * Walls and Gates
     * https://leetcode.com/problems/walls-and-gates
     */
    public static class WallsAndGates {
    }

    /**
     * Number of Islands II
     * https://leetcode.com/problems/number-of-islands-ii
     */
    public static class NumberOfIslandsIi {
    }

    /**
     * Number of Connected Components in an Undirected Graph
     * https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph
     */
    public static class NumberOfConnectedComponentsInAnUndirectedGraph {
    }

    /**
     * Battleships in a Board
     * https://leetcode.com/problems/battleships-in-a-board
     */
    public static class BattleshipsInABoard {
    }

    /**
     * Number of Distinct Islands
     * https://leetcode.com/problems/number-of-distinct-islands
     */
    public static class NumberOfDistinctIslands {
    }

    /**
     * Max Area of Island
     * https://leetcode.com/problems/max-area-of-island
     */
    public static class MaxAreaOfIsland {
    }

    /**
     * Count Sub Islands
     * https://leetcode.com/problems/count-sub-islands
     */
    public static class CountSubIslands {
    }

    /**
     * Find All Groups of Farmland
     * https://leetcode.com/problems/find-all-groups-of-farmland
     */
    public static class FindAllGroupsOfFarmland {
    }

    /**
     * Count Unreachable Pairs of Nodes in an Undirected Graph
     * https://leetcode.com/problems/count-unreachable-pairs-of-nodes-in-an-undirected-graph
     */
    public static class CountUnreachablePairsOfNodesInAnUndirectedGraph {
    }

    /**
     * Maximum Number of Fish in a Grid
     * https://leetcode.com/problems/maximum-number-of-fish-in-a-grid
     */
    public static class MaximumNumberOfFishInAGrid {
    }

    /**
     * Count Islands With Total Value Divisible by K
     * https://leetcode.com/problems/count-islands-with-total-value-divisible-by-k
     */
    public static class CountIslandsWithTotalValueDivisibleByK {
    }
}

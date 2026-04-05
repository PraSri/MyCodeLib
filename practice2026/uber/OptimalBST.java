package uber;

import java.util.Arrays;

public class OptimalBST {
    /**
     *
     * Problem Restatement
     * We have n distinct words and corresponding positive costs.
     * <p>
     * The BST must satisfy the BST property:
     * inorder traversal yields words in lexicographic order.
     * <p>
     * For a node at level L (root at L=0),
     * its contribution = (L+1) × cost.
     * <p>
     * Minimize the sum of contributions over all nodes.
     * <p>
     * This problem is a direct application of the
     * Optimal Binary Search Tree (OBST) dynamic programming paradigm.
     * The goal is to arrange a set of sorted keys (words) with given
     * search frequencies (costs) into a BST that minimizes
     * the total weighted search cost, where the cost of a node
     * is (depth + 1) × cost.
     *
     */

    int n;
    int[] cost; // sorted costs
    int[] prefixSum; // prefix sums for O(1) range sum
    int[][] memo; // memoization table

    public int minTotalCost(String[] words, int[] costs) {
        n = words.length;
        Pair[] pairs = new Pair[n];
        for (int i = 0; i < n; i++) {
            pairs[i] = new Pair(words[i], costs[i]);
        }
        // since inorder traversal must be lexicographically sorted,
        // we first sort the (word,cost) pairs by word.
        // after sorting the problem reduces to building
        // an optimal BST over a sorted array of weights.

        Arrays.sort(pairs, (a, b) -> a.word.compareTo(b.word));

        // extract sorted costs
        cost = new int[n];
        for (int i = 0; i < n; i++) {
            cost[i] = pairs[i].cost;
        }

        // build prefix sums
        // we compute prefix sums of
        // the sorted costs to get the sum of any contiguous subarray in O(1)

        prefixSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + cost[i];
        }

        // initialize memo table
        memo = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }

        return solve(0, n - 1);
    }

    // Returns minimum total cost for subarray [i,j]
    private int solve(int i, int j) {
        if (i > j) return 0; // empty subtree
        if (i == j) return cost[i]; // single node
        if (memo[i][j] != -1) return memo[i][j];

        int totalWeight = prefixSum[j + 1] - prefixSum[i];
        int best = Integer.MAX_VALUE;
        // try every node
        for (int r = i; r <= j; r++) {
            int leftCost = solve(i, r - 1);
            int rightCost = solve(r + 1, j);
            int candidate = leftCost + rightCost + totalWeight;
            best = Math.min(best, candidate);
        }
        memo[i][j] = best;
        return best;

    }

    private static class Pair {
        String word;
        int cost;

        Pair(String w, int c) {
            this.word = w;
            this.cost = c;
        }
    }

}

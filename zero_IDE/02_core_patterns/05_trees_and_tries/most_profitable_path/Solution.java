import java.util.*;

public class Solution {
    private List<List<Integer>> tree;
    private int[] bobTime;
    private int maxProfit = Integer.MIN_VALUE;

    public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        int n = amount.length;
        tree = new ArrayList<>(n);
        for (int i = 0; i < n; i++) tree.add(new ArrayList<>());
        for (int[] e : edges) {
            tree.get(e[0]).add(e[1]);
            tree.get(e[1]).add(e[0]);
        }

        bobTime = new int[n];
        Arrays.fill(bobTime, Integer.MAX_VALUE);

        // 1. Trace Bob's unique path to root 0 and record timestamps
        findBobPath(bob, -1, 0, new ArrayList<>());

        // 2. DFS Alice from root 0 to all leaves to maximize profit
        dfsAlice(0, -1, 0, 0, amount);

        return maxProfit;
    }

    private boolean findBobPath(int u, int parent, int time, List<Integer> path) {
        path.add(u);
        if (u == 0) {
            for (int i = 0; i < path.size(); i++) {
                bobTime[path.get(i)] = i;
            }
            return true;
        }

        for (int v : tree.get(u)) {
            if (v != parent) {
                if (findBobPath(v, u, time + 1, path)) return true;
            }
        }

        path.remove(path.size() - 1);
        return false;
    }

    private void dfsAlice(int u, int parent, int time, int currentIncome, int[] amount) {
        // Determine gate fee / reward for Alice at node u
        if (time < bobTime[u]) {
            currentIncome += amount[u]; // Alice arrives before Bob
        } else if (time == bobTime[u]) {
            currentIncome += amount[u] / 2; // Alice and Bob arrive simultaneously
        }
        // If time > bobTime[u], Bob already visited; Alice gets 0

        boolean isLeaf = true;
        for (int v : tree.get(u)) {
            if (v != parent) {
                isLeaf = false;
                dfsAlice(v, u, time + 1, currentIncome, amount);
            }
        }

        if (isLeaf) {
            maxProfit = Math.max(maxProfit, currentIncome);
        }
    }
}

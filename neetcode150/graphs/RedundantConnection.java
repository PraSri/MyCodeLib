package graphs;

/**
 * Redundant Connection II (Hard)
 * ? https://leetcode.com/problems/redundant-connection-ii/
 * <p>
 * Accounts Merge (Medium)
 * ? https://leetcode.com/problems/accounts-merge/
 * <p>
 * Maximum Employees to Be Invited to a Meeting (Hard)
 * ? https://leetcode.com/problems/maximum-employees-to-be-invited-to-a-meeting/
 * <p>
 * Shortest Cycle in a Graph (Hard)
 * ? https://leetcode.com/problems/shortest-cycle-in-a-graph/
 */

// time complexity = O(V + (E*alpha(V)))
// space complexity = O(V)

// dfs apporach => A tree cannot contain a cycle.
//While adding edges one by one, the first edge that creates a cycle is the redundant connection.
//For each new edge (u, v):
//
//Temporarily add it to the graph
//Run DFS to check if a cycle exists
//If DFS revisits a node (not coming from its parent), a cycle is formed
//? that edge is the answer

public class RedundantConnection {

    public static class UnionFind {
        int[] parent;
        int[] rank;

        public UnionFind(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int v) {
            if (parent[v] != v) {
                parent[v] = find(parent[v]);
            }
            return parent[v];
        }

        public boolean union(int v1, int v2) {
            int p1 = find(v1);
            int p2 = find(v2);
            if (p1 == p2) {
                return false;
            } else if (rank[p1] > rank[p2]) {
                parent[p2] = p1;
                rank[p1] = rank[p1] + rank[p2];
            } else {
                parent[p1] = p2;
                rank[p2] = rank[p1] + rank[p2];
            }
            return true;
        }
    }

    static class Solution {

        /**
         * Use Disjoint Set Union (Union-Find) to track connected components while adding edges one by one.
         * <p>
         * Initially, every node is its own component.
         * When we add an edge (u, v):
         * If u and v are already in the same component, adding this edge creates a cycle.
         * That edge is exactly the redundant connection.
         * If they are in different components, we safely merge them.
         * Because edges are processed in order, the first edge that fails to union is the answer.
         */

        public int[] findRedundantConnection(int[][] edges) {
            UnionFind uf = new UnionFind(edges.length);
            for (int[] edge : edges) {
                if (!uf.union(edge[0], edge[1])) {
                    return edge;
                }
            }
            return new int[0];
        }
    }


    /**
     * Redundant Connection II (Hard)
     */
    public static class RedundantConnectionIi {
    }

    /**
     * ? https://leetcode.com/problems/redundant-connection-ii
     */
    public static class HttpsLeetcodeComProblemsRedundantConnectionIi {
    }

    /**
     * Accounts Merge (Medium)
     */
    public static class AccountsMerge {
    }

    /**
     * ? https://leetcode.com/problems/accounts-merge
     */
    public static class HttpsLeetcodeComProblemsAccountsMerge {
    }

    /**
     * Maximum Employees to Be Invited to a Meeting (Hard)
     */
    public static class MaximumEmployeesToBeInvitedToAMeeting {
    }

    /**
     * ? https://leetcode.com/problems/maximum-employees-to-be-invited-to-a-meeting
     */
    public static class HttpsLeetcodeComProblemsMaximumEmployeesToBeInvitedToAMeeting {
    }

    /**
     * Shortest Cycle in a Graph (Hard)
     */
    public static class ShortestCycleInAGraph {
    }

    /**
     * ? https://leetcode.com/problems/shortest-cycle-in-a-graph
     */
    public static class HttpsLeetcodeComProblemsShortestCycleInAGraph {
    }

    /**
     * Use Disjoint Set Union (Union-Find) to track connected components while adding edges one by one.
     */
    public static class UseDisjointSetUnionUnionFindToTrackConnectedComponentsWhileAddingEdgesOneByOne {
    }

    /**
     * Initially, every node is its own component.
     */
    public static class InitiallyEveryNodeIsItsOwnComponent {
    }

    /**
     * When we add an edge (u, v):
     */
    public static class WhenWeAddAnEdgeUV {
    }

    /**
     * If u and v are already in the same component, adding this edge creates a cycle.
     */
    public static class IfUAndVAreAlreadyInTheSameComponentAddingThisEdgeCreatesACycle {
    }

    /**
     * That edge is exactly the redundant connection.
     */
    public static class ThatEdgeIsExactlyTheRedundantConnection {
    }

    /**
     * If they are in different components, we safely merge them.
     */
    public static class IfTheyAreInDifferentComponentsWeSafelyMergeThem {
    }

    /**
     * Because edges are processed in order, the first edge that fails to union is the answer.
     */
    public static class BecauseEdgesAreProcessedInOrderTheFirstEdgeThatFailsToUnionIsTheAnswer {
    }
}

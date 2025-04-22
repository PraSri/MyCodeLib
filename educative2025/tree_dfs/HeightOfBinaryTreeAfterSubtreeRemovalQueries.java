package tree_dfs;

import java.util.*;

public class HeightOfBinaryTreeAfterSubtreeRemovalQueries {

    // store the depth of each node
    Map<Integer, Integer> nodeDepth = new HashMap<>();

    // store the height of each node
    Map<Integer, Integer> nodeHeight = new HashMap<>();

    public int[] treeQueries(TreeNode<Integer> root, int[] queries) {

        int[] result = new int[queries.length];

        // start the dfs from the root with depth 0
        treeDfs(root, 0);

        // group nodes by their depth & keep the top two nodes
        Map<Integer, List<int[]>> depthGroups = new HashMap<>();

        // populate the depthGroups map with top 2 heights at each depth
        for (Map.Entry<Integer, Integer> entry : nodeDepth.entrySet()) {
            int node = entry.getKey();
            int depth = entry.getValue();
            // while populating, push the height & node value as tuple
            depthGroups.computeIfAbsent(depth, k -> new ArrayList<>()).add(new int[]{nodeHeight.get(node), node});
        }

        // iterate the depthGroups map
        for (List<int[]> group : depthGroups.values()) {
            // sort the height in descending order
            group.sort((a, b) -> (b[0] - a[0]));
            // keep only 2 nodes by clearing others nodes
            if (group.size() > 2) {
                group.subList(2, group.size()).clear();
            }
        }

        // process each query
        for (int i = 0; i < queries.length; i++) {
            int q = queries[i];
            // get the depth of the queried node
            int depth = nodeDepth.get(q);
            List<int[]> depthGroup = depthGroups.get(depth);
            // if no other node at the same depth, path length = depth - 1;
            if (depthGroup.size() == 1) {
                result[i] = depth - 1;
            } else if (depthGroup.get(0)[1] == q) {
                // else if removed node has the highest height, use the 2nd highest height
                result[i] = depthGroup.get(1)[0] + depth;
            } else {
                // otherwise use the largest height at the same depth
                result[i] = depthGroup.get(0)[0] + depth;
            }
        }

        // return the result
        return result;
    }

    // Depth-first search to populate node depth & node height maps
    private int treeDfs(TreeNode<Integer> root, int depth) {
        // if root is null return -1
        if (root == null) {
            return -1;
        }

        // record the depth of the current node
        nodeDepth.put(root.data, depth);
        int leftHeight = treeDfs(root.left, depth + 1);
        int rightHeight = treeDfs(root.right, depth + 1);

        // calculate the height of the current node based on its children
        int height = Math.max(leftHeight, rightHeight) + 1;

        // record the height of the current node
        nodeHeight.put(root.data, height);
        return height;
    }

}

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        dfs(root, targetSum, currentPath, results);
        return results;
    }

    private void dfs(TreeNode node, int remaining, List<Integer> currentPath, List<List<Integer>> results) {
        if (node == null) return;

        currentPath.add(node.val);

        // Check if current node is a leaf and completes the target sum
        if (node.left == null && node.right == null && remaining == node.val) {
            results.add(new ArrayList<>(currentPath));
        } else {
            dfs(node.left, remaining - node.val, currentPath, results);
            dfs(node.right, remaining - node.val, currentPath, results);
        }

        // Backtrack
        currentPath.remove(currentPath.size() - 1);
    }
}

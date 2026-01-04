package trees;

/**Path Sum (Easy)
https://leetcode.com/problems/path-sum/

Sum Root to Leaf Numbers (Medium)
https://leetcode.com/problems/sum-root-to-leaf-numbers/

Path Sum IV (Medium)
https://leetcode.com/problems/path-sum-iv/

Longest Univalue Path (Medium)
https://leetcode.com/problems/longest-univalue-path/

Time Needed to Inform All Employees (Medium)
https://leetcode.com/problems/time-needed-to-inform-all-employees/

Difference Between Maximum and Minimum Price Sum (Hard)
https://leetcode.com/problems/difference-between-maximum-and-minimum-price-sum/*/

public class BinaryTreeMaximumPathSum {

    // Problem recap (simple words)

// Binary tree diya hai (nodes me positive + negative values).

// 👉 Path = koi bhi sequence of nodes, connected by edges
// 👉 Path kahin se start aur kahin pe end ho sakta hai
// 👉 Path branch ho sakta hai, but cycle nahi

// 🎯 Goal: kisi bhi path ka maximum sum nikaalna

/**🧠 Core Idea (isko yaad rakh)

Har node pe 2 cheezein sochni hoti hain:

1️⃣ Agar mai parent ko path de raha hoon → kya bheju?

👉 Single side path (left ya right, dono nahi)

2️⃣ Agar mai yahin pe path tod du → best answer kya ban sakta hai?

👉 left + node + right

🔥 DFS ka role

Hum post-order DFS karte hain (left → right → node)*/

//“At each node, I compute the maximum gain from left and right subtrees.
// Negative gains are ignored.
// I update a global maximum considering the path passing through the node.
// I return only one side’s gain to the parent.”

    public int maxPathSum(TreeNode root) {
        int[] res = new int[]{root.val};
        dfs(root, res);
        return res[0];
    }

    private int dfs(TreeNode root, int[] res) {
        if(root == null) {
            return 0;
        }
        int l = Math.max(0, dfs(root.left, res));
        int r = Math.max(0, dfs(root.right, res));

        res[0] = Math.max(res[0], l + r + root.val);

        return root.val + Math.max(l,r);

    }

    /**
     * Path Sum
     * https://leetcode.com/problems/path-sum/
     */
    public static class PathSum {
    }

    /**
     * Sum Root to Leaf Numbers
     * https://leetcode.com/problems/sum-root-to-leaf-numbers/
     */
    public static class SumRootToLeafNumbers {
    }

    /**
     * Path Sum IV
     * https://leetcode.com/problems/path-sum-iv/
     */
    public static class PathSumIv {
    }

    /**
     * Longest Univalue Path
     * https://leetcode.com/problems/longest-univalue-path/
     */
    public static class LongestUnivaluePath {
    }

    /**
     * Time Needed to Inform All Employees
     * https://leetcode.com/problems/time-needed-to-inform-all-employees/
     */
    public static class TimeNeededToInformAllEmployees {
    }

    /**
     * Difference Between Maximum and Minimum Price Sum
     * https://leetcode.com/problems/difference-between-maximum-and-minimum-price-sum/
     */
    public static class DifferenceBetweenMaximumAndMinimumPriceSum {
    }
}

package greedy;

/***Jump Game (Medium)
 https://leetcode.com/problems/jump-game/

 Jump Game III (Medium)
 https://leetcode.com/problems/jump-game-iii/

 Jump Game VII (Medium)
 https://leetcode.com/problems/jump-game-vii/

 Jump Game VIII (Medium)
 https://leetcode.com/problems/jump-game-viii/

 Minimum Number of Visited Cells in a Grid (Hard)
 https://leetcode.com/problems/minimum-number-of-visited-cells-in-a-grid/

 Maximum Number of Jumps to Reach the Last Index (Medium)
 https://leetcode.com/problems/maximum-number-of-jumps-to-reach-the-last-index/

 Visit Array Positions to Maximize Score (Medium)
 https://leetcode.com/problems/visit-array-positions-to-maximize-score/*/

public class JumpGameII {
    //Input: nums = [2,3,1,1,4]
    //Output: 2
    public int jump(int[] nums) {

        int n = nums.length;

        int jumps = 0, l = 0, r = 0;

        while (r < n - 1) {
            int farthest = 0;
            for (int i = l; i <= r; i++) {
                farthest = Math.max(farthest, nums[i] + i);
            }
            l = r + 1;
            r = farthest;
            jumps++;
        }

        return jumps;
    }

    /**
     * Jump Game
     * https://leetcode.com/problems/jump-game
     */
    public static class JumpGame {
    }

    /**
     * Jump Game III
     * https://leetcode.com/problems/jump-game-iii
     */
    public static class JumpGameIii {
    }

    /**
     * Jump Game VII
     * https://leetcode.com/problems/jump-game-vii
     */
    public static class JumpGameVii {
    }

    /**
     * Jump Game VIII
     * https://leetcode.com/problems/jump-game-viii
     */
    public static class JumpGameViii {
    }

    /**
     * Minimum Number of Visited Cells in a Grid
     * https://leetcode.com/problems/minimum-number-of-visited-cells-in-a-grid
     */
    public static class MinimumNumberOfVisitedCellsInAGrid {
    }

    /**
     * Maximum Number of Jumps to Reach the Last Index
     * https://leetcode.com/problems/maximum-number-of-jumps-to-reach-the-last-index
     */
    public static class MaximumNumberOfJumpsToReachTheLastIndex {
    }

    /**
     * Visit Array Positions to Maximize Score
     * https://leetcode.com/problems/visit-array-positions-to-maximize-score
     */
    public static class VisitArrayPositionsToMaximizeScore {
    }
}

package greedy;

/**
 * Jump Game II (Medium)
 * https://leetcode.com/problems/jump-game-ii/
 * <p>
 * Jump Game III (Medium)
 * https://leetcode.com/problems/jump-game-iii/
 * <p>
 * Jump Game VII (Medium)
 * https://leetcode.com/problems/jump-game-vii/
 * <p>
 * Jump Game VIII (Medium)
 * https://leetcode.com/problems/jump-game-viii/
 * <p>
 * Minimum Number of Visited Cells in a Grid (Hard)
 * https://leetcode.com/problems/minimum-number-of-visited-cells-in-a-grid/
 * <p>
 * Largest Element in an Array after Merge Operations (Medium)
 * https://leetcode.com/problems/largest-element-in-an-array-after-merge-operations/
 */

public class JumpGame {

    //Tumhe ek array nums diya hai.
    //Har index pe nums[i] batata hai maximum kitna jump le sakte ho.
    //Start index = 0
    //Goal = last index tak pahunchna possible hai ya nahi?

    //? Last index se peeche ki taraf sochte hain

    //targetIndex = wo position jahan tak pahunchna zaroori hai
    //
    //Initially, target = last index
    //
    //Peeche se check karo:
    //
    //Agar current index i se targetIndex reachable hai
    //(i + nums[i] >= targetIndex)
    //
    //Toh target ko shift karke i bana do
    //
    //End me agar targetIndex == 0, matlab start se end tak pahunch sakte ho

    //Last index ko catch karte hue peeche aao; agar 0 pakad liya, game jeet gaye.

    public boolean canJump(int[] nums) {
        int n = nums.length;
        int targetIndex = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            if (targetIndex <= (i + nums[i])) {
                targetIndex = i;
            }
        }
        return targetIndex == 0;
    }

    /**
     * Jump Game II
     * https://leetcode.com/problems/jump-game-ii
     */
    public static class JumpGameIi {
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
     * Largest Element in an Array after Merge Operations
     * https://leetcode.com/problems/largest-element-in-an-array-after-merge-operations
     */
    public static class LargestElementInAnArrayAfterMergeOperations {
    }
}

package twopointers;

public class ContainerWithMostWater {

    /**
     * Trapping Rain Water
     * <a href="https://leetcode.com/problems/trapping-rain-water/">...</a>
     * <p>
     * Maximum Tastiness of Candy Basket
     * <a href="https://leetcode.com/problems/maximum-tastiness-of-candy-basket/">...</a>
     * <p>
     * House Robber IV
     * <a href="https://leetcode.com/problems/house-robber-iv/">...</a>
     */

    public int maxArea(int[] heights) {
        int n = heights.length;
        int l = 0;
        int r = n - 1;

        int res = Integer.MIN_VALUE;

        while(l<r) {

            var base = r - l;
            var height = Math.min(heights[l], heights[r]);
            var area = base * height;

            res = Math.max(res, area);

            if(heights[l] < heights[r]) {
                l++;

            } else {
                r--;

            }
        }

        return res;
    }

    /**
     * <a href="https://leetcode.com/problems/trapping-rain-water/">LeetCode - Trapping Rain Water</a>
     */
    public static class TrappingRainWater {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/maximum-tastiness-of-candy-basket/">LeetCode - Maximum Tastiness of Candy Basket</a>
     */
    public static class MaximumTastinessOfCandyBasket {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/house-robber-iv/">LeetCode - House Robber IV</a>
     */
    public static class HouseRobberIV {
        // placeholder
    }
}

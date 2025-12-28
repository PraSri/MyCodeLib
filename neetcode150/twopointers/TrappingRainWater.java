package twopointers;

public class TrappingRainWater {

    /**
     * Container With Most Water
     * <a href="https://leetcode.com/problems/container-with-most-water/">...</a>
     * <p>
     * Product of Array Except Self
     * <a href="https://leetcode.com/problems/product-of-array-except-self/">...</a>
     * <p>
     * Trapping Rain Water II
     * <a href="https://leetcode.com/problems/trapping-rain-water-ii/">...</a>
     * <p>
     * Pour Water
     * <a href="https://leetcode.com/problems/pour-water/">...</a>
     * <p>
     * Maximum Value of an Ordered Triplet II
     * <a href="https://leetcode.com/problems/maximum-value-of-an-ordered-triplet-ii/">...</a>
     */

    public int trap(int[] height) {

        int n = height.length;

        // water cannot be trapped, it needs at least 3 bars
        if(n<=2)
            return 0;

        // 2 pointer approach
        int left = 0;
        int right = n-1;

        // track max from both sides
        int leftMax = height[left];
        int rightMax = height[right];

        int area = 0;

        while(left < right) {

//            Agar left height < right height hai,
//            toh left side ka max hi decide karega ki kitna water trap hoga.
//            Right side ka height already bada hai, toh woh boundary ban chuka hai.
//            Overflow nahi hota � bas left ke liye water trapping tabhi calculate ho sakti
//            hai jab left side boundary choti ho. Warna right side limit ban jayegi.
            if(height[left] < height[right]) {
                // next pillar pe jao
                left++;
                // max check kro, kyuki water trap lestMax aur cur left ke beech me hi hoga
                leftMax = Math.max(leftMax, height[left]);
                //Water trapped = leftMax - currentHeight (only if leftMax is greater)
                area += leftMax - height[left];
            } else  {
                right--;
                rightMax = Math.max(rightMax, height[right]);
                area += rightMax - height[right];
            }

        }

        /**
         * Main logic:
         *
         * Dono sides se max height track karo (leftMax, rightMax).
         *
         * Jis side ka height chota ho, wahi water trapping decide karega.
         *
         * Isliye:
         *
         * Agar height[left] < height[right], toh left pointer move hoga.
         *
         * Warna right pointer move hoga.
         *
         * Why move smaller side?
         *
         * Smaller side hi bottleneck hota hai.
         *
         * Agar left < right, toh right wali wall hamesha left ke liye ek valid boundary hai.
         *
         * Toh left wale block ke upar : water = leftMax - height[left].
         *
         * Process:
         *
         * Pointer move karo.
         *
         * Max update karo.
         *
         * Water accumulate karo.
         *
         * Final area = total trapped water.
         * */

        return area;
    }


    /**
     * <a href="https://leetcode.com/problems/container-with-most-water/">LeetCode - Container With Most Water</a>
     */
    public static class ContainerWithMostWater {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/product-of-array-except-self/">LeetCode - Product of Array Except Self</a>
     */
    public static class ProductOfArrayExceptSelf {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/trapping-rain-water-ii/">LeetCode - Trapping Rain Water II</a>
     */
    public static class TrappingRainWaterII {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/pour-water/">LeetCode - Pour Water</a>
     */
    public static class PourWater {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/maximum-value-of-an-ordered-triplet-ii/">LeetCode - Maximum Value of an Ordered Triplet II</a>
     */
    public static class MaximumValueOfAnOrderedTripletII {
        // placeholder
    }
}

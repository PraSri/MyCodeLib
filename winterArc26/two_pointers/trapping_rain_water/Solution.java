package two_pointers.trapping_rain_water;

public class Solution {

    public int trap(int[] height) {
        int l = 0;
        int r = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int water = 0;
        while (l < r) {
            if (height[l] < height[r]) {
                leftMax = Math.max(leftMax, height[l]);
                water += leftMax - height[l];
                l++;
            } else {
                rightMax = Math.max(rightMax, height[r]);
                water += rightMax - height[r];
                r--;
            }
        }
        return water;
    }
}

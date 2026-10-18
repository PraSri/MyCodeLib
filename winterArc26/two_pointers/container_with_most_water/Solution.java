package two_pointers.container_with_most_water;

public class Solution {

    public int maxArea(int[] height) {
        int l = 0;
        int r = height.length - 1;
        int best = 0;
        while (l < r) {
            best = Math.max(best, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return best;
    }
}

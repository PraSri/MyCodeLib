package twopointers;

public class ContainerWithMostWater {

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

}

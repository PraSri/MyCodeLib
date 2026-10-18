package monotonic_stack.largest_rectangle_in_histogram;

public class Solution {

    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] stack = new int[n + 1];
        int top = -1;
        int best = 0;
        for (int i = 0; i <= n; i++) {
            int current = i == n ? 0 : heights[i];
            while (top >= 0 && heights[stack[top]] >= current) {
                int height = heights[stack[top--]];
                int left = top >= 0 ? stack[top] : -1;
                best = Math.max(best, height * (i - left - 1));
            }
            stack[++top] = i;
        }
        return best;
    }
}

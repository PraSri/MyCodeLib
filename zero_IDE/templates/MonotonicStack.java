import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Monotonic Stack Template
 *
 * Patterns:
 * 1. Next Greater Element: Stack holds indices with decreasing values.
 * 2. Next Smaller / Previous Smaller (e.g. Histogram width calculation).
 */
public class MonotonicStack {

    // Next Greater Element (returns array of next greater values, or -1 if none)
    public static int[] nextGreaterElement(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);
        Deque<Integer> stack = new ArrayDeque<>(); // stores indices

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                int prevIndex = stack.pop();
                result[prevIndex] = nums[i];
            }
            stack.push(i);
        }
        return result;
    }

    // Largest Rectangle in Histogram Idiom: Monotonic strictly increasing stack
    public static int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Deque<Integer> stack = new ArrayDeque<>(); // stores indices
        int maxArea = 0;

        for (int i = 0; i <= n; i++) {
            int currentHeight = (i == n) ? 0 : heights[i];
            while (!stack.isEmpty() && heights[stack.peek()] >= currentHeight) {
                int h = heights[stack.pop()];
                int w = stack.isEmpty() ? i : (i - stack.peek() - 1);
                maxArea = Math.max(maxArea, h * w);
            }
            stack.push(i);
        }
        return maxArea;
    }
}

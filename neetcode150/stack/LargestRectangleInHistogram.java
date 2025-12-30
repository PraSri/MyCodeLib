package stack;

import java.util.*;

/**Maximal Rectangle
https://leetcode.com/problems/maximal-rectangle/

Maximum Score of a Good Subarray
https://leetcode.com/problems/maximum-score-of-a-good-subarray/*/

public class LargestRectangleInHistogram {

    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i <= n; i++) {
            while (!stack.isEmpty() && (i == n || heights[stack.peek()] >= heights[i])) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }
        return maxArea;
    }

    public int largestRectangleArea2(int[] heights) {
        int n = heights.length;
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i <= n; i++) {
           //.....some code, jo check kre ki jo maine push kiya hai stack me
           // wo stack me rehne layak hai bhi ki nahi

           // agr nahi hai, toh usko nikaal ke 
           // usse max area lelo aur agey badho
            stack.push(i);
        }
        return maxArea;
    }

    public int largestRectangleArea3(int[] heights) {
        int n = heights.length;
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i <= n; i++) {
           while (!stack.isEmpty() && (i == n || heights[stack.peek()] >= heights[i])) {
                // ...ye tab tak kro
                // jab tak, stack empty na ho
                // ya i ka end na aa jae
                // ya stack top height badi ho curr height se
                // jaise hi badi height aaeyi, loop se nikalna hoga
            }
            stack.push(i);
        }
        return maxArea;
    }

    public int largestRectangleArea4(int[] heights) {
        int n = heights.length;
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i <= n; i++) {
           while (!stack.isEmpty() && (i == n || heights[stack.peek()] >= heights[i])) {
            // har baar height nikalo
                int height = heights[stack.pop()];
                // width nikalo
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                // area nikalo aur max area update kro
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }
        return maxArea;
    }

    /**
     * <a href="https://leetcode.com/problems/maximal-rectangle/">LeetCode - Maximal Rectangle</a>
     */
    public static class MaximalRectangle {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/maximum-score-of-a-good-subarray/">LeetCode - Maximum Score of a Good Subarray</a>
     */
    public static class MaximumScoreOfAGoodSubarray {
        // placeholder
    }

}

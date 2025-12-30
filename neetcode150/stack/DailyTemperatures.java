package stack;

import java.util.*;

/**Next Greater Element I
https://leetcode.com/problems/next-greater-element-i/

Online Stock Span
https://leetcode.com/problems/online-stock-span/*/

public class DailyTemperatures {

    public int[] dailyTemperatures(int[] temperatures) {

        int n = temperatures.length;

        int[] res = new int[n];

        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i<n; i++) {
            while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int j = stack.pop();
                res[j] = i - j;
            }
            stack.push(i);
        }

        return res;
    }

    /**
     * <a href="https://leetcode.com/problems/next-greater-element-i/">LeetCode - Next Greater Element I</a>
     */
    public static class NextGreaterElementI {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/online-stock-span/">LeetCode - Online Stock Span</a>
     */
    public static class OnlineStockSpan {
        // placeholder
    }

}

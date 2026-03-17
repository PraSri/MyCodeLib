package stack;

import java.util.Stack;

/**
 * Sliding Window Maximum (Hard)
 * https://leetcode.com/problems/sliding-window-maximum/
 * <p>
 * Max Stack (Hard)
 * https://leetcode.com/problems/max-stack/
 */

public class MinimumStack {
    //Input: ["MinStack", "push", 1, "push", 2, "push", 0, "getMin", "pop", "top", "getMin"]
    //
    //Output: [null,null,null,null,0,null,2,1]
    //
    //Explanation:
    //MinStack minStack = new MinStack();
    //minStack.push(1);
    //minStack.push(2);
    //minStack.push(0);
    //minStack.getMin(); // return 0
    //minStack.pop();
    //minStack.top();    // return 2
    //minStack.getMin(); // return 1

    static class MinStack {

        Stack<Integer> mainStack, minTrackStack;

        public MinStack() {
            mainStack = new Stack<>();
            minTrackStack = new Stack<>(); // keeps min value
        }

        public void push(int val) {
            mainStack.push(val);
            if (minTrackStack.empty()) {
                minTrackStack.push(val);
            } else {
                Integer currMin = minTrackStack.peek();
                if (currMin < val) {
                    minTrackStack.push(currMin);
                } else {
                    minTrackStack.push(val);
                }
            }
        }

        public void pop() {
            mainStack.pop();
            minTrackStack.pop();
        }

        public int top() {
            return mainStack.peek();
        }

        public int getMin() {
            return minTrackStack.peek();
        }
    }

    public static class MinStack2 {
        long min;
        Stack<Long> stack;

        public MinStack2() {
            stack = new Stack<>();
        }

        public void push(int val) {
            if (stack.isEmpty()) {
                stack.push(0L);
                min = val;
            } else {
                long diff = val - min;
                stack.push(diff);
                // agr diff -ve matlab naya val chota hai curr min se
                if (diff < 0) min = val;
            }
        }

        public void pop() {
            if (stack.isEmpty()) return;

            long pop = stack.pop();

            // restore previous min
            // diff = val - min
            // diff -ve => min = val
            // matlab min nikal gya
            // naya min = min - pop => min = min - diff
            if (pop < 0) min = min - pop;
        }

        public int top() {
            long top = stack.peek();
            if (top > 0) {
                return (int) (top + min);
            } else {
                return (int) min;
            }
        }

        public int getMin() {
            return (int) min;
        }
    }

    /**
     * <a href="https://leetcode.com/problems/sliding-window-maximum/">LeetCode - Sliding Window Maximum</a>
     */
    public static class SlidingWindowMaximum {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/max-stack/">LeetCode - Max Stack</a>
     */
    public static class MaxStack {
        // placeholder
    }

}

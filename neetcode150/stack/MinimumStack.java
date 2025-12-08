package stack;

import java.util.*;

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

        Stack<Integer> s1, s2;

        public MinStack() {
            s1 = new Stack<>();
            s2 = new Stack<>(); // keeps min value
        }

        public void push(int val) {
            s1.push(val);
            if(s2.empty()) {
                s2.push(val);
            } else {
                Integer currMin = s2.peek();
                if(currMin < val) {
                    s2.push(currMin);
                } else {
                    s2.push(val);
                }
            }
        }

        public void pop() {
            s1.pop();
            s2.pop();
        }

        public int top() {
            return s1.peek();
        }

        public int getMin() {
            return s2.peek();
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
                stack.push(val - min);
                if (val < min) min = val;
            }
        }

        public void pop() {
            if (stack.isEmpty()) return;

            long pop = stack.pop();

            // restore previous min
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

}

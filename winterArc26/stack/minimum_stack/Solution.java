package stack.minimum_stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

    public static class MinStack {
        private final Deque<int[]> stack = new ArrayDeque<>(); // {value, minSoFar}

        public MinStack() {
        }

        public void push(int val) {
            int min = stack.isEmpty() ? val : Math.min(val, stack.peek()[1]);
            stack.push(new int[]{val, min});
        }

        public void pop() {
            stack.pop();
        }

        public int top() {
            return stack.peek()[0];
        }

        public int getMin() {
            return stack.peek()[1];
        }
    }
}

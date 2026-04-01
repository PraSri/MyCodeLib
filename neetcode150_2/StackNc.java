import java.util.Arrays;
import java.util.Stack;

public class StackNc {

    // valid parenthesis
    public boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '[' || c == '(' || c == '{') {
                stack.push(c);
            } else {
                if (!stack.isEmpty()) {
                    char p = stack.pop();
                    if (c == ']' && p != '[') {
                        return false;
                    }
                    if (c == '}' && p != '{') {
                        return false;
                    }
                    if (c == ')' && p != '(') {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    // Evaluate reverse polish notation
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String s : tokens) {
            if (s.equals("+")) {

                stack.push(stack.pop() + stack.pop());

            } else if (s.equals("-")) {
                int x = stack.pop();
                int y = stack.pop();
                stack.push(y - x);

            } else if (s.equals("*")) {

                stack.push(stack.pop() * stack.pop());

            } else if (s.equals("/")) {
                int x = stack.pop();
                int y = stack.pop();
                stack.push(y / x);
            } else {
                stack.push(Integer.parseInt(s));
            }
        }
        return stack.pop();
    }

    // Daily temperatures - poor judgement on logic, put more brain to implement
    // thought in the right direction, but got blank while implementing
    // this is what exactly happening in real interviews

    public int[] dailyTemperatures(int[] temperatures) {

        Stack<Integer> stack = new Stack<>();
        int n = temperatures.length;
        int[] res = new int[n];

        for (int i = 0; i < n; i++) {
            int curr = temperatures[i];
            while (!stack.isEmpty() && curr > temperatures[stack.peek()]) {
                int j = stack.pop();
                res[j] = i - j;
            }
            stack.push(i);

        }

        return res;

    }

    // Car fleet - > need good thinking and strong practice to do in interview
    // A car forms a new fleet only if it takes longer than the fleet in front of it.
    public int carFleetViaStack(int target, int[] position, int[] speed) {
        Stack<Double> stack = new Stack<>();
        int n = position.length;
        int[][] pair = new int[n][2];
        for (int i = 0; i < n; i++) {
            pair[i][0] = position[i];
            pair[i][1] = speed[i];
        }
        Arrays.sort(pair, (a, b) -> Integer.compare(b[0], a[0]));
        for (int[] p : pair) {
            double time = ((double) target - p[0]) / p[1];
            stack.push(time);
            if (stack.size() >= 2 && stack.peek() <= stack.get(stack.size() - 2)) {
                stack.pop();
            }
        }
        return stack.size();
    }

    public int carFleetViaIteration(int target, int[] position, int[] speed) {
        int n = position.length;
        double[][] pair = new double[n][2];
        for (int i = 0; i < n; i++) {
            pair[i][0] = position[0];
            pair[i][1] = (double) (target - position[i]) / speed[i];
        }
        // sort in descending order of position
        Arrays.sort(pair, (a, b) -> Double.compare(b[0], a[0]));
        int fleet = 1;
        double prevTime = pair[0][1];
        for (int i = 1; i < n; i++) {
            double currTime = pair[i][1];
            if (currTime > prevTime) {
                fleet++;
                prevTime = currTime;
            }
        }
        return fleet;
    }

    //Largest Rectangle In Histogram -> hard question, practice harder
    // heights = [7,1,7,2,2,4]
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i <= n; i++) {
            while (!stack.isEmpty() &&
                    (i == n || heights[i] <= heights[stack.peek()])) {
                int height = heights[stack.pop()];
                int width = stack.empty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }
        return maxArea;
    }

    // determine for each bar how far we can go left
    // and right without encountering the shorter bar
    public int largestRectangleAreaIntuitive(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Stack<Integer> stack = new Stack<>();

        //NSL - nearest smallest left
        for (int i = 0; i < n; i++) {
            while (!stack.empty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        stack.clear();

        //NSR - nearest smallest right
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.empty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            right[i] = stack.empty() ? n : stack.peek();
            stack.push(i);
        }

        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            int width = right[i] - left[i] - 1;
            maxArea = Math.max(maxArea, heights[i] * width);
        }

        return maxArea;
    }

    // Min Stack - practice hard, get intuition
    // that you have keep min stack up to date with min value
    static class MinStack {

        Stack<Integer> stack, minStack;

        public MinStack() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int val) {
            stack.push(val);
            if (minStack.isEmpty()) {
                minStack.push(val);
            } else {
                if (minStack.peek() > val) {
                    minStack.push(val);
                } else {
                    minStack.push(minStack.peek());
                }
            }
        }

        public void pop() {
            stack.pop();
            minStack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.isEmpty() ? -1 : minStack.peek();
        }
    }

    static class MinStackV2 {

        Stack<Long> stack; // put diff of val & min
        long min;

        public MinStackV2() {
            stack = new Stack<>();
        }

        public void push(int val) {
            if (stack.isEmpty()) {
                stack.push(0L);
                min = val;
            } else {
                long diff = val - min;
                stack.push(diff);
                if (diff < 0) {
                    min = val;
                }
            }
        }

        public void pop() {

            if (stack.isEmpty())
                return;

            long pop = stack.pop();

            if (pop < 0) {
                min = min - pop;
            }

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

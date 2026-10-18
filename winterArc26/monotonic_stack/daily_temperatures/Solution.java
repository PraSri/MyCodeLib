package monotonic_stack.daily_temperatures;

public class Solution {

    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        int[] stack = new int[n];
        int top = -1;
        for (int i = 0; i < n; i++) {
            while (top >= 0 && temperatures[stack[top]] < temperatures[i]) {
                int day = stack[top--];
                answer[day] = i - day;
            }
            stack[++top] = i;
        }
        return answer;
    }
}

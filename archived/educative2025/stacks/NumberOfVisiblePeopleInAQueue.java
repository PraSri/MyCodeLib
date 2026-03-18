package stacks;

import java.util.ArrayDeque;
import java.util.Deque;

public class NumberOfVisiblePeopleInAQueue {

    public int[] canSeePersonsCount(int[] heights) {
        int n = heights.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int[] answer = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            // pop all people shorter then curr
            while (!stack.isEmpty() && heights[i] > stack.peek()) {
                stack.poll();
                answer[i]++;
            }
            //taller or equal remains. means visible
            if (!stack.isEmpty()) {
                answer[i]++;
            }
            stack.push(heights[i]);
        }
        return answer;
    }
}

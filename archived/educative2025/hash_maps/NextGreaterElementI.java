package hash_maps;

import java.util.*;

public class NextGreaterElementI {

    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {

        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();

        // iterate over nums2
        for (int current : nums2) {
            // while stack is not empty and current element is greater than the top element of the stack
            while (!stack.empty() && current > stack.peek()) {
                // update the map with the current element as the value for the popped element
                map.put(stack.pop(), current);
            }
            // push the current element to the stack
            stack.push(current);
        }

        // iterate over remaining elements in the stack, pop them and set their values to -1 in the map
        while (!stack.empty()) {
            map.put(stack.pop(), -1);
        }

        int[] ans = new int[nums1.length];
        // iterate over nums1 and add the corresponding value from the map to ans
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = map.get(nums1[i]);
        }

        return ans;
    }


}

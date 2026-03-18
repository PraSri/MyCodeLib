package sort_and_search;

import java.util.Arrays;

public class ValidTriangleNumber {
    public static int triangleNumber(int[] nums) {
        // Sort the array to facilitate the two-pointer approach
        Arrays.sort(nums);
        int count = 0;

        // Iterate backward through the array, treating nums[i] as the largest side
        for (int i = nums.length - 1; i >= 2; i--) {
            int left = 0, right = i - 1; // Two pointers: left (smallest side), right (second largest side)

            // Search to find all valid pairs (nums[left], nums[right]) for nums[i]
            while (left < right) {
                // If the sum of the two smaller sides is greater than the largest side
                if (nums[left] + nums[right] > nums[i]) {
                    // All pairs between left and right are valid
                    count += right - left;
                    // Move the right pointer to explore smaller second largest sides
                    right--;
                } else {
                    // Otherwise, move the left pointer to explore larger smallest sides
                    left++;
                }
            }
        }

        return count;
    }
}

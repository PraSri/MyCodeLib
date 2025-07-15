package sliding_window;

import java.util.Arrays;

public class FrequencyOfMostFrequentElement {

    public int maxFrequency(int[] nums, int k) {

        Arrays.sort(nums); // O(log n)

        int windowSum = 0;
        int left = 0;
        int maxFreq = 0;
        for (int right = 0; right < nums.length; right++) {
            int target = nums[right];
            windowSum += target;
            // sum if we make all elements in window equal to target - (right - left + 1) * target
            // sum if we apply k operations - windowSum + k
            while ((right - left + 1) * target > windowSum + k) {
                // shrink the window
                windowSum -= nums[left];
                left++;
            }
            maxFreq = Math.max(maxFreq, (right + left - 1));
        }
        return maxFreq;
    }

    public int maxFrequency2(int[] nums, int k) {

        // Sort the array to facilitate the sliding window approach
        Arrays.sort(nums);

        // 1. 'left' = Left pointer of the window
        // 2. 'maxFreq' = Stores the maximum frequency found
        int left = 0, maxFreq = 0;

        // Sum of elements within the current window
        long windowSum = 0;

        // Expand the window by moving the right pointer
        for (int right = 0; right < nums.length; right++) {

            // Target element to make frequent
            long target = nums[right];

            // Update the sum of elements in the window
            windowSum += target;

            // Check if the total required increments exceed k
            while ((right - left + 1) * target > windowSum + k) {

                // Remove the leftmost element
                windowSum -= nums[left];

                // Shrink the window from the left
                left++;
            }

            // Update the maximum frequency found
            maxFreq = Math.max(maxFreq, right - left + 1);
        }

        return maxFreq;
    }

    public static void main(String[] args) {
        FrequencyOfMostFrequentElement obj = new FrequencyOfMostFrequentElement();
        System.out.println(obj.maxFrequency2(new int[]{1,2,4}, 5));
    }
}

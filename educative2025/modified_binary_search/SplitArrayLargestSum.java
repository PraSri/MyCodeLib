package modified_binary_search;

public class SplitArrayLargestSum {
  // Check if the array can be split into k or fewer subarrays with the maximum sum as mid
  public boolean canSplit(int[] nums, int k, int mid) {
    // Initialize the count of subarrays and the current sum of the current subarray
    int subarrays = 1;
    int currentSum = 0;

    for (int num : nums) {
      // Check if adding the current number exceeds the allowed sum (mid)
      if (currentSum + num > mid) {
        // Increment the count of subarrays
        subarrays += 1;
        // Start a new subarray with the current number
        currentSum = num;

        // If the number of subarrays exceeds the allowed k, return False
        if (subarrays > k) {
          return false;
        }
      } else {
        // Otherwise, add the number to the current subarray
        currentSum += num;
      }
    }

    // Return True if the array can be split within the allowed subarray count
    return true;
  }

  public int splitArray(int[] nums, int k) {
    // Set the initial search range for the largest sum:
    // Minimum is the largest number in the array, and maximum is the sum of all numbers
    int left = Integer.MIN_VALUE, right = 0;
    for (int num : nums) {
      left = Math.max(left, num);
      right += num;
    }

    // Perform binary search to find the minimum largest sum
    while (left < right) {
      // Find the middle value of the current range
      int mid = (left + right) / 2;

      // Check if the array can be split into k or fewer subarrays with this maximum sum
      if (canSplit(nums, k, mid)) {
        // If possible, try a smaller maximum sum
        right = mid;
      } else {
        // Otherwise, increase the range to allow larger sums
        left = mid + 1;
      }
    }

    // Return the smallest maximum sum that satisfies the condition
    return left;
  }
  
  // Driver code
}

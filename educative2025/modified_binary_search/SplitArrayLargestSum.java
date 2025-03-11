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
  public static void main(String[] args) {
    Solution solution = new Solution();

    int[][] splits = {
            {3, 4, 6, 3},
            {2, 7, 8, 9, 2, 1, 4},
            {12, 53, 43, 67, 35},
            {4, 6, 4, 6, 4, 6},
            {11, 11, 11, 11, 11}
    };
    int[] k = {3, 6, 5, 4, 2};

    for (int i = 0; i < splits.length; i++) {
      System.out.println((i + 1) + ".\tInput Array: " + java.util.Arrays.toString(splits[i]));
      System.out.println("\tk: " + k[i]);
      System.out.println("\tLargest minimized sum: " + solution.splitArray(splits[i], k[i]));
      System.out.println(new String(new char[100]).replace('\0', '-'));
    }
  }
}

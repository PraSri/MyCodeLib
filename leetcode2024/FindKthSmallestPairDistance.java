package leetcode2024;

import java.util.Arrays;

public class FindKthSmallestPairDistance {

  public static void main(String[] args) {

  }

  public int smallestDistancePair(int[] nums, int k) {

    Arrays.sort(nums);
    int n = nums.length;

//    int s = nums[1] - nums[0];
//    for (int i = 1; i < n - 1; i++) {
//      s = Math.min(s, (nums[i + 1] - nums[i]));
//    }
    int s = 0;
    int e = nums[n - 1] - nums[0];

    while (s < e) {
      int mid = s + (e - s) / 2;

      if (enough(mid, nums, k)) {
        e = mid;
      } else {
        s = mid + 1;
      }

    }

    return s;

  }

  private boolean enough(int x, int[] nums, int k) {

    int count = 0;

    for (int i = 0; i < nums.length; i++) {
      int j = i; // fast pointer to scan till its diff is lesser than equal to x
      while (j < nums.length && nums[j] - nums[i] <= x) {
        j++;
      }
      count += (j - i - 1); // count pairs
    }

    // see if count of x is greater than equal k,
    return count >= k;
  }

}

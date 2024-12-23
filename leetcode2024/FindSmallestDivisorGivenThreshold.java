package leetcode2024;

import java.util.Arrays;

public class FindSmallestDivisorGivenThreshold {

  public static void main(String[] args) {
    FindSmallestDivisorGivenThreshold ob = new FindSmallestDivisorGivenThreshold();
    System.out.println(ob.smallestDivisor(new int[] {1, 2, 5, 9}, 6));

  }

  public int smallestDivisor(int[] nums, int threshold) {

    int s = 1;
    int e = Arrays.stream(nums).max().getAsInt();

    while (s < e) {
      int mid = s + (e - s) / 2;
      if (isPossible(mid, nums, threshold)) {
        e = mid;
      } else {
        s = mid + 1;
      }
    }

    return s;

  }

  private boolean isPossible(int mid, int[] nums, int threshold) {

    int sum = 0;

    for (int num : nums) {
      sum += Math.ceil((double)(num) / (double)(mid));
    }

    return sum <= threshold;

  }


}

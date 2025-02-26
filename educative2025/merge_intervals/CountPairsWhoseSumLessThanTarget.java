package package2;

import java.util.*;

public class CountPairsWhoseSumLessThanTarget {
  
  public static int countPairs(List<Integer> nums, int target) {
    // Sort the array to enable the two-pointer approach
    Collections.sort(nums);
    int count = 0; // Initialize the count of valid pairs
    int low = 0, high = nums.size() - 1; // Initialize two pointers

    // Loop until the two pointers meet
    while (low < high) {
      // Check if the sum of the current pair is less than the target
      if (nums.get(low) + nums.get(high) < target) {
        // All pairs between low and high are valid, because array is sorted
        count += (high - low);
        // Move the low pointer to explore more pairs
        low++;
      } else {
        // If the sum is not less than the target, move the high pointer
        high--;
      }
    }

    // Return the total count of pairs
    return count;
  }

  public static void main(String[] args) {
    List<List<Integer>> testCases = Arrays.asList(
        Arrays.asList(10, 1, 6, 2, 3, 8),
        Arrays.asList(1, 3, 5, 7),
        Arrays.asList(1, 2, 3, 6),
        Arrays.asList(2, 4, 6, 8, 10),
        Arrays.asList(5, 1, 9, 2)
    );
    List<Integer> targets = Arrays.asList(9, 8, 6, 12, 10);

    for (int i = 0; i < testCases.size(); i++) {
      List<Integer> nums = testCases.get(i);
      int target = targets.get(i);
      System.out.println((i + 1) + "\tnums: " + nums);
      System.out.println("\ttarget: " + target);

      int result = countPairs(nums, target);
      System.out.println("\n\tNumber of valid pairs: " + result);
      System.out.println(new String(new char[100]).replace('\0', '-'));
    }
  }

}

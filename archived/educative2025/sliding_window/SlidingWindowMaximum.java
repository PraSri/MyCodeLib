package sliding_window;

import java.util.*;
import java.util.stream.*;

public class SlidingWindowMaximum {


  // function to clean up the window
  // keep the max element in window, if window has lower elements remove from it
  public static void cleanUp(int i, Deque<Integer> currentWindow, int[] nums) {
    while (!currentWindow.isEmpty() && nums[i] >= nums[currentWindow.getLast()]) {
      currentWindow.removeLast();
    }
  }


  // function to find the maximum in all possible windows
  public static int[] findMaxSlidingWindow(int[] nums, int w) {

    if (nums.length == 1) {
      return nums;
    }

    // possible windows in array - (nums.length - w + 1)
    // eg. a[1,2,3], w = 2 => (1,2), (2,3) => a.len-w+1 => 3-2+1=2
    int[] output = new int[nums.length - w + 1];

    // add & remove from first & last in O(1) time complexity
    Deque<Integer> currentWindow = new ArrayDeque<>();

    // first window
    for (int i = 0; i < w; i++) {
      cleanUp(i, currentWindow, nums);
      currentWindow.add(i);
    }

    // result of 1st window
    output[0] = nums[currentWindow.getFirst()];

    // iterate from 2nd window till last
    for (int i = w; i < nums.length; i++) {
      // remove lower elements from window
      cleanUp(i, currentWindow, nums);
      // remove the elements indexes which don't exist in current window
      // sink the window
      if (!currentWindow.isEmpty() && currentWindow.getFirst() <= (i - w)) {
        currentWindow.removeFirst();
      }
      currentWindow.add(i);
      output[i - w + 1] = nums[currentWindow.getFirst()];
    }
    return output;
  }




  // driver code
  public static void main(String[] args) {
    int[] windowSizes = {3, 3, 3, 3, 2, 4, 3, 2, 3, 6};
    int[][] numLists = {
        {1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
        {10, 9, 8, 7, 6, 5, 4, 3, 2, 1},
        {10, 10, 10, 10, 10, 10, 10, 10, 10, 10},
        {1, 5, 8, 10, 10, 10, 12, 14, 15, 19, 19, 19, 17, 14, 13, 12, 12, 12, 14, 18, 22, 26, 26,
            26, 28, 29, 30},
        {10, 6, 9, -3, 23, -1, 34, 56, 67, -1, -4, -8, -2, 9, 10, 34, 67},
        {4, 5, 6, 1, 2, 3},
        {9, 5, 3, 1, 6, 3},
        {2, 4, 6, 8, 10, 12, 14, 16},
        {-1, -1, -2, -4, -6, -7},
        {4, 4, 4, 4, 4, 4}
    };

    for (int i = 0; i < numLists.length; i++) {
      System.out.println(i + 1 + ".\tInput array:\t" + Arrays.toString(numLists[i]));
      System.out.println("\tWindow size:\t" + windowSizes[i]);
      System.out.println("\n\tMaximum in each sliding window:\t" +
          Arrays.toString(findMaxSlidingWindow(numLists[i], windowSizes[i])));
      Stream.generate(() -> "-").limit(100).forEach(System.out::print);
      System.out.println();
    }
  }
}

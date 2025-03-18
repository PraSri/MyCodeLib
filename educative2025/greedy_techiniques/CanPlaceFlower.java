import java.util.*;

public class CanPlaceFlower {
  public static boolean canPlaceFlowers(int[] flowerbed, int n) {
    // Counter to track the number of flowers that can be planted
    int count = 0;

    // Loop through each plot in the flowerbed
    for (int i = 0; i < flowerbed.length; i++) {
      // Check if the current plot is empty
      if (flowerbed[i] == 0) {
        // Check if the left side is either at the beginning or has an empty plot
        boolean left = (i == 0 || flowerbed[i - 1] == 0);
        // Check if the right side is either at the end or has an empty plot
        boolean right = (i == flowerbed.length - 1 || flowerbed[i + 1] == 0);

        // If both left and right sides are empty, plant a flower here
        if (left && right) {
          flowerbed[i] = 1;
          count++;

          // If n flowers are planted, return true
          if (count == n) {
            return true;
          }
        }
      }
    }
    
    return count >= n;
  }
  
  // Driver code
  public static void main(String[] args) {
    int[][] flowerbeds = {
      {0, 0, 1, 0, 1, 0, 0, 0, 0, 1},
      {1, 0, 1, 0, 1, 0, 0, 1},
      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0},
      {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1}
    };
    
    int[] n = {2, 1, 6, 2, 3};

    for (int i = 0; i < flowerbeds.length; i++) {
      System.out.println((i + 1) + ".\tFlower bed: " + Arrays.toString(flowerbeds[i]));
      System.out.println("\tn: " + n[i]);
      System.out.println("\n\tFlowers planted: " + canPlaceFlowers(flowerbeds[i], n[i]));
      System.out.println(new String(new char[100]).replace('\0', '-'));
    }
  }
}

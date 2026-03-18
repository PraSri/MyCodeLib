package leetcode2024;

import java.util.Arrays;

public class CapacityToShipPackagesWithinDdays {

  public static void main(String[] args) {

  }


  public int shipWithinDays(int[] weights, int days) {

    // define range of answer space
    int s = Arrays.stream(weights).max().getAsInt();
    int e = Arrays.stream(weights).sum();

    while (s < e) {
      int mid = s + (e - s) / 2;
      if (shipDays(mid, weights) <= days) {
        // possible answer, but need to go for minimum value
        e = mid;
      } else {
        // +1 because mid was not possible as answer
        s = mid + 1;
      }
    }

    // minimum value will be return
    return s;

  }

  private int shipDays(int shipCap, int[] weights) {

    int cap = 0;
    int days = 1;

    for (int w : weights) {
      if (w + cap > shipCap) {
        days++;
        cap = w;
      } else {
        cap += w;
      }
    }

    return days;

  }

}

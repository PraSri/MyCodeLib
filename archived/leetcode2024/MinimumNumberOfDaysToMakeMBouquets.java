package leetcode2024;

public class MinimumNumberOfDaysToMakeMBouquets {

  public static void main(String[] args) {

  }

  public int minDays(int[] bloomDay, int m, int k) {

    int n = bloomDay.length;
    if (m * k > n) {
      return -1;
    }
    int s = 1;
    int e = (int) 1e9;
    while (s < e) {
      int mid = s + (e - s) / 2;
      if (isPossible(bloomDay, m, k, mid)) {
        e = mid;
      } else {
        s = mid + 1;
      }
    }

    return s;

  }

  private boolean isPossible(int[] bloomDay, int m, int k, int day) {
    int n = bloomDay.length;
    int total = 0;
    for (int i = 0; i < n; i++) {
      int count = 0;
      while (i < n && count < k && bloomDay[i] <= day) {
        count++;
        i++;
      }
      // one bouquet is complete
      if (count == k) {
        total++;
        i--;
      }
      if (total >= m) {
        return true;
      }
    }

    return false;

  }

}

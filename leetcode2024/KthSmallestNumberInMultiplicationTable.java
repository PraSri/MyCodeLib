package leetcode2024;

public class KthSmallestNumberInMultiplicationTable {

  public static void main(String[] args) {

    KthSmallestNumberInMultiplicationTable obj = new KthSmallestNumberInMultiplicationTable();
    System.out.println(obj.findKthNumber(3, 3, 5));

  }

  public int findKthNumber(int m, int n, int k) {

    // define range
    int s = 1;
    int e = m * n;

    while (s < e) {
      int mid = s + (e - s) / 2;
      if (enough(mid, m, n, k)) {
        e = mid;
      } else {
        s = mid + 1;
      }
    }

    return s;

  }

  private boolean enough(int num, int m, int n, int k) {

    // count of numbers before num
    int count = 0;

    for (int i = 1; i <= m; i++) {
      count += Math.min(num / i, n);
    }

    return count >= k;

  }

}

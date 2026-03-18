package leetcode2024;

public class UglyNumberIII {

  /**
   * An ugly number is a positive integer that is divisible by a, b, or c.
   * Given four integers n, a, b, and c, return the nth ugly number.
   */

  long a, b, c, ab, bc, ac, abc;

  public static void main(String[] args) {
    UglyNumberIII obj = new UglyNumberIII();
    int n = 3, a = 2, b = 3, c = 5;
    int ans = obj.nthUglyNumber(n, a, b, c);
    System.out.println(ans);
  }

  public int nthUglyNumber(int n, int a, int b, int c) {

    this.a = a;
    this.b = b;
    this.c = c;

    ab = ((long) a * b) / gcd(a, b);
    bc = ((long) b * c) / gcd(b, c);
    ac = ((long) a * c) / gcd(a, c);
    abc = ((long) a * bc) / gcd(a, (int) bc);

    long s = 0;
    long e = Long.MAX_VALUE;

    while (s < e) {
      long mid = s + (e - s) / 2;
      if (countUgly(mid) >= n) {
        e = mid;
      } else {
        s = mid + 1;
      }
    }

    return (int) s;

  }

  private long countUgly(long mid) {
    return mid / a + mid / b + mid / c - mid / ab - mid / bc - mid / ac + mid / abc;
  }

  public int gcd(int a, int b) {
    return (b == 0) ? a : gcd(b, a % b);
  }

}

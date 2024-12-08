package leetcode2024;

public class CanPlaceFlowers {

  public static void main(String[] args) {
    int[] f = new int[] {1, 0, 0, 0, 1, 0, 0};
    int n = 2;
    System.out.println(canPlaceFlowers(f, n));
  }

  public static boolean canPlaceFlowers(int[] flowerbed, int n) {

    for (int i = 0; i < flowerbed.length; i++) {
      if (flowerbed[i] == 0) {
        if (i == 0 && i == flowerbed.length - 1) {
          return true;
        } else if (i == 0 && flowerbed[i + 1] == 0) {
          flowerbed[i] = 1;
          n--;
        } else if (i == flowerbed.length - 1 && flowerbed[i - 1] == 0) {
          flowerbed[i] = 1;
          n--;
        } else if (i > 0 && flowerbed[i - 1] == 0) {
          if (i < flowerbed.length - 1 && flowerbed[i + 1] == 0) {
            flowerbed[i] = 1;
            n--;
          }
        }

      }
    }
    return n <= 0;

  }
}

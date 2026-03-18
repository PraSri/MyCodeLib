package leetcode2024;

import java.util.Arrays;

public class DestroyingAsteroids {

  public static void main(String[] args) {

  }

  public boolean asteroidsDestroyed(int mass, int[] asteroids) {

    Arrays.sort(asteroids);

    for (int asteroid : asteroids) {

      if (mass >= asteroid) {
        mass += asteroid;
      } else {
        return false;
      }
      if (mass > 100000) {
        return true;
      }

    }

    return true;


  }

}

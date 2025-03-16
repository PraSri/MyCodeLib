package package3;

import java.util.Arrays;

public class BoatsSavePeople {
  public int numRescueBoats(int[] people, int limit) {

    Arrays.sort(people);
    int left = 0;
    int right = people.length - 1;
    int boats = 0;
    while (left <= right) {
      if (people[left] + people[right] <= limit) {
        left++;
      }
      right--;
      boats++;
    }

    return boats;

  }
}

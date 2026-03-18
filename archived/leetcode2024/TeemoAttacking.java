package leetcode2024;

public class TeemoAttacking {

  public static void main(String[] args) {

  }


  public int findPoisonedDuration(int[] timeSeries, int duration) {

    int c = 0;

    for (int i = 0; i < timeSeries.length-1; i++) {
      if(timeSeries[i+1] <= timeSeries[i] + duration-1) {
        c += timeSeries[i+1] - timeSeries[i];
      }else {
        c += duration;
      }
    }

    c += duration;

    return c;

  }

}

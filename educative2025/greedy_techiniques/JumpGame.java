package package3;

public class JumpGame {
  public boolean canJump(int[] nums) {

    int n = nums.length;
    int targetIndex = n - 1;

    for (int i = n - 2; i >= 0; i--) {
      if (targetIndex <= (i + nums[i])) {
        targetIndex = i;
      }
    }

    return targetIndex == 0;

  }
}

package greedy;

public class JumpGameII {
    public int jump(int[] nums) {
        int n = nums.length;
        int jumps = 0, l = 0, r = 0;
        while(r < n-1) {
            int farthest = 0;
            for(int i = l; i<=r; i++) {
                farthest = Math.max(farthest, nums[i]+i);
            }
            l = r+1;
            r = farthest;
            jumps++;
        }
        return jumps;
    }
}

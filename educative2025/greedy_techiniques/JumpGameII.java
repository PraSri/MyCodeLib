class JumpGameII {
    public int jump(int[] nums) {
        
        int farthestJump = 0;
        int curJump = 0;
        int jumps = 0;
        for(int i = 0;i<nums.length-1;i++) {
            farthestJump = Math.max(farthestJump, i + nums[i]);
            if(i == curJump) {
                jumps++;
                curJump = farthestJump;
            }
        }
        return jumps;
    }
}

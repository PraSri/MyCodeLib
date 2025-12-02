package twopointers;

public class TwoIntegerSumII {
    public int[] twoSum(int[] numbers, int target) {
        int n = numbers.length;
        int l = 0;
        int r = n-1;
        while(l<r) {
            if(target - numbers[l] == numbers[r]) {
                return new int[]{l+1, r+1};
            } else if(target - numbers[l] > numbers[r]) {
                l++;
            } else {
                r--;
            }
        }
        return new int[]{-1,-1};
    }
}

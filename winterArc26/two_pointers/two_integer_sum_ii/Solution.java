package two_pointers.two_integer_sum_ii;

public class Solution {

    public int[] twoSum(int[] numbers, int target) {
        int l = 0;
        int r = numbers.length - 1;
        while (l < r) {
            long sum = (long) numbers[l] + numbers[r];
            if (sum == target) {
                return new int[]{l + 1, r + 1};
            }
            if (sum < target) {
                l++;
            } else {
                r--;
            }
        }
        throw new IllegalArgumentException("No solution");
    }
}

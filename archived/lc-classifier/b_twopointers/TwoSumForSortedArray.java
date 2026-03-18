package b_twopointers;

public class TwoSumForSortedArray {

    // https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/

    public int[] twoSum(int[] numbers, int target) {
        int n = numbers.length;
        int s = 0;
        int e = n - 1;
        while (s <= e) {
            int sum = numbers[s] + numbers[e];
            if (sum == target) {
                return new int[]{s + 1, e + 1};
            } else if (sum > target) {
                e--;
            } else {
                s++;
            }
        }
        return new int[]{-1, -1};
    }

}

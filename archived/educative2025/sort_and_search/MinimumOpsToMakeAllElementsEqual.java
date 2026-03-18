package sort_and_search;

import java.util.*;

public class MinimumOpsToMakeAllElementsEqual {

    public List<Long> minOperations(int[] nums, int[] queries) {
        Arrays.sort(nums);
        int n = nums.length;
        long[] prefixSum = new long[n + 1];
        prefixSum[0] = 0;
        for (int i = 0; i < n; i++) {
            prefixSum[i+1] = prefixSum[i] + nums[i];
        }

        List<Long> answer = new ArrayList<>();
        for (int query : queries) {
            // find idx in nums which is first number >= query // segregate the smaller part with greater part
            int idx = binarySearch(query, nums);
            long smallerElementsCost = ((long) idx * query) - prefixSum[idx];
            long largerElementsCost = (prefixSum[n] - prefixSum[idx]) - ((long) query * (n - idx));
            answer.add(smallerElementsCost + largerElementsCost);
        }
        return answer;
    }

    private int binarySearch(int query, int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] < query) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

}

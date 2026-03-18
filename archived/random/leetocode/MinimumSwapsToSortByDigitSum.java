package leetocode;

import java.util.Arrays;
import java.util.*;

public class MinimumSwapsToSortByDigitSum {

    private int sum(int x) {
        int s = 0;
        while(x>0) {
            s = s + x%10;
            x = x/10;
        }
        return s;
    }

    public int minSwaps(int[] nums) {

        int n = nums.length;

        int[][] a = new int[n][2];

        for (int i = 0; i<n; i++) {
            a[i][0] = sum(nums[i]);
            a[i][1] = nums[i];
        }

        Arrays.sort(a, (first, second) -> {
            if(first[0] != second[0]) return first[0] - second[0];
            return first[1] - second[1];
        });

        return solve(nums, a);
    }

    int solve(int[] nums, int[][] a) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i<n; i++) {
            map.put(nums[i], i);
        }
        boolean[] v = new boolean[n];
        int ans = 0;
        for (int i = 0; i<n; i++) {
            if(v[i] || nums[i] == a[i][1]) {
                continue;
            }
            int size = 0;
            int j = i;
            while (!v[j]) {
                v[j] = true;
                j = map.get(a[j][1]);
                size++;
            }
            if(size > 1) {
                ans += (size - 1);
            }
        }
        return ans;
    }

}

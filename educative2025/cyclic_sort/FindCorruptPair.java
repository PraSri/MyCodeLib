package cyclic_sort;

import java.util.Arrays;

public class FindCorruptPair {

    public static int[] getCorruptPair(int[] nums) {
        int n = nums.length;
        int i = 0;
        int[] res = new int[2];
        while(i < n) {
            int correctPos = nums[i]-1;
            if(correctPos >=0 && correctPos <n && nums[i]!=nums[correctPos]) {
                int temp = nums[i];
                nums[i] = nums[correctPos];
                nums[correctPos] = temp;
            }else {
                i++;
            }
        }
        for(int j = 0; j<n; j++) {
            if(j+1!=nums[j]) {
                res[0] = j+1;
                res[1] = nums[j];
                return res;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(getCorruptPair(new int[]{4, 1, 2, 1, 6, 3})));
    }

}

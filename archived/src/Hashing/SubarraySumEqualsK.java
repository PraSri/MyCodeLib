package Hashing;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {

 // At each step, we check if the counterpart of sum_till_now {i.e. negative sum_till_now} has been calculated previously or not, and if yes then how many times, that will help us to build the previous subarrays which could've been the answer candidate but were left out in the calculation up till now. Most helpful in case of arrays having negative values.
    
    // Best discussion : https://leetcode.com/problems/subarray-sum-equals-k/discuss/1532102/JAVA-oror-Picture-%2B-Detail-Explanation-oror-2-methods-oror-prefix-sum-%2B-HashMap-oror-Easy-Solution

    
    public int subarraySum(int[] nums, int k) { // [1,1,1] k = 2
        int n = nums.length;
        Map<Integer, Integer> m = new HashMap<>();
        int sum = 0, count = 0;
        m.put(0,1); 
        for(int i = 0; i<n; i++) {
            sum += nums[i]; // i=0, sum = 1, i=1 sum=2, i=2 sum=3
            if(m.containsKey(sum-k)) { // -1 not in map, 0 is in map, 1 is in map
                count += m.get(sum-k); //count = 1, count = 2
            }
            m.put(sum, m.getOrDefault(sum,0) + 1); // m {0:1,1:1, 2:1, 3:1}
        }
        return count;
    }


}

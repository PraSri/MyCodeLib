package arraysandhashing;

import java.util.*;

class TopKFrequentElements {
    
    public int[] topKFrequent(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> count = new HashMap<>();
        List<Integer>[] frq = new List[n+1];
        for(int i = 0;i<n;i++) {
            count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);
        }
        for(int i = 0;i<=n;i++) { 
            frq[i] = new ArrayList<>();
        }
        for(Map.Entry<Integer,Integer> me: count.entrySet()) {
            frq[me.getValue()].add(me.getKey());
        }
        int[] ans = new int[k];
        int j = 0;
        for(int i = frq.length - 1; i>0 && j<k; i--) {
            for(int f : frq[i]) {
                ans[j++] = f;
                if(j == k) {
                    break;
                }
            }
        }
        return ans;
    }
}

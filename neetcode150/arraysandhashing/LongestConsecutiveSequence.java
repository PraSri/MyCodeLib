package arraysandhashing;

import java.util.*;

public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> mp = new HashMap<>();
        int res = 0;

        for (int num : nums) {
            if (!mp.containsKey(num)) {
                mp.put(num, mp.getOrDefault(num - 1, 0) + mp.getOrDefault(num + 1, 0) + 1);
                mp.put(num - mp.getOrDefault(num - 1, 0), mp.get(num));
                mp.put(num + mp.getOrDefault(num + 1, 0), mp.get(num));
                res = Math.max(res, mp.get(num));
            }
        }
        return res;
    }

    public static int longestConsecutiveV2(int[] nums) {
        Map<Integer, Integer> lengthMap = new HashMap<>();
        int res = 0;

        for(int num: nums) {
            if(!lengthMap.containsKey(num)) {
                int leftLength = lengthMap.getOrDefault(num - 1, 0);
                int rightLength = lengthMap.getOrDefault(num + 1, 0);
                int length = leftLength + rightLength + 1;
                lengthMap.put(num, length);
                lengthMap.put(num - leftLength, length);
                lengthMap.put(num + rightLength, length);
                res = Math.max(res, length);
            }
        }

        System.out.println(lengthMap);
        return res;
    }

    public static void main(String[] args) {
       int[] nums = {2, 20, 4, 10, 3, 4, 5};
        System.out.println(longestConsecutiveV2(nums));
    }
}

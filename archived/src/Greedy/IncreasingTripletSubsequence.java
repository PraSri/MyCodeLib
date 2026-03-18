package Greedy;

public class IncreasingTripletSubsequence {
  
  // Best discussion : https://leetcode.com/problems/increasing-triplet-subsequence/discuss/79053/My-way-to-approach-such-a-problem.-How-to-think-about-it-Explanation-of-my-think-flow.


public boolean increasingTriplet(int[] nums) {
        int a = Integer.MAX_VALUE;
        int b = Integer.MAX_VALUE;
        
        for(int x : nums) {
            if(x<=a)
                a = x;
            else if(x<=b) 
                b = x;
            else 
                return true;
        }
        
        return false;
    }

}

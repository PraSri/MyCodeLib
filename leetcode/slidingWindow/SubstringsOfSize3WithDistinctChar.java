package leetcode.slidingWindow;

import java.util.HashSet;
import java.util.Set;

public class SubstringsOfSize3WithDistinctChar {
  
  
  //  Substrings of Size Three with Distinct Characters

    public static void main(String[] args) {
        System.out.println(countGoodSubstrings("aababcabc"));
    }
    public static int countGoodSubstrings(String s) {
        int n = s.length();
        int ans = 0;
        for(int i = 0;i<=n-3;i++) {
            String sub = s.substring(i, i+3);
            // System.out.println(" sub = " + sub);
            int c = 0 ;
            Set<Character> set = new HashSet();
            for(char ch : sub.toCharArray()) {
                if(set.add(ch)) {
                    c++;
                }
                // System.out.println(set + " - " + c);
            }
            if(c==3) {
                ans++;
            }
        }
        return ans;
    }
  
  // solution without set
  int countGoodSubstrings(string s,int r=0) {
        for(int i=1;i<s.size()-1;i++)
            if(s[i]!=s[i-1] && s[i]!=s[i+1] && s[i-1]!=s[i+1])r++;   
        return r;
    }
}

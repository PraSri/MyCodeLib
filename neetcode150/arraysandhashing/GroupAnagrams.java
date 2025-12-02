package arraysandhashing;

import java.util.*;
import java.util.List;

class GroupAnagrams {
    
   public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap();
        for(String s: strs) {
            int[] f = new int[26];
            for(char ch : s.toCharArray()) {
                f[ch - 'a']++;
            }
            String key = Arrays.toString(f);
            map.putIfAbsent(key, new ArrayList());
            map.get(key).add(s);
        }
        List<List<String>> result = new ArrayList();
        result.addAll(map.values());
        return result;
    }
}
    
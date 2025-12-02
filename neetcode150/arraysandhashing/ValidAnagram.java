package arraysandhashing;

class ValidAnagram {
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(1)   
    */
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) {
            return false;
        }
        int[] count = new int[26];
        for(int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }
        for(int x: count) {
            if(x != 0) {
                return false;
            }
        }
        return true;
    }
}

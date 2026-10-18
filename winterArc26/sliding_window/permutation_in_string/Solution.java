package sliding_window.permutation_in_string;

public class Solution {

    public boolean checkInclusion(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        if (n > m) {
            return false;
        }
        int[] diff = new int[26];
        for (int i = 0; i < n; i++) {
            diff[s1.charAt(i) - 'a']++;
            diff[s2.charAt(i) - 'a']--;
        }
        int matches = 0;
        for (int d : diff) {
            if (d == 0) {
                matches++;
            }
        }
        for (int r = n; r < m; r++) {
            if (matches == 26) {
                return true;
            }
            matches += update(diff, s2.charAt(r) - 'a', -1);
            matches += update(diff, s2.charAt(r - n) - 'a', +1);
        }
        return matches == 26;
    }

    private int update(int[] diff, int idx, int delta) {
        int before = diff[idx] == 0 ? 1 : 0;
        diff[idx] += delta;
        int after = diff[idx] == 0 ? 1 : 0;
        return after - before;
    }
}

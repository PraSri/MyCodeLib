import java.util.*;

public class ArraysAndHashing {

    // Valid sudoku
    public boolean isValidSudoku(char[][] board) {
        int n = board.length;
        int m = board[0].length;
        Set<String> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char ch = board[i][j];
                if (ch != '.') {
                    if (!set.add(ch + " add row - " + i) || !set.add(ch + " add col - " + j) || !set.add(ch + " add block - " + i / 3 + "_" + j / 3)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // Longest consecutive sequence
    // int[] nums = {2, 20, 4, 10, 3, 4, 5} ans = 4 => [2,3,4,5]
    public int longestConsecutiveSequence(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int num : nums) {
            if (!map.containsKey(num)) {
                int left = map.getOrDefault(num - 1, 0);
                int right = map.getOrDefault(num + 1, 0);
                int len = left + right + 1;
                map.put(num - 1, len);
                map.put(num + 1, len);
                res = Math.max(res, len);
            }
        }
        return res;
    }

    // valid palindrome
    //Input: s = "Was it a car or a cat I saw?"
    //
    //Output: true
    public boolean isValid(String s) {
        int n = s.length();
        int l = 0;
        int r = n - 1;
        s = s.toLowerCase();
        while (l < r) {
            while (l < r && !isAlpha(s.charAt(l))) l++;
            while (l < r && !isAlpha(s.charAt(r))) r--;
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

    private boolean isAlpha(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9');
    }

    // contains duplicate
    // input - nums = [1,2,3,3]
    // output - true

    public boolean hasDuplicates(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int x : nums) {
            if (set.contains(x)) {
                return true;
            }
            set.add(x);
        }
        return false;
    }

    // valid anagram
    public boolean isAnagram(String s, String t) {
        int n = s.length();
        int m = t.length();
        if (n != m) return false;
        int[] freq = new int[26];
        for (int i = 0; i < n; i++) {
            freq[s.charAt(i) - 'a']++;
            freq[t.charAt(i) - 'a']--;
        }
        for (int x : freq) {
            if (x != 0) {
                return false;
            }
        }
        return true;
    }

    //two sum
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    // group anagrams
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            int[] f = new int[26];
            for (char c : s.toCharArray()) {
                f[c - 'a']++;
            }
            String key = Arrays.toString(f);
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(s);
        }
        return new ArrayList<>(map.values());
    }

    // top K frequent elements
    public int[] topKFrequent(int[] nums, int k) {
        int n = nums.length;

        // each elements frequency
        Map<Integer, Integer> count = new HashMap<>();

        // reverse mapping, each frequency values bucket
        List<Integer>[] freq = new List[n + 1];

        for (int i = 0; i < n; i++) {
            count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);
        }

        for (int i = 0; i <= n; i++) {
            freq[i] = new ArrayList<>();
        }

        for (Map.Entry<Integer, Integer> me : count.entrySet()) {
            freq[me.getValue()].add(me.getKey());
        }

        int[] ans = new int[k];
        int j = 0;
        for (int i = freq.length - 1; i > 0; i--) {

            for (int f : freq[i]) {
                ans[j++] = f;
                if (j == k) {
                    break;
                }
            }

        }

        return ans;

    }

    // encode decode strings
    public String encode(List<String> strings) {
        StringBuilder res = new StringBuilder();
        for (String s : strings) {
            res.append(s.length()).append('#').append(s);
        }
        return res.toString();
    }

    public List<String> decode(String str) {
        List<String> res = new ArrayList<>();
        int i = 0;
        while (i < str.length()) {
            int j = i;
            while (str.charAt(j) != '#') {
                j++;
            }
            int length = Integer.parseInt(str.substring(i, j));
            i = j + 1;
            j = i + length;
            res.add(str.substring(i, j));
            i = j;
        }
        return res;
    }

    // products of array except self
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int[] pref = new int[n];
        int[] suff = new int[n];

        pref[0] = 1;
        suff[n - 1] = 1;

        for (int i = 1; i < n; i++) {
            pref[i] = pref[i - 1] * nums[i - 1];
        }

        for (int i = n - 2; i >= 0; i--) {
            suff[i] = suff[i + 1] * nums[i + 1];
        }

        for (int i = 0; i < n; i++) {
            res[i] = pref[i] * suff[i];
        }

        // Or O(1) space solution

        res[0] = 1;
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }

        int postFix = 1;

        for (int i = n - 1; i >= 0; i--) {
            res[i] = res[i] * postFix;
            postFix = postFix * nums[i];
        }

        return res;
    }

}

package agoda;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello, Agoda!!");
    }

    // https://leetcode.com/problems/longest-substring-without-repeating-characters
    public static class LongestSubstringWithoutRepeatingCharacters {

        public int lengthOfLongestSubstring(String s) {
            // last seen
            HashMap<Character, Integer> mp = new HashMap<>();
            int l = 0, res = 0;

            for (int r = 0; r < s.length(); r++) {
                // agar current char phele aa chuka hai toh dekho
                if (mp.containsKey(s.charAt(r))) {
                    // iski start kaha se hui thi
                    // jo bhi max index hai usse l se update kro
                    l = Math.max(mp.get(s.charAt(r)) + 1, l);
                }
                // har baar naya index update kr dete hai
                // taki last seen pata rhe
                mp.put(s.charAt(r), r);
                res = Math.max(res, r - l + 1);
            }
            return res;
        }


    }

    // https://leetcode.com/problems/3sum
    public static class ThreeSum {


        public List<List<Integer>> threeSum(int[] nums) {
            return threeSum(nums, 0);
        }

        public List<List<Integer>> threeSum(int[] nums, int target) {
            List<List<Integer>> res = new ArrayList<>();
            Arrays.sort(nums);
            int n = nums.length;
            for (int i = 0; i < n - 2; i++) {
                if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                    int l = i + 1, h = n - 1, sum = target - nums[i];
                    while (l < h) {
                        if (nums[l] + nums[h] == sum) {
                            res.add(Arrays.asList(nums[i], nums[l], nums[h]));
                            while (l < h && nums[l] == nums[l + 1]) l++;
                            while (l < h && nums[h] == nums[h - 1]) h--;
                            l++;
                            h--;
                        } else if (nums[l] + nums[h] < sum) l++;
                        else h--;
                    }
                }
            }

            return res;
        }


    }

    // https://leetcode.com/problems/valid-parentheses
    public static class ValidParentheses {

        public boolean isValid(String s) {

            Stack<Character> stack = new Stack<>();

            for (char ch : s.toCharArray()) {

                if (ch == '(') {
                    stack.push(')');
                } else if (ch == '[') {
                    stack.push(']');
                } else if (ch == '{') {
                    stack.push('}');
                } else {
                    if (stack.isEmpty()) {
                        return false;
                    } else {
                        char x = stack.pop();
                        if (x != ch) {
                            return false;
                        }
                    }
                }
            }
            return stack.isEmpty();
        }


    }

    // https://leetcode.com/problems/capacity-to-ship-packages-within-d-days
    public static class CapacityToShipPackagesWithinDDays {

        public int shipWithinDays(int[] weights, int days) {

            // define range of answer space
            int s = Arrays.stream(weights).max().getAsInt();
            int e = Arrays.stream(weights).sum();

            while (s < e) {
                int mid = s + (e - s) / 2;
                if (shipDays(mid, weights) <= days) {
                    // possible answer, but need to go for minimum value
                    e = mid;
                } else {
                    // +1 because mid was not possible as answer
                    s = mid + 1;
                }
            }

            // minimum value will be return
            return s;

        }

        private int shipDays(int shipCap, int[] weights) {

            int cap = 0;
            int days = 1;

            for (int w : weights) {
                if (w + cap > shipCap) {
                    days++;
                    cap = w;
                } else {
                    cap += w;
                }
            }

            return days;

        }

    }

    // https://leetcode.com/problems/brick-wall
    public static class BrickWall {

        public int leastBricks(List<List<Integer>> wall) {
            Map<Integer, Integer> map = new HashMap();

            int count = 0;
            for (List<Integer> row : wall) {
                int sum = 0;
                for (int i = 0; i < row.size() - 1; i++) {
                    sum += row.get(i);
                    map.put(sum, map.getOrDefault(sum, 0) + 1);
                    count = Math.max(count, map.get(sum));
                }
            }

            return wall.size() - count;
        }

    }

    // https://leetcode.com/problems/group-anagrams
    public static class GroupAnagrams {

        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String, List<String>> map = new HashMap<>();
            for (String s : strs) {
                int[] f = new int[26];
                for (char ch : s.toCharArray()) {
                    f[ch - 'a']++;
                }
                String key = Arrays.toString(f);
                map.putIfAbsent(key, new ArrayList<>());
                map.get(key).add(s);
            }
            return new ArrayList<>(map.values());
        }

    }

    // https://leetcode.com/problems/triangle
    public static class Triangle {
        //recurrence - dp[i] = triangle[row][i] + min(dp[i], dp[i+1])
        public int minimumTotal(List<List<Integer>> triangle) {

            int n = triangle.size();
            int[] min = new int[n + 1];
            for (int row = n - 1; row >= 0; row--) {
                for (int i = 0; i <= row; i++) {
                    min[i] = Math.min(min[i], min[i + 1]) + triangle.get(row).get(i);
                }
            }
            return min[0];
        }
    }

    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock
    public static class BestTimeToBuyAndSellStock {

        public int maxProfit(int[] prices) {

            int l = 0, r = l + 1;

            int maxP = 0;

            while (r < prices.length) {

                if (prices[l] < prices[r]) {

                    int profit = prices[r] - prices[l];
                    maxP = Math.max(maxP, profit);

                } else {

                    l = r;

                }

                r++;
            }
            return maxP;
        }
    }

    // https://leetcode.com/problems/house-robber
    public static class HouseRobber {

        public int rob(int[] nums) {
            int[] dp = new int[nums.length];
            Arrays.fill(dp, -1);
            return solve(nums, 0, dp);
        }

        private int solve(int[] nums, int i, int[] dp) {
            // base case
            if (i >= nums.length) {
                return 0;
            }

            // already computed
            if (dp[i] != -1) {
                return dp[i];
            }

            // choices
            int rob = nums[i] + solve(nums, i + 2, dp);
            int skip = solve(nums, i + 1, dp);

            dp[i] = Math.max(rob, skip);
            return dp[i];
        }


    }

    // https://leetcode.com/problems/find-the-child-who-has-the-ball-after-k-seconds
    public static class FindTheChildWhoHasTheBallAfterKSeconds {

        int numberOfChild(int n, int k) {
            // 0,1,2
            // t=0 0
            // t=1 1
            // t=2 2
            // t=3 1
            // t=4 0
            // N = 4
            // k = 1 x=1
            // k=2 x=2
            //k=3 x=3
            //k=4 x=0
            //k=5 x=1
            //k=6 x=2
            //k=7 x=3
            //k=8 x=0
            int N = 2 * n - 2;
            int x = k % N;
            return (x < n) ? x : N - x;
        }

        public int numberOfChild2(int n, int k) {
            n--; // Decrement n to simplify calculation (so range is now 0 to n-1)
            int rounds = k / n; // Calculate the number of complete back-and-forth trips
            int rem = k % n; // Calculate the remaining steps after the last complete trip

            if (rounds % 2 == 0) {
                // If the number of complete back-and-forth trips is even
                return rem; // The ball is passed forward from the start
            } else {
                // If the number of complete back-and-forth trips is odd
                return n - rem; // The ball is passed backward from the end
            }
        }

    }

    // https://leetcode.com/problems/minimum-window-substring
    public static class MinimumWindowSubstring {

        // s me aisi window dhudhno, joki minimum ho hai saare t ke chars aa jae
        public String minWindow(String s, String t) {

            // map with frequency of t
            // kyuki upper and lower case english alphabets hai sirf
            int[] f = new int[128];

            for (char c : t.toCharArray()) {
                f[c - 'A']++;
            }

            int start = 0;
            int e = 0;
            int minLen = Integer.MAX_VALUE;
            int minStart = 0;
            int c = t.length();
            int n = s.length();


            while (e < n) {

                char curr = s.charAt(e);

                // if curr char of s is present in t,
                // i.e, frequency is more than zero, decrease its counter...moving towards valid window
                if (f[curr - 'A'] > 0) {
                    c--;
                }

                // making non-present negative
                f[curr - 'A']--;

                e++;

                // condition if window is valid, record your results
                while (c == 0) {

                    // record stats
                    if (minLen > (e - start)) {
                        minLen = e - start;
                        minStart = start;
                    }

                    // window contraction phase ...to get improved results

                    char windowStart = s.charAt(start);
                    // restoring the frequency of t..because now we are moving to new window
                    f[windowStart - 'A']++;

                    if (f[windowStart - 'A'] > 0) {
                        c++;
                    }
                    start++;

                }

            }

            return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
        }

    }

    // https://leetcode.com/problems/can-place-flowers
    public static class CanPlaceFlowers {
        public boolean canPlaceFlowers(int[] flowerbed, int n) {
            for (int i = 0; i < flowerbed.length; i++) {
                if (flowerbed[i] == 0) {
                    if (i == 0 && i == flowerbed.length - 1) {
                        return true;
                    } else if (i == 0 && flowerbed[i + 1] == 0) {
                        flowerbed[i] = 1;
                        n--;
                    } else if (i == flowerbed.length - 1 && flowerbed[i - 1] == 0) {
                        flowerbed[i] = 1;
                        n--;
                    } else if (i > 0 && flowerbed[i - 1] == 0) {
                        if (i < flowerbed.length - 1 && flowerbed[i + 1] == 0) {
                            flowerbed[i] = 1;
                            n--;
                        }
                    }

                }
            }
            return n <= 0;
        }

        public boolean canPlaceFlowers2(int[] flowerbed, int n) {
            // If no flowers need to be planted, return true immediately
            if (n <= 0) return true;

            for (int i = 0; i < flowerbed.length && n > 0; i++) {
                // Skip if current spot already has a flower
                if (flowerbed[i] == 1) continue;

                // Check if left neighbor is empty or doesn't exist
                boolean leftEmpty = (i == 0) || (flowerbed[i - 1] == 0);
                // Check if right neighbor is empty or doesn't exist
                boolean rightEmpty = (i == flowerbed.length - 1) || (flowerbed[i + 1] == 0);

                // If both neighbors are empty, plant a flower here
                if (leftEmpty && rightEmpty) {
                    flowerbed[i] = 1;  // Plant the flower
                    n--;               // Decrement remaining flowers to plant
                }
            }

            // Return true if we planted all required flowers
            return n <= 0;
        }
    }

    // https://leetcode.com/problems/sort-array-by-increasing-frequency
    public static class SortArrayByIncreasingFrequency {

        // Given an array of integers nums, sort the array in
        // increasing order based on the frequency of the values.
        // If multiple values have the same frequency, sort them in decreasing order.
        //
        //Return the sorted array.
        public int[] frequencySort(int[] nums) {
            // Step 1: Count frequencies
            Map<Integer, Integer> freqMap = new HashMap<>();
            for (int num : nums) {
                freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
            }

            // Step 2: Convert array to list for easier sorting
            List<Integer> list = new ArrayList<>();
            for (int num : nums) {
                list.add(num);
            }

            // Step 3: Sort with custom comparator
            // Primary: Sort by frequency in increasing order
            // Secondary: If frequencies are equal, sort by value in decreasing order
            Collections.sort(list, new Comparator<Integer>() {
                @Override
                public int compare(Integer a, Integer b) {
                    int freqA = freqMap.get(a);
                    int freqB = freqMap.get(b);

                    if (freqA != freqB) {
                        return freqA - freqB;  // Increasing order of frequency
                    } else {
                        return b - a;  // Decreasing order of value
                    }
                }
            });

            // Step 4: Convert back to array
            int[] result = new int[nums.length];
            for (int i = 0; i < list.size(); i++) {
                result[i] = list.get(i);
            }

            return result;
        }

        public int[] frequencySort2(int[] nums) {
            // Step 1: Count frequencies
            Map<Integer, Integer> freqMap = new HashMap<>();
            for (int num : nums) {
                freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
            }

            // Step 2: Sort using custom comparator
            return Arrays.stream(nums)
                    .boxed()  // Convert int to Integer for sorting
                    .sorted((a, b) -> {
                        int freqA = freqMap.get(a);
                        int freqB = freqMap.get(b);
                        if (freqA != freqB) {
                            return freqA - freqB;  // Sort by frequency increasing
                        } else {
                            return b - a;  // Sort by value decreasing
                        }
                    })
                    .mapToInt(Integer::intValue)  // Convert back to int
                    .toArray();
        }

    }

    // https://leetcode.com/problems/move-zeroes
    public static class MoveZeroes {

    }

    // https://leetcode.com/problems/jump-game-ii
    public static class JumpGameII {

    }

    // https://leetcode.com/problems/lexicographically-smallest-string-after-substring-operation
    public static class LexicographicallySmallestStringAfterSubstringOperation {

    }

    // https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses
    public static class ReverseSubstringsBetweenEachPairOfParentheses {

    }

    // https://leetcode.com/problems/decode-string
    public static class DecodeString {

    }

    // https://leetcode.com/problems/unique-paths-ii
    public static class UniquePathsII {

    }

    // https://leetcode.com/problems/insert-delete-getrandom-o1
    public static class InsertDeleteGetRandomO1 {

    }

    // https://leetcode.com/problems/integer-to-roman
    public static class IntegerToRoman {

    }

    // https://leetcode.com/problems/next-greater-element-i
    public static class NextGreaterElementI {

    }

    // https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold
    public static class FindTheSmallestDivisorGivenAThreshold {

    }

    // https://leetcode.com/problems/minimum-absolute-difference
    public static class MinimumAbsoluteDifference {

    }

    // https://leetcode.com/problems/squares-of-a-sorted-array
    public static class SquaresOfASortedArray {

    }

    // https://leetcode.com/problems/daily-temperatures
    public static class DailyTemperatures {

    }

    // https://leetcode.com/problems/merge-sorted-array
    public static class MergeSortedArray {

    }

    // https://leetcode.com/problems/two-sum
    public static class TwoSum {

    }

    // https://leetcode.com/problems/subarray-product-less-than-k
    public static class SubarrayProductLessThanK {

    }

    // https://leetcode.com/problems/backspace-string-compare
    public static class BackspaceStringCompare {

    }

    // https://leetcode.com/problems/find-pivot-index
    public static class FindPivotIndex {

    }

    // https://leetcode.com/problems/eliminate-maximum-number-of-monsters
    public static class EliminateMaximumNumberOfMonsters {

    }

    // https://leetcode.com/problems/permutations
    public static class Permutations {

    }

    // https://leetcode.com/problems/longest-increasing-subsequence
    public static class LongestIncreasingSubsequence {

    }

    // https://leetcode.com/problems/number-of-atoms
    public static class NumberOfAtoms {

        //Yaad rakhne ka mantra
        //
        //'(' ? push map, new scope
        //
        //')' ? pop map, multiply & merge
        //
        //Atom ? capital + lowercase + number
        //
        //End me sort

        public String countOfAtoms(String formula) {
            Stack<Map<String, Integer>> stack = new Stack<>();
            Map<String, Integer> map = new HashMap<>();
            int i = 0, n = formula.length();
            while (i < n) {
                char c = formula.charAt(i);
                i++;
                if (c == '(') {
                    stack.push(map);
                    map = new HashMap<>();
                } else if (c == ')') {
                    int val = 0;
                    while (i < n && Character.isDigit(formula.charAt(i)))
                        val = val * 10 + formula.charAt(i++) - '0';

                    if (val == 0) val = 1;
                    if (!stack.isEmpty()) {
                        Map<String, Integer> temp = map;
                        map = stack.pop();
                        for (String key : temp.keySet())
                            map.put(key, map.getOrDefault(key, 0) + temp.get(key) * val);
                    }
                } else {
                    int start = i - 1;
                    while (i < n && Character.isLowerCase(formula.charAt(i))) {
                        i++;
                    }
                    String s = formula.substring(start, i);
                    int val = 0;
                    while (i < n && Character.isDigit(formula.charAt(i))) val = val * 10 + formula.charAt(i++) - '0';
                    if (val == 0) val = 1;
                    map.put(s, map.getOrDefault(s, 0) + val);
                }
            }
            StringBuilder sb = new StringBuilder();
            List<String> list = new ArrayList<>(map.keySet());
            Collections.sort(list);
            for (String key : list) {
                sb.append(key);
                if (map.get(key) > 1) sb.append(map.get(key));
            }
            return sb.toString();
        }


    }

    // https://leetcode.com/problems/fizz-buzz
    public static class FizzBuzz {

    }

    // https://leetcode.com/problems/longest-string-chain
    public static class LongestStringChain {

    }

    // https://leetcode.com/problems/reorganize-string
    public static class ReorganizeString {

    }

    // https://leetcode.com/problems/climbing-stairs
    public static class ClimbingStairs {

    }

    // https://leetcode.com/problems/sliding-window-maximum
    public static class SlidingWindowMaximum {

    }

    // https://leetcode.com/problems/happy-number
    public static class HappyNumber {

    }

}

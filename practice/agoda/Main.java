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
        public void moveZeroes(int[] nums) {
            int j = 0;
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                if (nums[i] != 0) {
                    nums[j++] = nums[i];
                }
            }
            while (j < n) {
                nums[j++] = 0;
            }
        }
    }

    // https://leetcode.com/problems/jump-game-ii
    public static class JumpGameII {

        public boolean canJump(int[] nums) {
            int n = nums.length;
            int targetIndex = n - 1;

            for (int i = n - 2; i >= 0; i--) {
                if (targetIndex <= (i + nums[i])) {
                    targetIndex = i;
                }
            }

            return targetIndex == 0;
        }

        public int jump(int[] nums) {

            int farthestJump = 0;
            int curJump = 0;
            int jumps = 0;
            for (int i = 0; i < nums.length - 1; i++) {
                farthestJump = Math.max(farthestJump, i + nums[i]);
                if (i == curJump) {
                    jumps++;
                    curJump = farthestJump;
                }
            }
            return jumps;
        }

    }

    // https://leetcode.com/problems/lexicographically-smallest-string-after-substring-operation
    public static class LexicographicallySmallestStringAfterSubstringOperation {

        public String smallestString(String s) {
            int i = 0;
            int n = s.length();
            char[] A = s.toCharArray();
            while (i < n && A[i] == 'a')
                i++;
            if (i == n)
                A[n - 1] = 'z';
            while (i < n && A[i] != 'a')
                --A[i++];
            return String.valueOf(A);
        }

    }

    // https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses
    public static class ReverseSubstringsBetweenEachPairOfParentheses {
        public String reverseParentheses(String s) {
            int n = s.length();
            int[] index_pairs = new int[n];
            Deque<Integer> stack_start_ind = new LinkedList<>();

            for (int char_ind = 0; char_ind < n; ++char_ind) {
                char char_s = s.charAt(char_ind);
                if (char_s == '(') {
                    stack_start_ind.push(char_ind);
                } else if (char_s == ')') {
                    int start_ind = stack_start_ind.pop();
                    index_pairs[char_ind] = start_ind;
                    index_pairs[start_ind] = char_ind;
                }
            }

            StringBuilder res = new StringBuilder();
            int cur_ind = 0;
            int cur_dir = 1;

            while (cur_ind < n) {
                char char_s = s.charAt(cur_ind);
                if (char_s == '(' || char_s == ')') {
                    cur_ind = index_pairs[cur_ind];
                    cur_dir *= -1;
                } else {
                    res.append(char_s);
                }
                cur_ind += cur_dir;
            }

            return res.toString();
        }

    }

    // https://leetcode.com/problems/decode-string
    public static class DecodeString {

        public String decodeString(String s) {
            StringBuilder res = new StringBuilder();

            Stack<Integer> countStack = new Stack<>();
            Stack<StringBuilder> strStack = new Stack<>();

            int count = 0;

            for (char ch : s.toCharArray()) {

                // when you get digit, save in count variable

                if (Character.isDigit(ch)) {
                    count = count * 10 + (ch - '0');
                }

                // for open bracket, push in stack, initialize variables
                else if (ch == '[') {
                    countStack.push(count);
                    count = 0;
                    strStack.push(res);
                    res = new StringBuilder();
                }

                // for closed bracket, pop from stacks & build final string
                else if (ch == ']') {
                    StringBuilder tmp = res;
                    res = strStack.pop();
                    int freq = countStack.pop();
                    res.append(String.valueOf(tmp).repeat(Math.max(0, freq)));
                } else {
                    res.append(ch);
                }
            }

            return res.toString();
        }


    }

    // https://leetcode.com/problems/unique-paths-ii
    public static class UniquePathsII {
        int[][] dp;

        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            int m = obstacleGrid.length;
            int n = obstacleGrid[0].length;
            dp = new int[m + 1][n + 1];
            for (int[] a : dp) {
                Arrays.fill(a, -1);
            }
            return path(obstacleGrid, 0, 0, m, n);
        }

        int path(int[][] grid, int i, int j, int m, int n) {
            if (i < 0 || j < 0 || i >= m || j >= n)
                return 0;
            if (grid[i][j] == 1)
                return 0;
            if (i == m - 1 && j == n - 1)
                return 1;
            if (dp[i][j] != -1) {
                return dp[i][j];
            }
            return dp[i][j] = path(grid, i + 1, j, m, n) + path(grid, i, j + 1, m, n);
        }

    }

    // https://leetcode.com/problems/insert-delete-getrandom-o1
    public static class InsertDeleteGetRandomO1 {

        ArrayList<Integer> nums;
        HashMap<Integer, Integer> locs;
        java.util.Random rand = new java.util.Random();

        /**
         * Initialize your data structure here.
         */
        public InsertDeleteGetRandomO1() {
            nums = new ArrayList<Integer>();
            locs = new HashMap<Integer, Integer>();
        }

        /**
         * Inserts a value to the set. Returns true if the set did not already contain the specified element.
         */
        public boolean insert(int val) {
            boolean contain = locs.containsKey(val);
            if (contain) return false;
            locs.put(val, nums.size());
            nums.add(val);
            return true;
        }

        /**
         * Removes a value from the set. Returns true if the set contained the specified element.
         */
        public boolean remove(int val) {
            boolean contain = locs.containsKey(val);
            if (!contain) return false;
            int loc = locs.get(val);
            if (loc < nums.size() - 1) { // not the last one than swap the last one with this val
                int lastone = nums.get(nums.size() - 1);
                nums.set(loc, lastone);
                locs.put(lastone, loc);
            }
            locs.remove(val);
            nums.remove(nums.size() - 1);
            return true;
        }

        /**
         * Get a random element from the set.
         */
        public int getRandom() {
            return nums.get(rand.nextInt(nums.size()));
        }


    }

    // https://leetcode.com/problems/integer-to-roman
    public static class IntegerToRoman {
        public String intToRoman(int num) {

            int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
            String[] strs = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < values.length; i++) {
                while (num >= values[i]) {
                    num -= values[i];
                    sb.append(strs[i]);
                }
            }
            return sb.toString();
        }

    }

    // https://leetcode.com/problems/next-greater-element-i
    public static class NextGreaterElementI {
        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            Stack<Integer> stack = new Stack<>();
            Map<Integer, Integer> map = new HashMap<>();

            // iterate over nums2
            for (int current : nums2) {
                // while stack is not empty and current element is greater than the top element of the stack
                while (!stack.empty() && current > stack.peek()) {
                    // update the map with the current element as the value for the popped element
                    map.put(stack.pop(), current);
                }
                // push the current element to the stack
                stack.push(current);
            }

            // iterate over remaining elements in the stack, pop them and set their values to -1 in the map
            while (!stack.empty()) {
                map.put(stack.pop(), -1);
            }

            int[] ans = new int[nums1.length];
            // iterate over nums1 and add the corresponding value from the map to ans
            for (int i = 0; i < nums1.length; i++) {
                ans[i] = map.get(nums1[i]);
            }

            return ans;
        }
    }

    // https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold
    public static class FindTheSmallestDivisorGivenAThreshold {
        public int smallestDivisor(int[] nums, int threshold) {

            int s = 1;
            int e = Arrays.stream(nums).max().getAsInt();

            while (s < e) {
                int mid = s + (e - s) / 2;
                if (isPossible(mid, nums, threshold)) {
                    e = mid;
                } else {
                    s = mid + 1;
                }
            }

            return s;

        }

        private boolean isPossible(int mid, int[] nums, int threshold) {

            int sum = 0;

            for (int num : nums) {
                sum += Math.ceil((double) (num) / (double) (mid));
            }

            return sum <= threshold;

        }
    }

    // https://leetcode.com/problems/minimum-absolute-difference
    public static class MinimumAbsoluteDifference {
        public List<List<Integer>> minimumAbsDifference(int[] arr) {
            List<List<Integer>> res = new ArrayList();
            //sort elements
            Arrays.sort(arr);
            //init our min difference value
            int min = Integer.MAX_VALUE;
            //start looping over array to find real min element. Each time we found smaller difference
            //we reset resulting list and start building it from scratch. If we found pair with the same
            //difference as min - add it to the resulting list
            for (int i = 0; i < arr.length - 1; i++) {
                int diff = arr[i + 1] - arr[i];
                if (diff < min) {
                    min = diff;
                    res.clear();
                    res.add(Arrays.asList(arr[i], arr[i + 1]));
                } else if (diff == min) {
                    res.add(Arrays.asList(arr[i], arr[i + 1]));
                }
            }
            return res;
        }
    }

    // https://leetcode.com/problems/squares-of-a-sorted-array
    public static class SquaresOfASortedArray {
        public int[] sortedSquares(int[] nums) {

            int n = nums.length;
            int[] res = new int[n];

            int i = 0, j = n - 1, k = n - 1;

            while (k >= 0) {

                if (Math.abs(nums[i]) > Math.abs(nums[j])) {
                    res[k] = nums[i] * nums[i];
                    i++;
                } else {
                    res[k] = nums[j] * nums[j];
                    j--;
                }

                k--;

            }

            return res;

        }
    }

    // https://leetcode.com/problems/daily-temperatures
    public static class DailyTemperatures {

        public int[] dailyTemperatures(int[] temperatures) {
            int n = temperatures.length; // Get the number of days

            int[] output = new int[n]; // Initialize the output array with all elements set to 0

            Stack<Integer> stack = new Stack<>(); // Initialize an empty stack to hold indices of temperatures

            for (int i = 0; i < n; i++) { // Loop through each day
                // While the stack is not empty and the current temperature is higher than the temperature
                // at the index stored at the top of the stack
                while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                    int j = stack.pop(); // Pop the top index from the stack
                    output[j] = i - j; // Calculate the number of days until a warmer temperature and store it in the output array
                }
                stack.push(i); // Push the current index onto the stack
            }

            return output; // Return the output array after processing all temperatures
        }
    }

    // https://leetcode.com/problems/merge-sorted-array
    public static class MergeSortedArray {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int p1 = m - 1;
            int p2 = n - 1;

            for (int p = m + n - 1; p >= 0; p--) {
                if (p2 < 0) {
                    break;
                }
                if (p1 >= 0 && nums1[p1] > nums2[p2]) {
                    nums1[p] = nums1[p1];
                    p1 -= 1;
                } else {
                    nums1[p] = nums2[p2];
                    p2 -= 1;
                }
            }
        }
    }

    // https://leetcode.com/problems/two-sum
    public static class TwoSum {
        public int[] twoSum(int[] nums, int target) {

            int[] ans = new int[2];

            int n = nums.length;
            Map<Integer, Integer> map = new HashMap<>();

            for (int i = 0; i < n; i++) {

                if (map.containsKey(target - nums[i])) {
                    ans[0] = map.get(target - nums[i]);
                    ans[1] = i;
                    break;
                }
                map.put(nums[i], i);

            }

            return ans;
        }
    }

    // https://leetcode.com/problems/subarray-product-less-than-k
    public static class SubarrayProductLessThanK {
        //Input: nums = [10,5,2,6], k = 100
        //Output: 8
        public int numSubarrayProductLessThanK(int[] nums, int k) {

            if (k <= 1)
                return 0;

            int prod = 1, ans = 0, left = 0, right = 0, n = nums.length;

            while (right < n) {

                prod = prod * nums[right]; // 10 -> 50 -> 100 -> 600

                while (prod >= k) {
                    prod = prod / nums[left]; // 60,
                    left++; // 1
                }

                ans = ans + (right - left + 1); // 1 -> 3 -> 6 ->

                right++; // 1 2 3

            }

            return ans;

        }
    }

    // https://leetcode.com/problems/backspace-string-compare
    public static class BackspaceStringCompare {
        //Input: s = "ab#c", t = "ad#c"
        //Output: true
        //Explanation: Both s and t become "ac".
        public boolean backspaceCompare(String s, String t) {

            int n = s.length() - 1;
            int m = t.length() - 1;

            // backspace count for s & t
            int bs = 0, bt = 0;

            int i = n, j = m;

            while (i >= 0 || j >= 0) {

                while (i >= 0) {
                    if (s.charAt(i) == '#') {
                        bs++;
                        i--;
                    } else if (bs > 0) {
                        bs--;
                        i--;
                    } else {
                        break;
                    }
                }

                while (j >= 0) {

                    if (t.charAt(j) == '#') {
                        bt++;
                        j--;
                    } else if (bt > 0) {
                        bt--;
                        j--;
                    } else {
                        break;
                    }

                }

                // after backspaces removed, the leftover string should be same, so to check at i,j have same chars

                if (i >= 0 && j >= 0 && s.charAt(i) != t.charAt(j)) {
                    return false;
                }

                // if anyone string is exhausted

                if ((i >= 0) != (j >= 0)) {
                    return false;
                }

                i--;
                j--;

            }

            return true;

        }
    }

    // https://leetcode.com/problems/find-pivot-index
    public static class FindPivotIndex {
        public int pivotIndex(int[] nums) {
            int n = nums.length;
            int sum = 0;
            for (int i = 0; i < n; i++) {
                sum += nums[i];
            }
            int ls = 0, rs = sum;
            for (int i = 0; i < n; i++) {
                rs = rs - nums[i];
                if (ls == rs)
                    return i;
                ls += nums[i];
            }
            return -1;
        }
    }

    // https://leetcode.com/problems/eliminate-maximum-number-of-monsters
    public static class EliminateMaximumNumberOfMonsters {

        public int eliminateMaximum(int[] dist, int[] speed) {
            PriorityQueue<Double> heap = new PriorityQueue<>();
            for (int i = 0; i < dist.length; i++) {
                heap.add((double) dist[i] / speed[i]);
            }

            int ans = 0;
            while (!heap.isEmpty()) {
                if (heap.remove() <= ans) {
                    break;
                }

                ans++;
            }

            return ans;
        }

    }

    // https://leetcode.com/problems/permutations
    public static class Permutations {

        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            boolean[] pick = new boolean[nums.length];
            backtrack(nums, res, pick, new ArrayList<>());
            return res;
        }

        private void backtrack(int[] nums, List<List<Integer>> res,
                               boolean[] pick, List<Integer> temp) {
            // base case
            if (temp.size() >= nums.length) {
                res.add(new ArrayList<>(temp));
                return;
            }
            for (int i = 0; i < nums.length; i++) {
                if (!pick[i]) {
                    temp.add(nums[i]);
                    pick[i] = true;
                    backtrack(nums, res, pick, temp);
                    temp.remove(temp.size() - 1);
                    pick[i] = false;
                }
            }
        }

    }

    // https://leetcode.com/problems/longest-increasing-subsequence
    public static class LongestIncreasingSubsequence {

        public int lengthOfLIS(int[] nums) {
            boolean useBinarySearch = true;
            if (useBinarySearch) {
                return lisBinarySearch(nums);
            }
            int n = nums.length;
            int[][] dp = new int[n][n + 1];
            Arrays.stream(dp).forEach(i -> Arrays.fill(i, -1));
            int curr = 0;
            int prev = -1;
            return len(nums, curr, prev, dp);
        }

        private int lisBinarySearch(int[] a) {
            int n = a.length;
            List<Integer> temp = new ArrayList<>();
            temp.add(a[0]);
            int len = 1;
            for (int i = 1; i < n; i++) {
                if (a[i] > temp.get(temp.size() - 1)) {
                    temp.add(a[i]);
                    len++;
                } else {
                    int ind = Collections.binarySearch(temp, a[i]);
                    if (ind < 0) {
                        ind = -ind - 1;
                    }
                    temp.set(ind, a[i]);
                }
            }
            return len;
        }

        private int len(int[] nums, int curr, int prev, int[][] dp) {

            // base case
            if (curr == nums.length) {
                return 0;
            }

            if (dp[curr][prev + 1] != -1) {
                return dp[curr][prev + 1];
            }

            // not pick
            int ex = len(nums, curr + 1, prev, dp);
            int in = Integer.MIN_VALUE;
            // pick
            if (prev == -1 || nums[curr] > nums[prev]) {
                in = 1 + len(nums, curr + 1, curr, dp);
            }

            return dp[curr][prev + 1] = Math.max(ex, in);

        }

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

        public List<String> fizzBuzz(int n) {
            List<String> ret = new ArrayList<>(n);
            for (int i = 1, fizz = 0, buzz = 0; i <= n; i++) {
                fizz++;
                buzz++;
                if (fizz == 3 && buzz == 5) {
                    ret.add("FizzBuzz");
                    fizz = 0;
                    buzz = 0;
                } else if (fizz == 3) {
                    ret.add("Fizz");
                    fizz = 0;
                } else if (buzz == 5) {
                    ret.add("Buzz");
                    buzz = 0;
                } else {
                    ret.add(String.valueOf(i));
                }
            }
            return ret;
        }

    }

    // https://leetcode.com/problems/longest-string-chain
    public static class LongestStringChain {
        public int longestStrChain(String[] words) {

            if (words == null || words.length == 0) return 0;
            int res = 0;
            Arrays.sort(words, (a, b) -> a.length() - b.length());  // Sort the words based on their lengths
            HashMap<String, Integer> map = new HashMap();       //Stores each word and length of its max chain.

            for (String w : words) {                             //From shortest word to longest word
                map.put(w, 1);                                  //Each word is atleast 1 chain long
                for (int i = 0; i < w.length(); i++) {               //Form next word removing 1 char each time for each w
                    StringBuilder sb = new StringBuilder(w);
                    String next = sb.deleteCharAt(i).toString();
                    if (map.containsKey(next) && map.get(next) + 1 > map.get(w))
                        map.put(w, map.get(next) + 1);            //If the new chain is longer, update the map
                }
                res = Math.max(res, map.get(w));                //Store max length of all chains
            }
            return res;
        }
    }

    // https://leetcode.com/problems/reorganize-string
    public static class ReorganizeString {

        public String reorganizeString(String str) {

            Map<Character, Integer> charCounter = new HashMap<>();

            for (char c : str.toCharArray()) {
                int freq = charCounter.getOrDefault(c, 0) + 1;
                charCounter.put(c, freq);
            }

            PriorityQueue<Map.Entry<Character, Integer>> maxFreqChar = new PriorityQueue<>(
                    (item1, item2) -> item2.getValue() - item1.getValue());

            maxFreqChar.addAll(charCounter.entrySet());

            Map.Entry<Character, Integer> previous = null;
            StringBuilder result = new StringBuilder(str.length());
            while (!maxFreqChar.isEmpty() || previous != null) {

                if (previous != null && maxFreqChar.isEmpty())
                    return "";

                Map.Entry<Character, Integer> currentEntry = maxFreqChar.poll();
                int count = currentEntry.getValue() - 1;
                result.append(currentEntry.getKey());

                if (previous != null) {
                    maxFreqChar.add(previous);
                    previous = null;
                }

                if (count != 0) {
                    previous = new AbstractMap.SimpleEntry<>(currentEntry.getKey(), count);

                }
            }

            return result.toString();
        }

    }

    // https://leetcode.com/problems/climbing-stairs
    public static class ClimbingStairs {

        public int climbStairs(int n) {
            int[] dp = new int[n + 1];

            // initialize with -1
            for (int i = 0; i <= n; i++) {
                dp[i] = -1;
            }

            return solve(n, dp);
        }

        private int solve(int n, int[] dp) {
            if (n == 0) return 1;
            if (n < 0) return 0;

            if (dp[n] != -1) {
                return dp[n];
            }

            dp[n] = solve(n - 1, dp) + solve(n - 2, dp);
            return dp[n];
        }


    }

    // https://leetcode.com/problems/sliding-window-maximum
    public static class SlidingWindowMaximum {

        // Humein har sliding window ka maximum chahiye.
        public int[] maxSlidingWindow(int[] nums, int k) {
            int n = nums.length;
            int[] output = new int[n - k + 1];
//        Hum ek deque (double-ended queue) use karte hain jisme sirf potential maximum candidates rakhte hain.
//        Key idea:
//
//Deque mein sirf indices rakhe jaate hain, values nahi.
//
//Deque hamesha values ke decreasing order mein hota hai.
//
//Deque ka front current window ka maximum hota hai.

            //Agar new element aaya aur woh kisi purane element se bada hai,
            //toh woh purana element kabhi window ka maximum nahi ban sakta future mein.
            // toh usko deque se nikaal do.
            Deque<Integer> q = new LinkedList<>();
            int l = 0, r = 0;

            while (r < n) {
                while (!q.isEmpty() && nums[q.getLast()] < nums[r]) {
                    q.removeLast();
                }
                q.addLast(r);

                if (l > q.getFirst()) {
                    q.removeFirst();
                }

                if ((r + 1) >= k) {
                    output[l] = nums[q.getFirst()];
                    l++;
                }
                r++;
            }

            return output;
        }
    }

    // https://leetcode.com/problems/happy-number
    public static class HappyNumber {

        public boolean isHappy(int n) {

            int slow = n;
            int fast = n;
            //while loop is not used here because initially slow and
            //fast pointer will be equal only, so the loop won't run.
            do {
                //slow moving one step ahead and fast moving two steps ahead

                slow = square(slow);
                fast = square(square(fast));
            } while (slow != fast);

            //if a cycle exists, then the number is not a happy number
            //and slow will have a value other than 1

            return slow == 1;
        }

        //Finding the square of the digits of a number

        public int square(int num) {

            int ans = 0;

            while (num > 0) {
                int remainder = num % 10;
                ans += remainder * remainder;
                num /= 10;
            }

            return ans;
        }

    }


    public static class HitCounter {
        // List to store all hit timestamps in chronological order
        private final List<Integer> timestamps = new ArrayList<>();

        /**
         * Initialize the hit counter system
         */
        public HitCounter() {
        }

        /**
         * Record a hit at the given timestamp
         *
         * @param timestamp - the current timestamp (in seconds granularity)
         */
        public void hit(int timestamp) {
            timestamps.add(timestamp);
        }

        /**
         * Return the number of hits in the past 5 minutes (300 seconds)
         *
         * @param timestamp - the current timestamp (in seconds granularity)
         * @return number of hits in the past 300 seconds from the given timestamp
         */
        public int getHits(int timestamp) {
            // Find the index of the first timestamp that is within the 300-second window
            // We search for the leftmost position where timestamp >= (currentTime - 299)
            int leftBoundaryIndex = binarySearchLeftmost(timestamp - 300 + 1);

            // All elements from leftBoundaryIndex to the end are within the 300-second window
            return timestamps.size() - leftBoundaryIndex;
        }

        /**
         * Binary search to find the leftmost index where timestamp >= target
         *
         * @param target - the target timestamp to search for
         * @return the index of the first element >= target, or list size if all elements < target
         */
        private int binarySearchLeftmost(int target) {
            int left = 0;
            int right = timestamps.size();

            // Binary search for the leftmost position where timestamp >= target
            while (left < right) {
                int mid = (left + right) >> 1;  // Equivalent to (left + right) / 2

                if (timestamps.get(mid) >= target) {
                    // Mid element is >= target, search in left half (including mid)
                    right = mid;
                } else {
                    // Mid element is < target, search in right half (excluding mid)
                    left = mid + 1;
                }
            }

            return left;
        }

        /**
         * Your HitCounter object will be instantiated and called as such:
         * HitCounter obj = new HitCounter();
         * obj.hit(timestamp);
         * int param_2 = obj.getHits(timestamp);
         */
    }

    public static class CountNumberOfPairsWithAbsoluteDifferenceK {
        //Given an integer array nums and an integer k,
        // return the number of pairs (i, j) where i < j
        // such that |nums[i] - nums[j]| == k.

        public int countKDifference(int[] nums, int k) {
            Map<Integer, Integer> map = new HashMap<>();
            int res = 0;

            for (int i = 0; i < nums.length; i++) {
                if (map.containsKey(nums[i] - k)) {
                    res += map.get(nums[i] - k);
                }
                if (map.containsKey(nums[i] + k)) {
                    res += map.get(nums[i] + k);
                }
                map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            }


            return res;
        }
    }

    public static class PalindromicSubstrings {
        int count = 0;

        public int countSubstrings(String s) {
            int n = s.length();
            for (int i = 0; i < n; i++) {
                extend(s, i, i);
                extend(s, i, i + 1);
            }
            return count;
        }

        private void extend(String s, int left, int right) {
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
                count++;
            }
        }
    }

    public static class KokoEatingBananas {

        // Input: piles = [1,4,3,2], h = 9
        //
        //Output: 2

        //Explanation: With an eating rate of 2, you can eat the bananas in 6 hours.
        // With an eating rate of 1, you would need 10 hours to eat all the bananas (which exceeds h=9),
        // thus the minimum eating rate is 2.

        // tumhe aisa number dhoona hai jisme tum h hours ke andar bananas kahatam kr pao, aur har hour tum k bananas hi
        // kha skte ho. wo k minimum hona chahiye

        public int minEatingSpeed(int[] piles, int h) {
            int s = 1; // kam se kam tumhe ek banana toh khana hi hoga khatam krne ko

            int e = Arrays.stream(piles).max().getAsInt(); // maximum tum har baar sabse jada wala pile ke barabar agr khaoge
            // toh sabse jaldi khatam hoga

            // hume iske beech me ek value dhundni hai, jo h se choti ho aur minimum ho

            // hum binary search krege in the range [1, max(piles[])]
            // ek example lete hai
            // piles = [1,4,3,2], h = 9
            // range = [1, 4]

            while (s < e) {

                int mid = s + (e - s) / 2;

                // maine range [1,4] me mid value nikali
                // wo hai 2
                // abb main dekhuga ki 2, kya possible answer jo ye satisfy kre
                // ki main har ghante 2 banana khao aur saare banana 9 ghante me khatam ho jae
                // piles = [ 1 -> 1h, 4 -> 2h, 3 -> 2h, 2 -> 1h] total hours = 6h
                // toh ye ek answer ho skta hai
                // but ye minimum value hai ki nahi, iske liye hume binary search continue krna padega
                if (isPossible(piles, mid, h)) {
                    e = mid; // e = 2
                } else {
                    s = mid + 1;
                }
            }

            return s;
        }

        private boolean isPossible(int[] piles, int bananasAllowedPerHour, int expectedHours) {
            int actualHours = 0;
            int i = 0;
            int n = piles.length;
            while (i < n) {
                // time to eat ith pile
                int timeTakenPerPile = piles[i] / bananasAllowedPerHour;
                actualHours += timeTakenPerPile;
                if (piles[i] % bananasAllowedPerHour != 0) {
                    actualHours++;
                }
                i++;
            }
            return actualHours <= expectedHours;
        }
    }

    static class NextPermutation {
        public void nextPermutation(int[] a) {

            // reverse se aisa number dhoono ki aane waala number usse chota ho

            int n = a.length; // ex : [2,3,1]

            int i = n - 1; // i = 2

            while (i > 0 && a[i - 1] >= a[i]) {
                i--; // i = 1
            }

            i--; // i = 0 a[0] = 2

            // agr waisa nhi milta toh wo last permutation hai aur wo tum sort kodro in ascending order

            // agr milta hai, iska matlab i>=0 hai

            if (i >= 0) {

                // abb firse reverse se aisa number serach kro jo just bada ho tumhare upar searched number se i.e just greater than a[i]

                int j = n - 1;

                while (j >= 0 && a[j] <= a[i]) {
                    j--;
                }

                // agr aisa number milta hai toh usko swap krdo ith number se

                if (j >= 0) {
                    swap(a, i, j);
                }
            }

            // abb baaki jo i+1 se end tak numbers unko reverse krdo, actually me ye lowest sorting krni thi but
            // reverse bhi same kaam krega, but kyu??  yha pe reverse krne ke baad muhje lowest sorting hi milege
            // above ex: [2,3,1] ki baat kre toh wo swap step ke baad aisa hoga [3,2,1]
            // abb baccha 2,1 reverse krege toh 1,2 milega aur final answer [3,1,2] hoga
            // iska genarailize solution ye keh skte hai ki jo
            // 1st while loop hai i wali usme hum ye ensure kr rhe hai ki left se elements descending order (matlab bade se chota)
            // me ho aur jaise hi ye order break hota hai, hum loop exit kr dete hai,

            reverse(a, i);
        }

        public void swap(int[] a, int i, int j) {

            int t = a[i];
            a[i] = a[j];
            a[j] = t;

        }

        public void reverse(int[] a, int i) {
            int s = i + 1;
            int e = a.length - 1;
            while (s < e) {
                if (a[s] > a[e]) {
                    swap(a, s, e);
                }
                s++;
                e--;
            }
        }

    }
}


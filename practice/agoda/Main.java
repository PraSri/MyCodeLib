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

    }

    // https://leetcode.com/problems/triangle
    public static class Triangle {

    }

    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock
    public static class BestTimeToBuyAndSellStock {

    }

    // https://leetcode.com/problems/house-robber
    public static class HouseRobber {

    }

    // https://leetcode.com/problems/find-the-child-who-has-the-ball-after-k-seconds
    public static class FindTheChildWhoHasTheBallAfterKSeconds {

    }

    // https://leetcode.com/problems/minimum-window-substring
    public static class MinimumWindowSubstring {

    }

    // https://leetcode.com/problems/can-place-flowers
    public static class CanPlaceFlowers {

    }

    // https://leetcode.com/problems/sort-array-by-increasing-frequency
    public static class SortArrayByIncreasingFrequency {

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

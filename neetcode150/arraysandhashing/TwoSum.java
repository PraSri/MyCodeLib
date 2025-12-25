package arraysandhashing;

import java.util.*;

/**
 *
 * Similar Questions (Two Sum)
 * <p>
 * 3Sum
 * <a href="https://leetcode.com/problems/3sum/">...</a>
 * <p>
 * 4Sum
 * <a href="https://leetcode.com/problems/4sum/">...</a>
 * <p>
 * Two Sum II – Input Array Is Sorted
 * <a href="https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/">...</a>
 * <p>
 * Two Sum III – Data Structure Design
 * <a href="https://leetcode.com/problems/two-sum-iii-data-structure-design/">...</a>
 * <p>
 * Subarray Sum Equals K
 * <a href="https://leetcode.com/problems/subarray-sum-equals-k/">...</a>
 * <p>
 * Two Sum IV – Input is a BST
 * <a href="https://leetcode.com/problems/two-sum-iv-input-is-a-bst/">...</a>
 * <p>
 * Two Sum Less Than K
 * <a href="https://leetcode.com/problems/two-sum-less-than-k/">...</a>
 * <p>
 * Max Number of K-Sum Pairs
 * <a href="https://leetcode.com/problems/max-number-of-k-sum-pairs/">...</a>
 * <p>
 * Count Good Meals
 * <a href="https://leetcode.com/problems/count-good-meals/">...</a>
 * <p>
 * Count Number of Pairs With Absolute Difference K
 * <a href="https://leetcode.com/problems/count-number-of-pairs-with-absolute-difference-k/">...</a>
 * <p>
 * Number of Pairs of Strings With Concatenation Equal to Target
 * <a href="https://leetcode.com/problems/number-of-pairs-of-strings-with-concatenation-equal-to-target/">...</a>
 * <p>
 * Find All K-Distant Indices in an Array
 * <a href="https://leetcode.com/problems/find-all-k-distant-indices-in-an-array/">...</a>
 * <p>
 * First Letter to Appear Twice
 * <a href="https://leetcode.com/problems/first-letter-to-appear-twice/">...</a>
 * <p>
 * Number of Excellent Pairs
 * <a href="https://leetcode.com/problems/number-of-excellent-pairs/">...</a>
 * <p>
 * Number of Arithmetic Triplets
 * <a href="https://leetcode.com/problems/number-of-arithmetic-triplets/">...</a>
 * <p>
 * Node With Highest Edge Score
 * <a href="https://leetcode.com/problems/node-with-highest-edge-score/">...</a>
 * <p>
 * Check Distances Between Same Letters
 * <a href="https://leetcode.com/problems/check-distances-between-same-letters/">...</a>
 * <p>
 * Find Subarrays With Equal Sum
 * <a href="https://leetcode.com/problems/find-subarrays-with-equal-sum/">...</a>
 * <p>
 * Largest Positive Integer That Exists With Its Negative
 * <a href="https://leetcode.com/problems/largest-positive-integer-that-exists-with-its-negative/">...</a>
 * <p>
 * Number of Distinct Averages
 * <a href="https://leetcode.com/problems/number-of-distinct-averages/">...</a>
 * <p>
 * Count Pairs Whose Sum Is Less Than Target
 * <a href="https://leetcode.com/problems/count-pairs-whose-sum-is-less-than-target/">...</a>
 *
 */

class TwoSum {
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

    public static class ThreeSum {
        public List<List<Integer>> threeSum(int[] nums) {
            return threeSum(nums, 0);
        }

        public List<List<Integer>> threeSum(int[] nums, int target) {
            List<List<Integer>> res = new ArrayList<>();
            Arrays.sort(nums);
            int n = nums.length;
            for (int i = 0; i < n - 2; i++) {
                if (i == 0 || nums[i] != nums[i - 1]) {
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

    public static class FourSum {

        public List<List<Integer>> fourSum(int[] nums, int target) {
            Arrays.sort(nums);
            return kSum(nums, target, 0, 4);
        }

        public List<List<Integer>> kSum(int[] nums, long target, int start, int k) {
            List<List<Integer>> res = new ArrayList<>();

            // If we have run out of numbers to add, return res.
            if (start == nums.length) {
                return res;
            }

            // There are k remaining values to add to the sum. The
            // average of these values is at least target / k.
            long average_value = target / k;

            // We cannot obtain a sum of target if the smallest value
            // in nums is greater than target / k or if the largest
            // value in nums is smaller than target / k.
            if (
                    nums[start] > average_value || average_value > nums[nums.length - 1]
            ) {
                return res;
            }

            if (k == 2) {
                return twoSum(nums, target, start);
            }

            for (int i = start; i < nums.length; ++i) {
                if (i == start || nums[i - 1] != nums[i]) {
                    for (List<Integer> subset : kSum(
                            nums,
                            target - nums[i],
                            i + 1,
                            k - 1
                    )) {
                        res.add(new ArrayList<>(List.of(nums[i])));
                        res.get(res.size() - 1).addAll(subset);
                    }
                }
            }

            return res;
        }

        public List<List<Integer>> twoSum(int[] nums, long target, int start) {
            List<List<Integer>> res = new ArrayList<>();
            Set<Long> s = new HashSet<>();

            for (int i = start; i < nums.length; ++i) {
                if (res.isEmpty() || res.get(res.size() - 1).get(1) != nums[i]) {
                    if (s.contains(target - nums[i])) {
                        res.add(Arrays.asList((int) target - nums[i], nums[i]));
                    }
                }
                s.add((long) nums[i]);
            }

            return res;
        }

    }

    public static class TwoSumIIInputArrayIsSorted {
        public int[] twoSum(int[] numbers, int target) {
            int n = numbers.length;
            int s = 0;
            int e = n - 1;
            while (s <= e) {
                int sum = numbers[s] + numbers[e];
                if (sum == target) {
                    return new int[]{s + 1, e + 1};
                } else if (sum > target) {
                    e--;
                } else {
                    s++;
                }
            }
            return new int[]{-1, -1};
        }
    }

    public static class TwoSumIIIDataStructureDesign {
        //Design a data structure that supports the following two operations:
        //
        //1?? add(number)
        //
        //Adds the number to an internal data structure.
        //
        //2?? find(value)
        //
        //Returns true if there exists any pair of numbers whose sum is equal to value.
        //
        //Otherwise, returns false.

        //add(1);
        //add(3);
        //add(5);
        //
        //find(4) ? true   // 1 + 3 = 4
        //find(7) ? false  // No such pair

        //Important Notes
        //
        //You can use the same number twice only if it was added twice.
        //
        //Order of insertion does not matter.
        //
        //This is a design + data structure question, not a one-time array problem.


        // number -> frequency
        private final Map<Integer, Integer> map;

        public TwoSumIIIDataStructureDesign() {
            map = new HashMap<>();
        }

        // O(1) time
        public void add(int number) {
            // number ko map me daal rahe hain
            // agar pehle se hai toh frequency badhao
            map.put(number, map.getOrDefault(number, 0) + 1);
        }

        // O(n) time
        public boolean find(int value) {
            // har number ke liye check karenge
            for (int num : map.keySet()) {

                int complement = value - num;

                if (complement == num) {
                    // same number use karna hai
                    // toh frequency >= 2 honi chahiye
                    if (map.get(num) >= 2) {
                        return true;
                    }
                } else {
                    // different number hai
                    // agar complement map me exist karta hai
                    if (map.containsKey(complement)) {
                        return true;
                    }
                }
            }
            return false;
        }

    }

    public static class SubarraySumEqualsK {
        //Hume count karna hai kitne continuous subarrays ka sum = k hota hai.

        // Prefix sum intuition
        //sum(i, j) = prefixSum[j] - prefixSum[i-1]
        //prefixSum[j] - prefixSum[i-1] = k
        // prefixSum[i-1] = prefixSum[j] - k

        //Hum prefix sum ka use karte hain aur ek HashMap me store karte hain ki koi sum kitni baar aa chuka hai.
        //Matlab agar (currentSum - k) pehle kahin mila hai,
        //toh waha se lekar current index tak ka subarray valid hai
        public int subarraySum(int[] nums, int k) {
            int n = nums.length;
            Map<Integer, Integer> m = new HashMap<>();
            int sum = 0, count = 0;
            //Matlab agar directly koi prefix sum == k ho, toh wo bhi count ho jaye
            m.put(0, 1);
            for (int i = 0; i < n; i++) {
                //Har element add karke current prefix sum nikal rahe ho
                sum += nums[i];
                //Agar pehle (sum - k) exist karta hai
                //
                //Toh jitni baar wo aaya hoga, utni subarrays yahan end ho rahi hongi
                if (m.containsKey(sum - k)) {
                    count += m.get(sum - k);
                }
                m.put(sum, m.getOrDefault(sum, 0) + 1);
            }
            return count;
        }
    }

    public static class TwoSumIVInputIsABST {
        public boolean findTarget(TreeNode root, int k) {
            Set<Integer> set = new HashSet<>();
            return find(set, root, k);
        }

        boolean find(Set<Integer> set, TreeNode root, int k) {
            if (root == null) {
                return false;
            }

            if (set.contains(k - root.val)) {
                return true;
            }

            set.add(root.val);

            return find(set, root.left, k) || find(set, root.right, k);


        }

        public static class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;

            TreeNode() {
            }

            TreeNode(int val) {
                this.val = val;
            }

            TreeNode(int val, TreeNode left, TreeNode right) {
                this.val = val;
                this.left = left;
                this.right = right;
            }
        }


    }

    public static class TwoSumLessThanK {

        //Given an array nums of integers and an integer k,
        //return the maximum sum of two distinct elements in nums such that their sum is less than k.
        //
        //If no such pair exists, return -1.
        //
        // Example 1
        //
        //Input:
        //nums = [34,23,1,24,75,33,54,8], k = 60
        //
        //Output:
        //58

        //We sort the array and use two pointers. If the sum is less than k,
        // we update the answer and move left pointer to increase sum;
        // otherwise, we move right pointer to decrease sum.”

        public int twoSumLessThanK(int[] nums, int k) {
            Arrays.sort(nums);

            int l = 0, r = nums.length - 1;
            int ans = -1;

            while (l < r) {
                int sum = nums[l] + nums[r];

                if (sum < k) {
                    ans = Math.max(ans, sum);
                    l++; // try bigger sum
                } else {
                    r--; // reduce sum
                }
            }
            return ans;
        }
    }

    public static class MaxNumberOfKSumPairs {

        // In one operation, you can pick two numbers from the array whose sum equals k and remove them from the array.
        //
        //Return the maximum number of operations you can perform on the ar

        public int maxOperations(int[] nums, int k) {
            Map<Integer, Integer> map = new HashMap<>();
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                int res = k - nums[i];
                if (map.containsKey(res)) {
                    count++;
                    if (map.get(res) == 1) map.remove(res);
                    else map.put(res, map.get(res) - 1);
                } else {
                    map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
                }
            }
            return count;
        }
    }

    public static class CountGoodMeals {

        //“Ye problem Two Sum ka variant hai jisme
        // target ek fixed number nahi, balki powers of 2 hain.
        // Since max power sirf 22 possible hai,
        // hum har element ke liye 22 checks karke HashMap se
        // count efficiently nikaal sakte hain.”

        int mod = 1000000007;

        public int countPairs(int[] arr) {
            Map<Integer, Integer> map = new HashMap<>();
            int n = arr.length;
            long res = 0;
            for (int x : arr) {
                //for each powerOfTwo (1, 2, 4 ... 2^21)
                int power = 1;
                for (int i = 0; i < 22; i++) {
                    if (map.containsKey(power - x)) {
                        res += map.get(power - x);
                        res %= mod;
                    }
                    power *= 2;
                }
                map.put(x, map.getOrDefault(x, 0) + 1);
            }
            return (int) res;
        }

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

    public static class NumberOfPairsOfStringsWithConcatenationEqualToTarget {

        //Given an array of digit strings nums and a digit string target,
        // return the number of pairs of indices (i, j) (where i != j)
        // such that the concatenation of nums[i] + nums[j] equals target.

        public int numOfPairs(String[] nums, String target) {
            Map<String, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            }
            int ans = 0;
            int n = target.length();
            String a = "";
            String b = "";
            for (int i = 0; i < n; i++) {
                a = target.substring(0, i);
                b = target.substring(i, n);
                if (map.containsKey(a) && map.containsKey(b)) {
                    if (a.equals(b)) {
                        ans += map.get(a) * (map.get(b) - 1);
                    } else {
                        ans += map.get(a) * map.get(b);
                    }
                }
            }
            return ans;
        }
    }

    public static class FindAllKDistantIndicesInAnArray {
        //Tumhe ek array nums diya hai, aur do numbers key aur k.
        //
        //? K-distant index wo index i hota hai jiske liye koi ek index j exist karta ho aisa ki:
        //
        //nums[j] == key
        //
        //aur |i - j| <= k (yaani i aur j ke beech ka distance k se zyada nahi)
        //
        //Agar ye condition satisfy hoti hai, to i valid index hai.
        //
        //Tumhe saare valid indices return karne hain, sorted order me.

        // Intuition
        // Jahan-jahan key present hai array me ? wo center points hain
        //
        //Har center point se left aur right k distance tak ke
        // saare indices valid ho jaate hain

        public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
            List<Integer> res = new ArrayList<>();
            int r = 0;
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                if (nums[i] == key) {
                    int l = Math.max(r, i - k);
                    r = Math.min(n - 1, i + k) + 1;
                    while (l < r) {
                        res.add(l++);
                    }
                }
            }
            return res;
        }

    }

    public static class FirstLetterToAppearTwice {
        public char repeatedCharacter(String s) {
            Set<Character> set = new HashSet<>();
            for (char c : s.toCharArray())
                if (!set.add(c))
                    return c;

            return 'a';// can't reach to this line, because there must be a letter appearing TWICE
        }
    }

    public static class NumberOfExcellentPairs {

        //The Inclusion-Exclusion Principle
        //bits(num1 OR num2) + bits(num1 AND num2) = bits(num1) + bits(num2)

        public long countExcellentPairs(int[] nums, int k) {

            long[] count = new long[30];
            long res = 0;

            Set<Integer> set = new HashSet<>();

            for (int x : nums) {
                set.add(x);
            }

            for (int x : set) {
                count[Integer.bitCount(x)]++;
            }

            for (int i = 0; i < 30; i++) {
                for (int j = 0; j < 30; j++) {
                    if (i + j >= k) {
                        res += count[i] * count[j];
                    }
                }
            }

            return res;
        }
    }

    public static class NumberOfArithmeticTriplets {
        public int arithmeticTriplets(int[] nums, int diff) {
            HashMap<Integer, Integer> seen = new HashMap<>();
            int counter = 0;
            for (int i = 0; i < nums.length; i++) {
                seen.put(nums[i], i);
            }
            for (int num : nums) {
                if (seen.containsKey(num + diff) && seen.containsKey(num + 2 * diff)) {
                    counter++;
                }
            }
            return counter;
        }
    }

    public static class NodeWithHighestEdgeScore {

        public int edgeScore(int[] edges) {
            Map<Integer, Integer> scores = new HashMap<>();
            int highestScoreNode = 0;
            for (int i = 0; i < edges.length; i++) {
                scores.put(edges[i], scores.getOrDefault(edges[i], 0) + i);
                int lastScore = scores.get(edges[i]);
                int highestScore = scores.getOrDefault(highestScoreNode, 0);
                if (lastScore > highestScore)
                    highestScoreNode = edges[i];
                else if (lastScore == highestScore)
                    highestScoreNode = Math.min(edges[i], highestScoreNode);
            }
            return highestScoreNode;
        }

    }

    public static class CheckDistancesBetweenSameLetters {
        public boolean checkDistances(String s, int[] distance) {

            Map<Character, Integer> map = new HashMap<>();
            char[] arr = s.toCharArray();
            int len = arr.length;

            for (int i = 0; i < len; i++) {
                if (map.containsKey(arr[i])) {
                    int start = map.get(arr[i]);
                    int diff = i - start - 1;
                    if (distance[arr[i] - 'a'] != diff) return false;
                } else map.put(arr[i], i);
            }
            return true;
        }
    }

    public static class FindSubarraysWithEqualSum {
        public boolean findSubarrays(int[] nums) {
            if (nums.length < 2) return false;

            Map<Integer, Integer> freq = new HashMap<>();

            for (int i = 0; i < nums.length - 1; i++) {
                int sum = nums[i] + nums[i + 1];
                if (freq.getOrDefault(sum, 0) > 0) {
                    return true;
                }
                freq.put(sum, freq.getOrDefault(sum, 0) + 1);
            }

            return false;
        }


    }

    public static class LargestPositiveIntegerThatExistsWithItsNegative {

        public int findMaxK(int[] nums) {
            HashSet<Integer> hs = new HashSet<>();
            int ans = -1;
            for (int num : nums) {
                hs.add(num);
                int k = num * (-1);
                if (hs.contains(k)) {
                    ans = Math.max(ans, Math.abs(num));
                }
            }
            return ans;
        }
    }

    public static class NumberOfDistinctAverages {

        public int distinctAverages(int[] nums) {
            Arrays.sort(nums);

            Set<Double> u = new HashSet<>();
            int n = nums.length;

            for (int i = 0; i < n / 2; ++i)
                u.add((nums[i] + nums[n - i - 1]) / 2.0);

            return u.size();
        }
    }

    public static class CountPairsWhoseSumIsLessThanTarget {
        public int countPairs(List<Integer> nums, int target) {
            Collections.sort(nums); // sort the vector nums
            int count = 0; // variable to store the count
            int left = 0; // variable to store the left
            int right = nums.size() - 1; // variable to store the right
            while (left < right) { // loop until left is less than right
                if (nums.get(left) + nums.get(right) < target) { // if nums[left] + nums[right] is less than target
                    count += right - left; // update the count
                    left++; // increment the left
                } else { // else
                    right--; // decrement the right
                }
            }
            return count; // return the count

        }
    }
}
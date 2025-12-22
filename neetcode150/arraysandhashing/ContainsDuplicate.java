package arraysandhashing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class ContainsDuplicate {
    public boolean hasDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int x : nums) {
            if (set.contains(x)) {
                return true;
            }
            set.add(x);
        }
        return false;
    }

    static class ContainsDuplicateII {
        //Tumhe ek array nums diya hai aur ek number k.
        //
        //Tumhe check karna hai:
        //
        //kya same value ke do elements exist karte hain
        //
        //aur unke indices ka distance ? k ho
        public boolean containsNearbyDuplicate(int[] nums, int k) {
            Map<Integer, Integer> map = new HashMap<>();
            int i = 0;
            for (int num : nums) {
                if (map.containsKey(num) && isIndexInRange(i, map.get(num), k)) {
                    return true;
                }
                map.put(num, i);
                i++;
            }
            return false;
        }

        private boolean isIndexInRange(int curr_index, int map_index, int k) {
            int x = Math.abs(curr_index - map_index);
            return x <= k;
        }
    }

    static class ContainsDuplicateIII {
        //Tumhe array nums diya hai + 2 limits:
        //
        //indexDiff ? index ka distance
        //
        //valueDiff ? value ka distance
        //
        //Tumhe check karna hai:
        //
        //i != j
        //|i - j| <= indexDiff
        //|nums[i] - nums[j]| <= valueDiff

        // Bucket sort solution - O(n)
        //Bucket ka idea kya hai?
        //
        //Socho number line ko equal-size ke boxes (buckets) mein divide kar diya:
        //
        //Bucket width = (t + 1)
        //
        //Har bucket ek range cover karta hai
        //t = 3
        //Bucket width = 4
        //
        //Buckets:
        //[0 – 3], [4 – 7], [8 – 11], ...
        //bucketId = num / (t + 1)
        //Agar do numbers ka difference ? t hai, to unke beech distance t se zyada ho hi nahi sakta.
        //Har new number ke liye sirf 3 checks kaafi hain :
        //
        //Same bucket
        //
        //Left bucket
        //
        //Right bucket


        // ye fucntion map krta hai kisi num ko ek bucket me jiki width given hai
        // negative ko ye floor like division se handle krta hai
        private long getBucketId(long num, long width) {
            return num < 0 ? (num + 1) / width - 1 : num / width;
        }

        public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
            if (valueDiff < 0) return false;
            Map<Long, Long> lookup = new HashMap<>();
            // isme +1 kr rhe kyuki zero se divide possible nhi
            long bucketWidth = (long) valueDiff + 1;

            for (int i = 0; i < nums.length; ++i) {

                long bucketId = getBucketId(nums[i], bucketWidth);

                if (lookup.containsKey(bucketId))
                    return true;

                else if (lookup.containsKey(bucketId - 1) && Math.abs(nums[i] - lookup.get(bucketId - 1)) < bucketWidth)
                    return true;

                else if (lookup.containsKey(bucketId + 1) && Math.abs(nums[i] - lookup.get(bucketId + 1)) < bucketWidth)
                    return true;

                lookup.put(bucketId, (long) nums[i]);

                // jo bucket range se bahar hogi usko lookup se hata do
                if (i >= indexDiff) {
                    long bucketOutsideOfRange = getBucketId(nums[i - indexDiff], bucketWidth);
                    lookup.remove(bucketOutsideOfRange);
                }
            }

            return false;
        }
    }

    static class MakeArrayZeroBySubtractingEqualAmounts {
        // ou are given a non-negative integer array nums. In one operation, you must:
        //
        //Choose a positive integer x such that x is less than or equal to the smallest non-zero element in nums.
        //Subtract x from every positive element in nums.
        //Return the minimum number of operations to make every element in nums equal to 0.

        //Input: nums = [1,5,0,3,5]
        //Output: 3
        //Explanation:
        //In the first operation, choose x = 1. Now, nums = [0,4,0,2,4].
        //In the second operation, choose x = 2. Now, nums = [0,2,0,0,2].
        //In the third operation, choose x = 2. Now, nums = [0,0,0,0,0].

        // observation : 1. same elements are always same - ek operation me sab ek sath nipat jaege
        // 2. different elements always different, matlab inko nipatane me unique operations lagege
        // return no. of unique elements

        public int minimumOperations(int[] nums) {
            Set<Integer> set = new HashSet<>();
            for (int n : nums) {
                if (n > 0) {
                    set.add(n);
                }
            }
            return set.size();
        }
    }

    static class FindValidPairOfAdjacentDigitsInString {
        //You are given a string s consisting only of digits. A valid pair is defined as two adjacent digits in s such that:
        //
        //The first digit is not equal to the second.
        //Each digit in the pair appears in s exactly as many times as its numeric value.
        //Return the first valid pair found in the string s when traversing from left to right.
        // If no valid pair exists, return an empty string.

        //Input: s = "2523533"
        //
        //Output: "23"
        //
        //Explanation:
        //
        //Digit '2' appears 2 times and digit '3' appears 3 times.
        // Each digit in the pair "23" appears in s exactly as many times as its numeric value. Hence, the output is "23".

        public String findValidPair(String s) {
            int[] f = new int[10];
            int n = s.length();
            for (int i = 0; i < n; i++) {
                f[s.charAt(i) - '0']++;
            }
            for (int i = 1; i < n; i++) {
                int l = s.charAt(i - 1) - '0';
                int r = s.charAt(i) - '0';
                if (l == r)
                    continue;
                if (f[l] == l && f[r] == r) {
                    return l + String.valueOf(r);
                }
            }
            return "";
        }
    }
}
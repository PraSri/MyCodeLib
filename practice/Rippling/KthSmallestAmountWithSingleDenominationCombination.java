package Rippling;

//https://leetcode.com/problems/kth-smallest-amount-with-single-denomination-combination/description/

import java.util.*;

public class KthSmallestAmountWithSingleDenominationCombination {
    static class Solution {
        public long findKthSmallest(int[] coins, int k) {
            List<Long> lcms = new ArrayList<>();
            int n = 1 << coins.length;

            for (int i = 1; i < n; ++i) {
                long lcm = 1;

                for (int j = 0; j < coins.length; ++j) {
                    if ((i & (1 << j)) > 0) {
                        lcm = lcm(lcm, coins[j]);
                    }
                }

                lcms.add(lcm * (Integer.bitCount(i) % 2 == 1 ? 1 : -1));
            }

            long low = 1;
            long high = Long.MAX_VALUE;

            while (low < high) {
                long mid = low + (high - low) / 2;

                if (count(mid, lcms) < k) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }

            return low;
        }

        private long count(long target, List<Long> lcms) {
            long count = 0;

            for (long lcm : lcms) {
                count += target / lcm;
            }

            return count;
        }

        private long gcd(long a, long b) {
            if (b == 0) return a;
            return gcd(b, a % b);
        }

        private long lcm(long a, long b) {
            return a * (b / gcd(a, b));
        }
    }
}

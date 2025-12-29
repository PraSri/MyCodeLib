package slidingwindow;

public class MinimumWindowSubstring {

    /****Similar Questions – Minimum Window Substring (LeetCode)**

1. **Substring with Concatenation of All Words**
   [https://leetcode.com/problems/substring-with-concatenation-of-all-words/](https://leetcode.com/problems/substring-with-concatenation-of-all-words/)

2. **Minimum Size Subarray Sum**
   [https://leetcode.com/problems/minimum-size-subarray-sum/](https://leetcode.com/problems/minimum-size-subarray-sum/)

3. **Sliding Window Maximum**
   [https://leetcode.com/problems/sliding-window-maximum/](https://leetcode.com/problems/sliding-window-maximum/)

4. **Permutation in String**
   [https://leetcode.com/problems/permutation-in-string/](https://leetcode.com/problems/permutation-in-string/)

5. **Smallest Range Covering Elements from K Lists**
   [https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/](https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/)

6. **Minimum Window Subsequence**
   [https://leetcode.com/problems/minimum-window-subsequence/](https://leetcode.com/problems/minimum-window-subsequence/)

7. **Count Substrings That Can Be Rearranged to Contain a String II**
   [https://leetcode.com/problems/count-substrings-that-can-be-rearranged-to-contain-a-string-ii/](https://leetcode.com/problems/count-substrings-that-can-be-rearranged-to-contain-a-string-ii/)

8. **Count Substrings That Can Be Rearranged to Contain a String I**
   [https://leetcode.com/problems/count-substrings-that-can-be-rearranged-to-contain-a-string-i/](https://leetcode.com/problems/count-substrings-that-can-be-rearranged-to-contain-a-string-i/)
*/

    //Input: s = "OUZODYXAZV", t = "XYZ"
    //
    //Output: "YXAZ"

    /***
     * Humko string s ke andar ek minimum size ka window dhoondna hai jisme string t ke saare characters present ho.
     *
     *�Window ko right se expand karo, left se shrink karo �
     * dono pointers sirf aage badhte hain � isi wajah se complexity O(n) hoti hai.�
     * */


        public String minWindow(String s, String t) {

            int n = s.length();
            int c = t.length();

            int[] f = new int[128];
            int start = 0;
            int end = 0;
            int minLen = Integer.MAX_VALUE;
            int minStart = 0;

//            1. Pehle frequency array banaya
            //Mujhe string t ke kaunse characters chahiye aur kitni quantity mein?
            for(char ch: t.toCharArray()) {
                f[ch - 'A']++;
            }
//            c ? kitne characters abhi bhi chahiye window ko valid banane ke liye

            while(end < n) {
//                Right pointer aage badhao, aur jaise hi koi required character mile, bacha hua required count (c) kam kar do.
                if(f[s.charAt(end) - 'A'] > 0) {
                    c--;
                }
                f[s.charAt(end)-'A']--;
                end++; // expansion
//                �Ab window ke andar t ke saare characters aa gaye hain.�
                while(c==0) {
                    // record stats
                    if(minLen > (end - start)) {
                        minLen = end - start;
                        minStart = start;
                    }
                    // contraction is window
//                    Ab hum try karte hain window ko shrink karne ka:
                    char windowStart = s.charAt(start);
                    f[windowStart - 'A']++;
                    if(f[windowStart - 'A'] > 0) {
                        c++;
                    }
                    // �Ab left side se window ko chhota karne ki koshish karo,
                    //lekin jaise hi koi required character window se bahar chala jaye, 
                    // window invalid ho jayegi � fir dubara expand karna padega.�
                    start++;
                }
            }

            return minLen==Integer.MAX_VALUE?"":s.substring(minStart, minStart + minLen);
        }

    /**
     * <a href="https://leetcode.com/problems/substring-with-concatenation-of-all-words/">LeetCode - Substring with Concatenation of All Words</a>
     */
    public static class SubstringWithConcatenationOfAllWords {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/minimum-size-subarray-sum/">LeetCode - Minimum Size Subarray Sum</a>
     */
    public static class MinimumSizeSubarraySum {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/sliding-window-maximum/">LeetCode - Sliding Window Maximum</a>
     */
    public static class SlidingWindowMaximum {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/permutation-in-string/">LeetCode - Permutation in String</a>
     */
    public static class PermutationInString {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/">LeetCode - Smallest Range Covering Elements from K Lists</a>
     */
    public static class SmallestRangeCoveringElementsFromKLists {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/minimum-window-subsequence/">LeetCode - Minimum Window Subsequence</a>
     */
    public static class MinimumWindowSubsequence {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/count-substrings-that-can-be-rearranged-to-contain-a-string-ii/">LeetCode - Count Substrings That Can Be Rearranged to Contain a String II</a>
     */
    public static class CountSubstringsThatCanBeRearrangedToContainAStringII {
        // placeholder
    }

    /**
     * <a href="https://leetcode.com/problems/count-substrings-that-can-be-rearranged-to-contain-a-string-i/">LeetCode - Count Substrings That Can Be Rearranged to Contain a String I</a>
     */
    public static class CountSubstringsThatCanBeRearrangedToContainAStringI {
        // placeholder
    }
}

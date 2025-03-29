package String;

import java.util.*;

public class LongestRepeatingCharacterReplacement {

    /**
     *
     * https://leetcode.com/problems/longest-repeating-character-replacement/
    * */

    public int characterReplacement(String st, int k) {
        int[] f = new int[26];
        int mostFreqLetter = 0;
        int s = 0;
        int e = 0;
        int n = st.length();
        int max = 0;

        while(e < n) {
            f[st.charAt(e)-'A']++;
            mostFreqLetter = Math.max(mostFreqLetter, f[st.charAt(e)-'A']);
            int changes = (e-s+1)-mostFreqLetter;
            if(changes > k) {
                f[st.charAt(s) - 'A']--;
                s++;
            }
            max = Math.max(max, (e-s+1));
            e++;
        }
        return max;
    }

     public static int longestRepeatingCharacterReplacement(String s, int k) {
        // initialize variables
        int stringLength = s.length();
        int lengthOfMaxSubstring = 0;
        int start = 0;
        Map<Character, Integer> charFreq = new HashMap<>();
        int mostFreqChar = 0;

        // iterate over the input string
        for (int end = 0; end < stringLength; ++end) {
            char currentChar = s.charAt(end);
            
            // if the new character is not in the hash map, add it, else increment its frequency
            charFreq.put(currentChar, charFreq.getOrDefault(currentChar, 0) + 1);
            
            // update the most frequent char
            mostFreqChar = Math.max(mostFreqChar, charFreq.get(currentChar));
            
            // if the number of replacements in the current window have exceeded the limit, slide the window
            if (end - start + 1 - mostFreqChar > k) {
                charFreq.put(s.charAt(start), charFreq.get(s.charAt(start)) - 1);
                start += 1;
            }

            // if this window is the longest so far, update the length of max substring
            lengthOfMaxSubstring = Math.max(lengthOfMaxSubstring, end - start + 1);
        }

        // return the length of the max substring with same characters after replacement(s)
        return lengthOfMaxSubstring;
    }

    // driver code
    public static void main(String[] args) {
        List<String> inputStrings = Arrays.asList("aabccbb", "abbcb", "abccde", "abbcab", "bbbbbbbbb");
        List<Integer> k = Arrays.asList(2, 1, 1, 2, 4);

        for (int i = 0; i < inputStrings.size(); ++i) {
            System.out.println((i + 1) + ".\tInput String: '" + inputStrings.get(i) + "'");
            System.out.println("\tk: " + k.get(i));
            System.out.println("\tLength of the longest substring with repeating characters: "
                    + longestRepeatingCharacterReplacement(inputStrings.get(i), k.get(i)));
            System.out.println(new String(new char[100]).replace("\0", "-"));
        }
    }
    
}

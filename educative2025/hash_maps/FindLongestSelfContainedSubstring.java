package hash_maps;

import java.util.*;

public class FindLongestSelfContainedSubstring {

    public static int maxSubstringLength(String s) {

        // Maps to store the first and last occurrence of each character
        Map<Character, Integer> first = new HashMap<>();
        Map<Character, Integer> last = new HashMap<>();

        // Populate the first and last occurrences of each character
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!first.containsKey(c)) {
                first.put(c, i);  // Store the first occurrence of character c
            }
            last.put(c, i);       // Always update to store the last occurrence of character c
        }

        // Initialize the maximum length of valid self-contained substring
        int maxLen = -1;

        // Iterate over each unique character's first occurrence
        for (char c1 : first.keySet()) {
            int start = first.get(c1);  // Starting index of the current window
            int end = last.get(c1);     // Initial end index based on c1's last occurrence
            int j = start;              // Pointer to expand the window

            // Expand the window to include the complete range of all characters inside it
            while (j < s.length()) {
                char c2 = s.charAt(j);  // Current character inside the window

                // If any character's first occurrence is before the window's start, window is invalid
                if (first.get(c2) < start) {
                    break;
                }

                // Update the end to include the farthest last occurrence of any character in the window
                end = Math.max(end, last.get(c2));

                // If the window is complete (j reached end) and not equal to the full string
                if (j == end && end - start + 1 != s.length()) {
                    // Update maxLen if a longer valid substring is found
                    maxLen = Math.max(maxLen, end - start + 1);
                }

                j++; // Move to the next character
            }
        }

        // Return the length of the longest valid self-contained substring found, or -1 if none
        return maxLen;
    }

    public static void main(String[] args) {
        String[] testCases = {
                "xyyx",      // Expected output: 2 ("yy")
                "abab",      // Expected output: -1 (no valid substring)
                "abacd",     // Expected output: 4 ("abac")
                "aabbcc",    // Expected output: 2 ("aa", "bb", or "cc")
                "abcabcabc"  // Expected output: -1 (all repeated across the string)
        };

        for (int i = 0; i < testCases.length; i++) {
            System.out.println((i + 1) + ".\tstring: " + testCases[i]);
            int result = maxSubstringLength(testCases[i]);
            System.out.println("\tOutput: " + result);
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }

}

package hash_maps;

import java.util.HashMap;
import java.util.Map;

public class NumberOfWonderfulSubstrings {

    // Function to calculate the number of wonderful substrings in a given string
    public static long wonderfulSubstrings(String word) {
        // Initialize a frequency map to store the count of each bitmask
        Map<Integer, Integer> freq = new HashMap<>();
        // The empty prefix (bitmask of 0) is considered to be seen once
        freq.put(0, 1);

        // Variable to hold the current bitmask for character parity
        int mask = 0;
        // Result variable to count the number of wonderful substrings
        long res = 0;

        // Iterate through each character in the input word
        for (char c : word.toCharArray()) {
            // Calculate the corresponding bit position for the character
            int bit = c - 'a';  // 'a' is 0, 'b' is 1, ..., 'j' is 9
            // Toggle the bit corresponding to the current character in the mask
            mask ^= (1 << bit);

            // Check if the current mask has been seen before
            if (freq.containsKey(mask)) {
                // If it has, add its frequency to the result
                res += freq.get(mask);
                // Increment the count for this mask in the frequency map
                freq.put(mask, freq.get(mask) + 1);
            } else {
                // If not, initialize its count to 1
                freq.put(mask, 1);
            }

            // Check for all possible characters that can be odd
            for (int odd_c = 0; odd_c < 10; ++odd_c) {
                // Toggle the bit for the odd character and check if that mask exists
                int toggled_mask = mask ^ (1 << odd_c);
                if (freq.containsKey(toggled_mask)) {
                    // If it does, add its frequency to the result
                    res += freq.get(toggled_mask);
                }
            }
        }

        // Return the total count of wonderful substrings
        return res;
    }

    // Driver code
    public static void main(String[] args) {
        String[] words = {
                "aba",               // Example 1
                "aabb",              // Example 2
                "he",                // Example 3
                "ccj",               // Example 4
                "abcdefghij",        // Example 5
                "jjjjjjjjjj"         // Example 6
        };

        for (int i = 0; i < words.length; i++) {
            System.out.println((i + 1) + ".\tword: " + words[i]);
            System.out.println("\n\tNumber of wonderful substrings: " + wonderfulSubstrings(words[i]));
            System.out.println(new String(new char[100]).replace("\0", "-"));
        }
    }
}

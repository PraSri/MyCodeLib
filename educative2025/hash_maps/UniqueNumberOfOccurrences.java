package hash_maps;

import java.util.*;

public class UniqueNumberOfOccurrences {
    public static boolean uniqueOccurrences(int[] nums) {
        // Count the frequency of each element
        // Map to store the frequency of elements
        Map<Integer, Integer> frequency = new HashMap<>();
        // Iterate over the nums array
        for (int num : nums) {
            // Check if num is already in the map
            if (frequency.containsKey(num)) {
                // Increment the count for existing elements
                frequency.put(num, frequency.get(num) + 1);
            } else {
                // Initialize the count for new elements
                frequency.put(num, 1);
            }
        }

        // Check if all frequencies are unique
        Set<Integer> seenFrequencies = new HashSet<>();
        // Set to store unique frequencies
        for (int count : frequency.values()) {
            if (seenFrequencies.contains(count)) {
                // If frequency is already seen, return false
                return false;
            }
            // Add frequency to the set
            seenFrequencies.add(count);
        }

        // All frequencies are unique, return true
        return true;
    }

}

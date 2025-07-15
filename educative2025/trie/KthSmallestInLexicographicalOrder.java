package trie;

public class KthSmallestInLexicographicalOrder {

    public static int findKthNumber(int n, int k) {

        // Start from number 1 (the smallest lexicographical number)
        int currentNumber = 1;

        // We already count '1' as the first number, so decrease k by 1
        k -= 1;

        // Loop until we've found the k-th number
        while (k > 0) {

            // Calculate how many numbers are in the current subtree (numbers starting with current_number)
            int numbersUnderPrefix = countNumbersUnderPrefix(n, currentNumber, currentNumber + 1);

            // If k is in the child subtree, go deeper to move to the first child
            // (i.e., next digit level in the same prefix)
            // For example, from 1 -> 10 -> 100...
            if (k < numbersUnderPrefix) {

                // Move down one level
                currentNumber *= 10;

                // Reduce k by 1
                k -= 1;
            } else {

                // Otherwise, if k is in the sibling subtree, i.e., if there are fewer or equal numbers than k under current prefix,
                // move to the next sibling by skipping this whole prefix subtree

                // Move to the next sibling
                currentNumber += 1;

                // Reduce k by how many numbers we skipped
                k -= numbersUnderPrefix;
            }
        }

        // This is the k-th number in lexicographical order
        return currentNumber;
    }

    // Helper function to count how many numbers are in the range [prefix_start, prefix_end) in lex order
    private static int countNumbersUnderPrefix(int n, long prefixStart, long prefixEnd) {
        int numbersUnderPrefix = 0;

        // Loop through each level of the number tree (1-digit, 2-digit, etc.)
        while (prefixStart <= n) {

            // Count numbers in this range, but don't go beyond n
            // At each level, we calculate the range [prefix_start, prefix_end)
            // For example: 1 to 2, 10 to 20, 100 to 200, etc.
            numbersUnderPrefix += Math.min(n + 1L, prefixEnd) - prefixStart;

            // Go one level deeper by multiplying by 10
            // This simulates moving to the next digit depth
            prefixStart *= 10;
            prefixEnd *= 10;
        }

        // Total count of valid numbers under the original prefix
        return numbersUnderPrefix;
    }

    // Driver code
    public static void main(String[] args) {
        int[][] testCases = {
                {13, 2},
                {100, 10},
                {1, 1},
                {1000, 100},
                {4289384, 1922239}
        };

        for (int i = 0; i < testCases.length; i++) {
            int n = testCases[i][0];
            int k = testCases[i][1];
            System.out.println((i + 1) + ".\tInput:\n\tn = " + n + ", k = " + k);
            System.out.println("\n\tK-th Smallest in Lexicographical Order = " + findKthNumber(n, k));
            System.out.println("-".repeat(100));
        }
    }
}
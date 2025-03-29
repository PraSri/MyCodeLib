package greedy_techiniques;

public class MaximumSwap {
    public static int maximumSwap(int num) {
        // Convert the number to a string for easy manipulation
        char[] numString = String.valueOf(num).toCharArray();
        int n = numString.length;

        // Variables to track the index of the maximum digit and the two swap indices
        int maxDigitIndex = -1, index1 = -1, index2 = -1;

        // Iterate the string from the last digit to the first
        for (int i = n - 1; i >= 0; i--) {
            // Update the max digit index if the current digit is greater
            if (maxDigitIndex == -1 || numString[i] > numString[maxDigitIndex]) {
                maxDigitIndex = i;
            }
            // If the current digit is less than the max digit found so far,
            // mark this index (index1) and the max digit's index (index2) for swapping
            else if (numString[i] < numString[maxDigitIndex]) {
                index1 = i;
                index2 = maxDigitIndex;
            }
        }

        // Perform the swap if valid indices are found
        if (index1 != -1 && index2 != -1) {
            char temp = numString[index1];
            numString[index1] = numString[index2];
            numString[index2] = temp;
        }

        // Convert the string back to an integer and return it
        return Integer.parseInt(new String(numString));
    }
    
    // Driver code
}

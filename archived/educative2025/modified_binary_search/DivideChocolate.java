package modified_binary_search;

import java.util.Arrays;

public class DivideChocolate {
    /**
     * You have a chocolate bar made up of several chunks,
     * and each chunk has a certain sweetness, given in an array called sweetness.
     * You want to share the chocolate with k friends. To do that, you'll make k cuts to divide the bar into k + 1 parts.
     * Each part will have chunks that are next to each other.
     * <p>
     * Being a kind person, you'll take the piece with the minimum total sweetness
     * and give the other parts to your friends. Find and return the maximum possible minimum sweetness
     * you can get if you cut the chocolate optimally.
     * TC: O(n*log S), n = total chocolate chunks, S = upper bound on binary search range
     * SC: O(1)
     */

    public static int maximizeSweetness(int[] sweetness, int k) {
        int low = 1;
        int high = Arrays.stream(sweetness).sum() / (k + 1); // if evenly divided
        int result = low;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canDivide(sweetness, k, mid)) {
                // my understanding: agar mid value se divide kr pa rahe, toh ye minimum sweetness ho skti hai
                // but hume maximum minimum sweetness chahiye, iss liye right side ke search space me ja rhe
                // aur iss liye hum low = mid + 1 kr rhe
                result = low; // this sweetness possible, try for higher values
                low = mid + 1; // move to right side
            } else {
                high = mid - 1; // not possible, move to left
            }
        }
        return result; // return the best found result
    }

    // helper function to check that the chocolate can be divided into atleast k+1 pieces
    // such that each piece has atleast 'minSweetness' total sweetness
    private static boolean canDivide(int[] sweetness, int k, int minSweetness) {
        int totalSweetness = 0;
        int pieces = 0;
        for (int sweet : sweetness) {
            totalSweetness += sweet;
            if (totalSweetness >= minSweetness) {
                pieces++;
                // reset to totalSweetness to zero, becuase 1 piece is completed, start for new piece
                totalSweetness = 0;
            }
        }
        return pieces >= k + 1;
    }

}

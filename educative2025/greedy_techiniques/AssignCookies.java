import java.util.Arrays;

public class AssignCookies {
    
    public static int findContentChildren(int[] greedFactors, int[] cookieSizes) {
        // Sort both arrays
        Arrays.sort(greedFactors);
        Arrays.sort(cookieSizes);
        
        int currentChild = 0, currentCookie = 0;
        int contentChildren = 0;
        
        // Iterate through both arrays using two pointers
        while (currentChild < greedFactors.length && currentCookie < cookieSizes.length) {
            if (cookieSizes[currentCookie] >= greedFactors[currentChild]) {
                // If the current cookie can satisfy the current child
                contentChildren++;
                currentChild++;  // Move to the next child
            }
            currentCookie++;  // Move to the next cookie regardless of whether a child was content or not
        }
        
        return contentChildren;
    }

    public static void main(String[] args) {
        
        int[][] greedFactors = {
            {1, 2, 3},
            {10, 20, 30, 40 ,50 ,60 ,70, 80},
            {3, 4, 5, 6, 7, 8},
            {9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
            {10, 9, 8, 7},
            {1000, 996, 867, 345, 23, 12}
        };
        
        int[][] cookieSizes = {
            {1, 1},
            {10, 20, 30, 40 ,50 ,60 ,70, 80, 90, 100},
            {1, 2},
            {9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9},
            {5, 6, 7, 8},
            {}
        };
        
        for (int i = 0; i < greedFactors.length; i++) {
            int result = findContentChildren(greedFactors[i], cookieSizes[i]);
            System.out.println((i + 1) + ".\tGreed factors: " + Arrays.toString(greedFactors[i]));
            System.out.println("\tCookie sizes: " + Arrays.toString(cookieSizes[i]));
            System.out.println("\n\tResult: " + result);
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}

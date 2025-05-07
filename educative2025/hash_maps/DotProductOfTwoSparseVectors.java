package hash_maps;

import java.util.HashMap;
import java.util.Map;

public class DotProductOfTwoSparseVectors {

    // Create a hash map to store non-zero elements and their indexes
    private Map<Integer, Integer> hashmap;

    public DotProductOfTwoSparseVectors(int[] nums) {
        hashmap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                hashmap.put(i, nums[i]);
            }
        }
    }

    public int dotProduct(DotProductOfTwoSparseVectors vec) {
        // Initialize a variable to store the final result of the dot product
        int sum = 0;
        // Calculate the dot product of two sparse vectors
        for (Map.Entry<Integer, Integer> entry : hashmap.entrySet()) {
            int i = entry.getKey();
            int n = entry.getValue();
            if (vec.hashmap.containsKey(i)) {
                sum += n * vec.hashmap.get(i);
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[][] nums1 = {{1, 0, 0, 2, 3}, {0, 1, 0, 0, 0}, {0, 1, 0, 0, 2, 0, 0}, {0, 2, 0, 3, 0, 0, 1}, {1, 0, 0, 0, 0, 0, 5}};

        int[][] nums2 = {{0, 3, 0, 4, 0}, {0, 0, 0, 0, 2}, {1, 0, 0, 0, 3, 0, 4}, {1, 0, 0, 4, 0, 0, 2}, {0, 6, 0, 0, 0, 7, 0}};

        for (int i = 0; i < nums1.length; i++) {
            System.out.print((i + 1) + ".\tnums1: [ ");
            for (int num : nums1[i]) {
                System.out.print(num + " ");
            }
            System.out.println("]");

            DotProductOfTwoSparseVectors v1 = new DotProductOfTwoSparseVectors(nums1[i]);

            System.out.print(" \tnums2: [ ");
            for (int num : nums2[i]) {
                System.out.print(num + " ");
            }
            System.out.println("]");

            DotProductOfTwoSparseVectors v2 = new DotProductOfTwoSparseVectors(nums2[i]);

            System.out.println("\n\tDot product: " + v1.dotProduct(v2));
            System.out.println(new String(new char[100]).replace("\0", "-"));
        }
    }

}

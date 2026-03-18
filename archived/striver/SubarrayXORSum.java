import java.util.HashMap;

public class SubarrayXORSum {
    public static int countSubarraysWithXOR(int[] nums, int k) {
        int count = 0;
        int prefixXor = 0;
        HashMap<Integer, Integer> xorCountMap = new HashMap<>();
        xorCountMap.put(0, 1); // To handle cases where prefixXor itself is k

        for (int num : nums) {
            prefixXor ^= num;

            // Check if there exists a subarray with XOR k
            count += xorCountMap.getOrDefault(prefixXor ^ k, 0);

            // Update the map
            xorCountMap.put(prefixXor, xorCountMap.getOrDefault(prefixXor, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        int[] nums = {4, 2, 2, 6, 4};
        int k = 6;
        System.out.println(countSubarraysWithXOR(nums, k)); // Output: 4
    }
}
package greedy_techiniques;

public class MinimumReplacementsToSortTheArray {

    public long minimumReplacement(int[] nums) {
        int n = nums.length;
        int last = nums[n - 1];
        long ans = 0;
        // do reverse order traversal
        for (int i = n - 2; i >= 0; i--) {
            int curr = nums[i];
            if (curr > last) { // If the current element needs replacement
                int t = curr / last;  // Calculate how many times the element needs to be divided
                if (curr % last != 0) {
                    t++;  // If there's a remainder, increment 't', why?? because if its divisible, it will split in equal parts
                }
                last = curr / t;  // Update 'last' for the next comparison
                ans += t - 1; // Add (t - 1) to 'ans' for the number of operations
            } else {
                last = curr;
            }
        }
        return ans;
    }
}

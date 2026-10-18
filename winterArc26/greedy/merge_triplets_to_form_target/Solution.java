package greedy.merge_triplets_to_form_target;

public class Solution {

    public boolean mergeTriplets(int[][] triplets, int[] target) {
        boolean x = false;
        boolean y = false;
        boolean z = false;
        for (int[] t : triplets) {
            if (t[0] > target[0] || t[1] > target[1] || t[2] > target[2]) {
                continue;
            }
            x |= t[0] == target[0];
            y |= t[1] == target[1];
            z |= t[2] == target[2];
        }
        return x && y && z;
    }
}

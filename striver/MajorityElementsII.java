import java.util.*;

public class MajorityElementsII {

    public List<Integer> majorityElement(int[] nums) {

        int c1 = 0, c2 = 0, x = Integer.MIN_VALUE, y = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            if (c1 == 0 && y != nums[i]) {
                c1++;
                x = nums[i];
            } else if (c2 == 0 && x != nums[i]) {
                c2++;
                y = nums[i];
            } else if (nums[i] == x) c1++;
            else if (nums[i] == y) c2++;
            else {
                c1--;
                c2--;
            }
        }

        List<Integer> res = new ArrayList<>();

        c1 = 0;
        c2 = 0;

        for (int i = 0; i < nums.length; i++) {
            if (x == nums[i]) c1++;
            if (y == nums[i]) c2++;
        }

        int mini = (int) (nums.length / 3) + 1;
        if (c1 >= mini) res.add(x);
        if (c2 >= mini) res.add(y);

        return res;

    }

}

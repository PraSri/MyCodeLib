package slidingwindow;

import java.util.*;

//Input: nums = [1,2,1,0,4,2,6], k = 3
//
//Output: [2,2,4,4,6]

public class SlidingWindowMaximum {

    // Humein har sliding window ka maximum chahiye.
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] output = new int[n - k + 1];
//        Hum ek deque (double-ended queue) use karte hain jisme sirf potential maximum candidates rakhte hain.
//        Key idea:
//
//Deque mein sirf indices rakhe jaate hain, values nahi.
//
//Deque hamesha values ke decreasing order mein hota hai.
//
//Deque ka front current window ka maximum hota hai.

        //Agar new element aaya aur woh kisi purane element se bada hai,
        //toh woh purana element kabhi window ka maximum nahi ban sakta future mein.
        // toh usko deque se nikaal do.
        Deque<Integer> q = new LinkedList<>();
        int l = 0, r = 0;

        while (r < n) {
            while (!q.isEmpty() && nums[q.getLast()] < nums[r]) {
                q.removeLast();
            }
            q.addLast(r);

            if (l > q.getFirst()) {
                q.removeFirst();
            }

            if ((r + 1) >= k) {
                output[l] = nums[q.getFirst()];
                l++;
            }
            r++;
        }

        return output;
    }
}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoPointers {

    public static void main(String[] args) {
        TwoPointers twoPointers = new TwoPointers();
        String s = "Was it a car or a cat I saw?";
        System.out.println(twoPointers.isPalindrome(s));
    }

    // Valid Palindrome - read question carefully,
    // take 2 mins to read questions
    // highly underrated question, looks easy but can be frustrating
    // of you don't pay attention
    //Input: s = "Was it a car or a cat I saw?"
    //
    //Output: true
    public boolean isPalindrome(String s) {

        int n = s.length();
        int l = 0;
        int r = n - 1;
        s = s.toLowerCase();
        while (l < r) {
            while (l < r && !isAlphaNum(s.charAt(l))) {
                l++;
            }
            while (l < r && !isAlphaNum(s.charAt(r))) {
                r--;
            }
            if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))) {
                return false;
            }
            l++;
            r--;
        }

        return true;
    }

    private boolean isAlphaNum(char c) {
        return (c >= 'A' && c <= 'Z')
                || (c >= 'a' && c <= 'z')
                || (c >= '0' && c <= '9');
    }

    // Two integers sum II
    public int[] twoSum(int[] numbers, int target) {
        int n = numbers.length;
        int l = 0;
        int r = n - 1;
        while (l < r) {
            if (target - numbers[l] == numbers[r]) {
                return new int[]{l + 1, r + 1};
            } else if (target - numbers[l] > numbers[r]) {
                l++;
            } else {
                r--;
            }
        }
        return new int[]{-1, -1};
    }

    // 3Sum
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int l = i + 1;
            int r = n - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum > 0) {
                    r--;
                } else if (sum < 0) {
                    l--;
                } else {
                    result.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    l++;
                    r--;
                    while (l < r && nums[l] == nums[l - 1]) {
                        l++;
                    }
                }
            }
        }
        return result;
    }

    // container with most water
    public int maxArea(int[] heights) {
        int n = heights.length;
        int l = 0;
        int r = n - 1;
        int res = Integer.MIN_VALUE;
        while (l < r) {
            int base = r - l;
            int height = Math.min(heights[l], heights[r]);
            int area = base * height;
            res = Math.max(res, area);
            if (heights[l] < heights[r]) {
                l++;
            } else {
                r--;
            }
        }
        return res;
    }

    // Trapping Rain Water
    public int trap(int[] height) {
        int n = height.length;
        int leftMax, rightMax;
        int res = 0;
        // O(n^2)
        for (int i = 0; i < n; i++) {
            leftMax = height[i];
            rightMax = height[i];
            for (int j = 0; j < i; j++) {
                leftMax = Math.max(leftMax, height[j]);
            }
            for (int j = i + 1; j < n; j++) {
                rightMax = Math.max(rightMax, height[j]);
            }
            res += Math.min(rightMax, leftMax) - height[i];
        }

        // Prefix - suffic array
        int[] leftMaxPre = new int[n];
        leftMaxPre[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMaxPre[i] = Math.max(leftMaxPre[i - 1], height[i]);
        }
        int[] rightMaxSuff = new int[n];
        rightMaxSuff[n - 1] = height[n - 1];
        for (int i = n - 1; i >= 0; i--) {
            rightMaxSuff[i] = Math.max(rightMaxSuff[i + 1], height[i]);
        }
        res = 0;
        for (int i = 0; i < n; i++) {
            res += Math.min(leftMaxPre[i], rightMaxSuff[i]) - height[i];
        }

        // two pointers
        if (n < 2) {
            return 0;
        }

        int l = 0;
        int r = n - 1;
        leftMax = height[l];
        rightMax = height[r];
        int area = 0;

        while (l < r) {
            if (height[l] < height[r]) {
                l++;
                leftMax = Math.max(leftMax, height[l]);
                area += leftMax - height[l];
            } else {
                r--;
                rightMax = Math.max(rightMax, height[r]);
                area += rightMax - height[r];
            }
        }

        res = area;
        return res;
    }
}

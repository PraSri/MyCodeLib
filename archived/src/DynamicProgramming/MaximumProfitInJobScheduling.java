package DynamicProgramming;

import java.util.Arrays;
import java.util.Comparator;

public class MaximumProfitInJobScheduling {

    private static Job[] jobs;
    private static int[] dp;

    public static void main(String[] args) {
        int[] startTime = new int[]{1, 2, 3, 3};
        int[] endTime = new int[]{3, 4, 5, 6};
        int[] profit = new int[]{50, 10, 40, 70};

        int ans = jobScheduling(startTime, endTime, profit);
        System.out.println("ANS : " + ans);
    }

    public static int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        jobs = new Job[n];
        dp = new int[n + 1];
        Arrays.fill(dp, -1);
        for (int i = 0; i < n; i++) {
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
        }
        Arrays.sort(jobs, Comparator.comparingInt(a -> a.startTime));
        return getMaxProfit(0);
    }

    private static int getMaxProfit(int i) {
        if (i >= jobs.length) {
            return 0;
        }

        if (dp[i] != -1) {
            return dp[i];
        }

        int nextElementIdx = binarySearchLowerBound(i);
        int nextProfit = 0;
        if (nextElementIdx != -1) {
            nextProfit = getMaxProfit(nextElementIdx);
        }

        int includeJob = jobs[i].profit + nextProfit;
        int excludeJob = getMaxProfit(i + 1);

        return dp[i] = Math.max(includeJob, excludeJob);

    }

    private static int binarySearchLowerBound(int i) {
        int s = i+1;
        int e = jobs.length - 1;
        int mid, ans = -1;
        while (s <= e) {
            mid = s + (e - s) / 2;
            if (jobs[mid].startTime >= jobs[i].endTime) {
                ans = mid;
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        return ans;
    }

    static int getLowerBound(int idx)
    {
        int low = idx+1;
        int high = jobs.length-1;
        int ans = -1;
        while(low <= high)
        {
            int mid = low + (high-low)/2;
            if(jobs[mid].startTime >= jobs[idx].endTime)
            {
                ans = mid;
                high = mid-1;
            }
            else
                low = mid+1;
        }
        return ans;
    }

    public static class Job {
        public int startTime;
        public int endTime;
        public int profit;

        public Job(int startTime, int endTime, int profit) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.profit = profit;
        }
    }

}

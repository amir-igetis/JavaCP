package leetcodeContest.weeklyContest500;

import java.util.Arrays;

public class MinCostToMoveBetweenIndices {
    public static void main(String[] args) {
        int[] nums = {-5, -2, 3};
        int[][] queries = {{0, 2}, {2, 0}, {1, 2}};
        System.out.println(Arrays.toString(minCost(nums, queries)));
    }

    static int[] minCost(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] close = new int[n];
        for (int i = 0; i < n; i++) {
            if (i == 0)
                close[i] = 1;
            else if (i == n - 1)
                close[i] = n - 2;
            else {
                int left = nums[i] - nums[i - 1];
                int right = nums[i + 1] - nums[i];
                if (left <= right)
                    close[i] = i - 1;
                else close[i] = i + 1;
            }
        }

        int[] forward = new int[n];
        for (int i = 0; i < n - 1; i++) {
            int cost = (close[i] == i + 1) ?
                    1 : (nums[i + 1] - nums[i]);
            forward[i + 1] = forward[i] + cost;
        }

        int[] backward = new int[n];
        for (int i = n - 1; i > 0; i--) {
            int cost = (close[i] == i - 1) ? 1 : (nums[i] - nums[i - 1]);
            backward[i - 1] = backward[i] + cost;
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            if (l < r)
                ans[i] = forward[r] - forward[l];
            else
                ans[i] = backward[r] - backward[l];

        }
        return ans;
    }
}

package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;

public class MinRemovalsToBalancedArr {
    public static void main(String[] args) {
        int[] nums = {2, 1, 5};
        int k = 2;
        System.out.println(minRemoval(nums, k));
    }

    //    sorting and two pointers
//    Time complexity: O(nlogn).
//    Sorting takes O(nlogn) time, and the two-pointer traversal takes O(n) time.
//    Space complexity: O(logn) or O(n).
//    This includes the stack space required by the sorting process, since built-in sorting implementations typically use a variant of quicksort or timsort.
    static int minRemoval(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int ans = n;
        int right = 0;
        for (int left = 0; left < n; left++) {
            while (right < n && nums[right] <=
                    (long) nums[left] * k)
                right++;

            ans = Math.min(ans, n - (right - left));
        }
        return ans;
    }
}

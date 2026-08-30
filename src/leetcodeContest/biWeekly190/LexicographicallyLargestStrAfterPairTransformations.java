package leetcodeContest.biWeekly190;

import java.util.Arrays;

public class LexicographicallyLargestStrAfterPairTransformations {
    public static void main(String[] args) {
        int[] nums = { 2, 5, 7 };
        int[] nums2 = { 3, 9, 1 };
        System.out.println(Arrays.toString(largestString(nums)));
        System.out.println(Arrays.toString(largestString(nums2)));
    }

    static String[] largestString(int[] nums) {
        String[] ans = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            StringBuilder sb = new StringBuilder();
            int bit = 30;
            while (bit >= 0) {
                if ((x & (1 << bit)) != 0)
                    sb.append((char) ('a' + bit));

                bit--;
            }
            ans[i] = sb.toString();
        }
        return ans;
    }
}
package LeetcodeInterview.dp;

public class HouseRobber {
    public static void main(String[] args) {
//
        int[] nums = {1, 2, 3, 1};
        System.out.println(rob(nums));
    }

    static int rob(int[] nums) {
        if (nums.length == 0)
            return 0;
        if (nums.length == 1)
            return nums[0];

        int prev2 = 0, prev1 = 0;
        for (int num : nums) {
            int temp = Math.max(prev1, num + prev2);
            prev2 = prev1;
            prev1 = temp;
        }

        return prev1;
    }
}

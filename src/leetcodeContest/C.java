package leetcodeContest;

public class C {
    public static void main(String[] args) {
        int[] nums = {3, 3, 2, 1};
        System.out.println(minOperations(nums));
    }

    static long minOperations(int[] nums) {
        long ans = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1])
                ans += nums[i] - nums[i + 1];
        }

        return ans;
    }
}

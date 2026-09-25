package leetcodeContest.weekly520;

public class maxPulseValAfterOneSubarrRotation {
    public static void main(String[] args) {
        int[] nums = {1, 5, 2};
        System.out.println(maxValue(nums));
    }

    static long maxValue(int[] nums) {
        int n = nums.length;
        long base = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0)
                base += nums[i];
            else
                base -= nums[i];
        }

        if (n < 2) return base;
        long mgE = helper(nums, 0);
        long mgO = helper(nums, 1);
        long bestGain = Math.max(0, Math.max(mgE, mgO));

        return base + bestGain;
    }

    private static long helper(int[] nums, int startIdx) {
        long maxSoFar = 0;
        long currSum = 0;

        for (int i = startIdx; i + 1 < nums.length; i += 2) {
            long pairGain;
            if (i % 2 == 0)
                pairGain = -2L * nums[i] + 2L * nums[i + 1];
            else
                pairGain = 2L * nums[i] - 2L * nums[i + 1];

            currSum += pairGain;
            if (currSum > maxSoFar)
                maxSoFar = currSum;
            if (currSum < 0)
                currSum = 0;
        }

        return maxSoFar;
    }
}

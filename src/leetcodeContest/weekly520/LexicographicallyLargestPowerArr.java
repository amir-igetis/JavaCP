package leetcodeContest.weekly520;

import java.util.Arrays;

public class LexicographicallyLargestPowerArr {
    public static void main(String[] args) {
        int[] nums = {7, 5};
        System.out.println(maxPulseValue(nums));
    }

    static long maxPulseValue(int[] nums) {
        int n = nums.length;

        // Step 1: Find base pulse value
        long basePulse = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                basePulse += nums[i];
            } else {
                basePulse -= nums[i];
            }
        }

        if (n < 2) return basePulse;

        // Step 2 & 3: Run Kadane's on even and odd alignments
        long maxGainEven = getKadaneGain(nums, 0);
        long maxGainOdd = getKadaneGain(nums, 1);

        // Max possible gain (can be 0 if doing no operation is best)
        long bestGain = Math.max(0, Math.max(maxGainEven, maxGainOdd));

        return basePulse + bestGain;
    }

    // Standard Kadane's Algorithm running across disjoint pairs
    private static long getKadaneGain(int[] nums, int startIdx) {
        long maxGain = 0;
        long currentGain = 0;

        for (int i = startIdx; i + 1 < nums.length; i += 2) {
            long delta;
            if (i % 2 == 0) {
                delta = -2L * nums[i] + 2L * nums[i + 1];
            } else {
                delta = 2L * nums[i] - 2L * nums[i + 1];
            }

            currentGain += delta;

            if (currentGain > maxGain) {
                maxGain = currentGain;
            }

            // Reset if window becomes negative
            if (currentGain < 0) {
                currentGain = 0;
            }
        }

        return maxGain;
    }
}

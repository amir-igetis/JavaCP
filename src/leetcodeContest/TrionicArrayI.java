package leetcodeContest;

public class TrionicArrayI {

    static boolean isTrionic(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return false;
        }

        for (int p = 1; p < n - 1; p++) {
            boolean increasingFirst = true;
            for (int i = 1; i <= p; i++) {
                if (nums[i] <= nums[i - 1]) {
                    increasingFirst = false;
                    break;
                }
            }
            if (!increasingFirst) {
                continue;
            }

            for (int q = p + 1; q < n - 1; q++) {
                boolean decreasingMid = true;
                for (int i = p + 1; i <= q; i++) {
                    if (nums[i] >= nums[i - 1]) {
                        decreasingMid = false;
                        break;
                    }
                }
                if (!decreasingMid) {
                    continue;
                }

                boolean increasingLast = true;
                for (int i = q + 1; i < n; i++) {
                    if (nums[i] <= nums[i - 1]) {
                        increasingLast = false;
                        break;
                    }
                }
                if (increasingLast) {
                    return true;
                }
            }
        }
        return false;
    }
}

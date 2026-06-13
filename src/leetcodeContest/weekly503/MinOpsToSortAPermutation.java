package leetcodeContest.weekly503;

public class MinOpsToSortAPermutation {
    public static void main(String[] args) {
        int[] nums = {0, 2, 1};
        System.out.println(minOperations(nums));
    }

    static int minOperations(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;
        int idx = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                idx = i;
                break;
            }
        }
        boolean isIncreasing = true;
        for (int i = 0; i < n; i++) {
            if (nums[(idx + i) % n] != i) {
                isIncreasing = false;
                break;
            }
        }
        boolean isDecreasing = true;
        for (int i = 0; i < n; i++) {
            if (nums[(idx - i + n) % n] != i) {
                isDecreasing = false;
                break;
            }
        }

        int minOps = Integer.MAX_VALUE;

        if (isIncreasing) {
            int costLeftShifts = idx;
            int costRightShifts = (idx == 0) ? 0 : (n - idx + 2);
            minOps = Math.min(minOps, Math.min(costLeftShifts, costRightShifts));
        }
        if (isDecreasing) {
            int costShiftThenReverse = idx + 2;
            int costReverseThenShift = n - idx;
            minOps = Math.min(minOps, Math.min(costShiftThenReverse, costReverseThenShift));
        }

        return minOps == Integer.MAX_VALUE ? -1 : minOps;
    }
}

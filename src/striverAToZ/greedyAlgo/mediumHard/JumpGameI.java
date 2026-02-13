package striverAToZ.greedyAlgo.mediumHard;

public class JumpGameI {
    public static void main(String[] args) {
        int[] nums = {4, 2, 7, 1, 2};
        System.out.println(canJump(nums));
    }

    static boolean canJump(int[] nums) {
        int maxIdx = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > maxIdx)
                return false;
            maxIdx = Math.max(maxIdx, i + nums[i]);
        }
        return true;
    }
}

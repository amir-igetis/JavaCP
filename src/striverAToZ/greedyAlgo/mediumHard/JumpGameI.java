package striverAToZ.greedyAlgo.mediumHard;

public class JumpGameI {
    public static void main(String[] args) {
        int[] nums = {4, 2, 7, 1, 2};
        System.out.println(canJump(nums));
    }

    ///  Question 1
    /// You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length from that position.
    ///
    /// Return true if you can reach the last index of the array, or false if you cannot.
    /// 
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

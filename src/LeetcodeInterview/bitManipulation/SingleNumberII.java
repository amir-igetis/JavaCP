package LeetcodeInterview.bitManipulation;

public class SingleNumberII {
    public static void main(String[] args) {
//
        int[] nums = {2, 2, 3, 2};
        System.out.println(singleNumber(nums));
    }

    static int singleNumber(int[] nums) {
        int ones = 0, twos = 0;
        for (int i : nums) {
            ones = (ones ^ i) & ~twos;
            twos = (twos ^ i) & ~ones;
        }

        return ones;
    }
}

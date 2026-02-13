package LeetcodeInterview.bitManipulation;

public class SingleNumber {
    public static void main(String[] args) {
//
        int[] nums = {2, 2, 1};
        System.out.println(singleNumber(nums));
    }

    static int singleNumber(int[] nums) {
        int res = 0;
        for (int i : nums) {
            res ^= i;
        }
        return res;
    }
}

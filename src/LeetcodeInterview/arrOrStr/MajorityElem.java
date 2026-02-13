package LeetcodeInterview.arrOrStr;

public class MajorityElem {
    public static void main(String[] args) {

        int[] nums = {2, 2, 1, 1, 1, 2, 2};
        System.out.println(majorityElement(nums));
    }

    static int majorityElement(int[] nums) {
        int majElem = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                majElem = nums[i];
                count = 1;
            } else if (nums[i] == majElem) {
                count++;
            } else count--;
        }
        return majElem;
    }
}

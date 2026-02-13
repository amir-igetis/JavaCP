package LeetcodeInterview.arrOrStr;

public class RemoveElem {
    public static void main(String[] args) {

        int[] nums = {3, 2, 2, 3};
        int val = 3;
        System.out.println(removeElement(nums, val));
    }

    static int removeElement(int[] nums, int val) {
        int newIdx = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[newIdx] = nums[i];
                newIdx++;
            }
        }
        return newIdx;
    }
}

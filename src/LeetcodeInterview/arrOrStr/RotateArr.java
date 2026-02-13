package LeetcodeInterview.arrOrStr;

public class RotateArr {
    public static void main(String[] args) {
//        soln for

        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        rotate(nums, k);
    }

    static void rotate(int[] nums, int k) {
        k = k % nums.length;
        reverseHelp(nums, 0, nums.length - 1);
        reverseHelp(nums, 0, k - 1);
        reverseHelp(nums, k, nums.length - 1);
    }

    private static void reverseHelp(int[] nums, int l, int r) {
        while (l <= r) {
            int temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
            l++;
            r--;
        }
    }
}

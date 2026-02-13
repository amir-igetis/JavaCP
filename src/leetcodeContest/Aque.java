package leetcodeContest;

public class Aque {
    public static void main(String[] args) {
//        int[] nums = {5, 2, 5, 4, 5};
//        int k = 2;

        int[] nums = {2, 1, 2};
        int k = 2;

//        int[] nums = {9, 7, 5, 3};
//        int k = 1;

        System.out.println(minOperations(nums, k));
    }

    static int minOperations(int[] nums, int k) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max)
                max = nums[i];
        }

        if (k >= max) return -1;

        int ops = 0;
        while (max > k) {
            int newMax = k;
            for (int i : nums) {
                if (i > k && i < max)
                    newMax = Math.max(newMax, i);
            }

            for (int i = 0; i < nums.length; i++) {
                if (nums[i] > max)
                    nums[i] = max;
            }
            max = newMax;
            ops++;
        }
        return ops;
    }
}

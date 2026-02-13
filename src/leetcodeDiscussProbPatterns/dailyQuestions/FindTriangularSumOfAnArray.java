package leetcodeDiscussProbPatterns.dailyQuestions;

public class FindTriangularSumOfAnArray {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        System.out.println(triangularSumI(nums));
    }

    static int triangularSum(int[] nums) {
        int n = nums.length;
        while (n > 1) {
            for (int i = 0; i < n - 1; i++) {
                nums[i] = (nums[i] + nums[i + 1]) % 10;
            }
            n--;
        }
        return nums[0];
    }

//    using binomial coefficients

    // not working
    static int triangularSumI(int[] nums) {
        int n = nums.length;
        int res = 0;
        long coeff = 1;
        for (int i = 0; i < n; i++) {
            res = (int) ((res + coeff * nums[i]) % 10);
            coeff = coeff * (n - 1 - i) / (i + 1);

        }
        return res;
    }

//    using recursion

    static int triangularSumII(int[] nums) {
        return helper(nums, nums.length);
    }

    private static int helper(int[] nums, int n) {
        if (n == 1) return nums[0];
        for (int i = 0; i < n - 1; i++) {
            nums[i] = (nums[i] + nums[i + 1]) % 10;
        }
        return helper(nums, n - 1);
    }
}

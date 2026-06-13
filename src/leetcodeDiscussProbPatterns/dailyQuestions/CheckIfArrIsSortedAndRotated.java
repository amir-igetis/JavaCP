package leetcodeDiscussProbPatterns.dailyQuestions;

public class CheckIfArrIsSortedAndRotated {
    public static void main(String[] args) {
        int[] nums = {3, 4, 5, 1, 2};
        System.out.println(check(nums));
    }

    static boolean check(int[] nums) {
        int n = nums.length;
        int f = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] > nums[(i + 1) % n]) {
                f++;
            }
        }
        if (f > 1)
            return false;
        return true;
    }
}


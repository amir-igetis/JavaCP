package leetcodeDiscussProbPatterns.dailyQuestions;

public class TrionicArr {
    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 4, 2, 6};
        System.out.println(isTrionicI(nums));
    }

    //    Evaluating the Validity of the Boundaries tc O(n) & sc O(1)
    static boolean isTrionic(int[] nums) {
        int n = nums.length;
        int i = 1;
        while (i < n && nums[i - 1] < nums[i])
            i++;
        int p = i - 1;
        while (i < n && nums[i - 1] > nums[i])
            i++;
        int q = i - 1;
        while (i < n && nums[i - 1] < nums[i])
            i++;
        int flag = i - 1;
        return (p != 0) && (q != p) && (flag == n - 1 && flag != q);

    }

    //    Counting the Number of Turning Points tc O(n) & sc O(1)
    static boolean isTrionicI(int[] nums) {
        int n = nums.length;
        if (nums[0] >= nums[1]) {
            return false;
        }
        int count = 1;
        for (int i = 2; i < n; i++) {
            if (nums[i - 1] == nums[i]) {
                return false;
            }
            if ((nums[i - 2] - nums[i - 1]) * (nums[i - 1] - nums[i]) < 0) {
                count++;
            }
        }
        return count == 3;
    }

}
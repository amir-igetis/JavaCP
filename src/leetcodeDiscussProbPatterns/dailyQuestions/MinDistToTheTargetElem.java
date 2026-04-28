package leetcodeDiscussProbPatterns.dailyQuestions;

public class MinDistToTheTargetElem {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        int target = 5, start = 3;
        System.out.println(getMinDistance(nums, target, start));
    }

    static int getMinDistance(int[] nums, int target, int start) {
        int res = nums.length;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target)
                res = Math.min(res, Math.abs(i - start));
        }
        return res;
    }
}

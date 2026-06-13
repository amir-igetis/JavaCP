package leetcodeDiscussProbPatterns.dailyQuestions;

public class MaxTotalSubarrValI {
    public static void main(String[] args) {
        int[] nums = {1, 3, 2};
        int k = 2;
        System.out.println(maxTotalValue(nums, k));
    }

    // greedy tc O(n) and sc O(1)
    static long maxTotalValue(int[] nums, int k) {
        int m1 = Integer.MAX_VALUE;
        int m2 = Integer.MIN_VALUE;
        for (int x : nums) {
            m1 = Math.min(m1, x);
            m2 = Math.max(m2, x);
        }
        return (long) (m2 - m1) * k;
    }

}

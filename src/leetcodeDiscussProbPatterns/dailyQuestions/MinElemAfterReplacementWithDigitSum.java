package leetcodeDiscussProbPatterns.dailyQuestions;

public class MinElemAfterReplacementWithDigitSum {
    public static void main(String[] args) {
        int[] nums = {10, 12, 13, 14};
        System.out.println(minElement(nums));
    }

    // math tc O(nlogd) & sc O(1)
    static int minElement(int[] nums) {
        int ans = 37;
        for (int num : nums) {
            int dig = 0;
            while (num > 0) {
                dig += num % 10;
                num /= 10;
            }
            ans = Math.min(ans, dig);
        }
        return ans;
    }
}

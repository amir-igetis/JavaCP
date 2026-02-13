package leetcodeDiscussProbPatterns.dailyQuestions;

public class MinOpsToMakeArrSumDivisibleByK {
    public static void main(String[] args) {
        int[] nums = {3, 9, 7};
        int k = 5;
        System.out.println(minOperations(nums, k));
    }

    static int minOperations(int[] nums, int k) {
        long sum = 0;
        for (int x : nums)
            sum += x;

        int rem = (int) sum % k;

        return rem;
    }
}

package leetcodeDiscussProbPatterns.dailyQuestions;

public class CountPartitionsWithEvenSumDiff {
    public static void main(String[] args) {
        int[] nums = {10, 10, 3, 7, 6};
        System.out.println(countPartitions(nums));
    }

    static int countPartitions(int[] nums) {
        int totalSum = 0;
        for (int x : nums) {
            totalSum += x;
        }
        return totalSum % 2 == 0 ? nums.length - 1 : 0;
    }
}

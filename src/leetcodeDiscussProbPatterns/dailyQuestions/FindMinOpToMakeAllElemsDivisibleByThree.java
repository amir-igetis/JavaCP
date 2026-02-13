package leetcodeDiscussProbPatterns.dailyQuestions;

public class FindMinOpToMakeAllElemsDivisibleByThree {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        System.out.println(minimumOperations(nums));
    }


    static int minimumOperations(int[] nums) {
        int ops = 0;
        for (int x : nums) {
            if (x % 3 != 0)
                ops += 1;
        }

        return ops;
    }

}

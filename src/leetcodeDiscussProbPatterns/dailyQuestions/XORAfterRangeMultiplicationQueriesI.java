package leetcodeDiscussProbPatterns.dailyQuestions;

public class XORAfterRangeMultiplicationQueriesI {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1};
        int[][] queries = {{0, 2, 1, 4}};
        System.out.println(xorAfterQueries(nums, queries));
    }

    private static final int MOD = 1_000_000_007;

    static int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length;
        for (int[] q : queries) {
            int l = q[0];
            int r = q[1];
            int k = q[2];
            int v = q[3];
            for (int i = l; i <= r; i += k)
                nums[i] = (int) (((long) nums[i] * v) % MOD);
        }
        int res = 0;
        for (int i : nums)
            res ^= i;

        return res;
    }
}

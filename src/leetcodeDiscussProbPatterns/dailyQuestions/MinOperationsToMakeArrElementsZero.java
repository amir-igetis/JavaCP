package leetcodeDiscussProbPatterns.dailyQuestions;

public class MinOperationsToMakeArrElementsZero {
    public static void main(String[] args) {
        int[][] queries = {{1, 2}, {2, 4}};
        System.out.println(minOperations(queries));
    }

    static long minOperations(int[][] queries) {
        long ans = 0;
        for (int[] query : queries) {
            int l = query[0];
            int r = query[1];
            long opsRange = getOperations(r) - getOperations(l - 1);
            ans += (opsRange + 1) / 2;
        }

        return ans;
    }

    private static long getOperations(int n) {
        if (n <= 0)
            return 0;
        long res = 0;
        int ops = 0;
        for (long powerOfFour = 1; powerOfFour <= n; powerOfFour *= 4) {
            ops++;
            long l = powerOfFour;
            long r = Math.min(n, powerOfFour * 4 - 1);
            res += (r - l + 1) * ops;
        }
        return res;
    }
}

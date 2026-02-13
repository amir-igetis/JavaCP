package leetcodeDiscussProbPatterns.dailyQuestions;

public class NumOfWaysToPaintNxThreeGrid {
    public static void main(String[] args) {
        int n = 5000;
        System.out.println(numOfWays(n));
    }

    static int numOfWays(int n) {
        final int MOD = (int) 1e9 + 7;
        long a = 6, b = 6;
        for (int i = 2; i <= n; i++) {
            long newA = (2 * a + 2 * b) % MOD;
            long newB = (2 * a + 3 * b) % MOD;
            a = newA;
            b = newB;
        }
        return (int) ((a + b) % MOD);
    }
}

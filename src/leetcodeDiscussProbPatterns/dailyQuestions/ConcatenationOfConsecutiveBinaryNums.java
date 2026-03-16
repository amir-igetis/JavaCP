package leetcodeDiscussProbPatterns.dailyQuestions;

public class ConcatenationOfConsecutiveBinaryNums {
    public static void main(String[] args) {
        int n = 1;
        System.out.println(concatenatedBinary(n));
    }

    static int concatenatedBinary(int n) {
        final int MOD = 1_000_000_007;
        long res = 0;
        int bitLen = 0;
        for (int i = 1; i <= n; i++) {
            if ((i & (i - 1)) == 0)
                bitLen++;

            res = ((res << bitLen) + i) % MOD;
        }
        return (int) res;
    }
}

package leetcodeContest.weekly517;

public class SumOfDecodedNums {

    public static void main(String[] args) {

        long[] nums = { 2522, 2101 };

        System.out.println(sumDecoded(nums));
    }

    static int sumDecoded(long[] nums) {

        final long MOD = 1_000_000_007L;
        long sum = 0;

        for (long num : nums) {
            long width = num % 10;
            long rem = num / 10;
            int dig = 0;
            long temp = rem;

            while (temp > 0) {
                dig++;
                temp /= 10;
            }
            long pow = 1;

            for (int i = 0; i < dig - width; i++)
                pow *= 10;

            long x = rem / pow;
            long y = rem % pow;
            long decoded = power(x, y, MOD);

            sum = (sum + decoded) % MOD;
        }

        return (int) sum;
    }

    private static long power(long x, long y, long MOD) {

        long res = 1;
        x %= MOD;

        while (y > 0) {
            if (y % 2 == 1)
                res = (res * x) % MOD;

            x = (x * x) % MOD;
            y /= 2;
        }

        return res;
    }
}
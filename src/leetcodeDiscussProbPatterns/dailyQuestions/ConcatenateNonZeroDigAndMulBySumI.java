package leetcodeDiscussProbPatterns.dailyQuestions;

public class ConcatenateNonZeroDigAndMulBySumI {
    public static void main(String[] args) {
        int n = 10203004;
        System.out.println(sumAndMultiplyI(n));
        System.out.println(sumAndMultiplyI(n));
    }

    //    left to right tc & sc O(logn)
    static long sumAndMultiplyI(int n) {
        long x = 0;
        long sum = 0;
        String s = String.valueOf(n);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int d = c - '0';
            sum += d;
            if (d > 0) {
                x = x * 10 + d;
            }
        }
        return x * sum;

    }

    //    right to left tc O(logn) & sc O(1)
    static long sumAndMultiply(int n) {
        long x = 0;
        long sum = 0;
        long pow10 = 1;
        while (n > 0) {
            int d = n % 10;
            sum += d;
            if (d > 0) {
                x += d * pow10;
                pow10 *= 10;
            }
            n /= 10;
        }
        return x * sum;
    }
}

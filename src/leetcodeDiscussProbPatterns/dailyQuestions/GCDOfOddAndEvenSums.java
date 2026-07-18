package leetcodeDiscussProbPatterns.dailyQuestions;

public class GCDOfOddAndEvenSums {
    public static void main(String[] args) {
        int n = 4;
        System.out.println(gcdOfOddEvenSums(n));
    }

    static int gcdOfOddEvenSums(int n) {

        int sumOdd = 0;
        int sumEven = 0;

        for (int i = 1; i <= n; i++) {
            sumOdd += (2 * i - 1);
            sumEven += (2 * i);
        }

        return gcd(sumOdd, sumEven);
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

}

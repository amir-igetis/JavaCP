package LeetcodeInterview.math;

public class FactorialTrailingZeroes {
    public static void main(String[] args) {
//
        int n = 3;
        System.out.println(trailingZeroes(n));
    }

    static int trailingZeroes(int n) {
        int count = 0;
        while (n >= 5) {
            count += n / 5;
            n /= 5;
        }
        return count;
    }
}

package LeetcodeInterview.math;

public class PowXN {
    public static void main(String[] args) {
//
        double x = 2.00000;
        int n = 10;
        System.out.println(myPow(x, n));
    }

    //    Recursive
    static double myPowI(double x, int n) {
        if (n == 0)
            return 1;
        if (n < 0) {
            x = 1 / x;
            n = -n;
        }
        double half = myPowI(x, n / 2);
        return (n % 2 == 0) ?
                half * half : half * half * x;
    }

    //    Iterative
    static double myPow(double x, int n) {
        if (n == 0)
            return 1;
        long exp = n;
        if (exp < 0) {
            x = 1 / x;
            exp = -exp;
        }

        double res = 1.0;
        while (exp > 0) {
            if (exp % 2 == 1)
                res *= x;
            x *= x;
            exp /= 2;
        }
        return res;
    }
}

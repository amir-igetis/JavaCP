package leetcodeDiscussProbPatterns.dailyQuestions;

public class CountSquareSumTriples {
    public static void main(String[] args) {
        int n = 5;
        System.out.println(countTriples(n));
    }

    static int countTriples(int n) {
        int res = 0;
        for (int u = 2; u * u <= n; u++) {
            for (int v = 1; v < u; v++) {
                if (((u - v) & 1) == 0 || gcd(u, v) != 1) continue;
                int c = u * u + v * v;
                if (c > n) continue;

                res += 2 * (n / c);
            }
        }
        return res;
    }

    static int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }
}

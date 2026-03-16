package leetcodeDiscussProbPatterns.dailyQuestions;

public class BinaryGap {
    public static void main(String[] args) {
        int n = 22;
        System.out.println(binaryGap(n));
    }

    static int binaryGap(int n) {
        int res = 0;
        for (int i = -32; n > 0; n /= 2, i++) {
            if (n % 2 == 1) {
                res = Math.max(res, i);
                i = 0;
            }

        }
        return res;

    }
}
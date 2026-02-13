package leetcodeDiscussProbPatterns.dailyQuestions;

public class MinOneBitOpsToMakeIntZero {
    public static void main(String[] args) {
        int n = 3;
        System.out.println(minimumOneBitOperationsI(n));
    }

    static int minimumOneBitOperations(int n) {
        if (n == 0)
            return 0;

//        find position of most significant bit
        int msb = 31 - Integer.numberOfLeadingZeros(n);
//        (1 << msb) flips all bits below and including msb
        return (1 << (msb + 1)) - 1 - minimumOneBitOperations(n ^ (1 << msb));
    }


    static int minimumOneBitOperationsI(int n) {
        return minimumOneBitOp(n, 0);
    }

    private static int minimumOneBitOp(int n, int res) {
        if (n == 0) return res;
        int b = 1;
        while ((b << 1) <= n)
            b = b << 1;
        return minimumOneBitOp((b >> 1) ^ b ^ n, res + b);
    }
}

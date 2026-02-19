package leetcodeDiscussProbPatterns.dailyQuestions;

public class BinaryNumberWithAlternativeBits {
    public static void main(String[] args) {
        int n = 5;
        System.out.println(hasAlternatingBits(n));
    }

    static boolean hasAlternatingBits(int n) {
        n = n ^ (n >> 1);
        return (n & n + 1) == 0;
    }
}

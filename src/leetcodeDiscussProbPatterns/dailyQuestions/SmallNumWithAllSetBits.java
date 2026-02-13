package leetcodeDiscussProbPatterns.dailyQuestions;

public class SmallNumWithAllSetBits {
    public static void main(String[] args) {
        int n = 5;
        System.out.println(smallestNumber(n));
    }

    static int smallestNumber(int n) {
        int x = 1;
        while (x <= n)
            x <<= 1;

        return x - 1;

    }
}

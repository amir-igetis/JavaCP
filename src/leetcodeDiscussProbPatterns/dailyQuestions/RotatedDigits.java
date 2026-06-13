package leetcodeDiscussProbPatterns.dailyQuestions;

public class RotatedDigits {
    public static void main(String[] args) {
        int n = 10;
        System.out.println(rotatedDigits(n));
    }

    static int rotatedDigits(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++)
            if (isGood(i))
                count++;


        return count;
    }

    private static boolean isGood(int num) {
        boolean hasChanged = false;
        while (num > 0) {
            int digit = num % 10;
            if (digit == 3 || digit == 4 || digit == 7)
                return false;

            if (digit == 2 || digit == 5 || digit == 6 || digit == 9)
                hasChanged = true;

            num /= 10;
        }
        return hasChanged;
    }

}

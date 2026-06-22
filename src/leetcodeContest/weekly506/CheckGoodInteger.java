package leetcodeContest.weekly506;

public class CheckGoodInteger {

    public static void main(String[] args) {
        int n = 5;
        System.out.println(checkGoodInteger(n));
    }

    static boolean checkGoodInteger(int n) {
        int squareSum = 0;
        int digitSum = 0;
        while (n > 0) {
            int digit = n % 10;
            digitSum += digit;
            squareSum += digit * digit;
            n /= 10;
        }

        return (squareSum - digitSum) >= 50;
    }
}
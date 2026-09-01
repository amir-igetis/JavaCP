package striverAToZ.bitManipulation.learnBitManipulation;

public class DivideTwoIntWithoutUsingMulDivMod {

    /// Question 8
    ///
    /// Problem Statement: Given the two integers, dividend and divisor. Divide without using the mod, division, or multiplication operators and return the quotient.
    ///
    /// The fractional portion of the integer division should be lost as it truncates toward zero.
    ///
    /// As an illustration, 8.345 and -2.7335 would be reduced to 8 and -2 respectively.
    ///
    /// Note: Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range: (−231, 231 − 1). For this problem, if the quotient is strictly greater than 231 - 1, then return 231 - 1, and if the quotient is strictly less than -231, then return -231.
    public static void main(String[] args) {
        int dividend = 10, divisor = 3;
        int ans = divide(dividend, divisor);

        System.out.println("The result of dividing " + dividend + " and " + divisor + " is " + ans);
    }


    // brute

    /// Time Complexity: O(dividend) In the worst case when the divisor is 1, the number of iterations taken will be O(dividend).
    /// Space Complexity: O(1) Using a couple of variables i.e., constant space.
    static int divide(int dividend, int divisor) {

        // Base case
        if (dividend == divisor) return 1;
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
        if (divisor == 1) return dividend;

        // Variable to store the sign of result
        boolean isPositive = true;

        // Updating the sign of quotient
        if (dividend >= 0 && divisor < 0)
            isPositive = false;
        else if (dividend < 0 && divisor > 0)
            isPositive = false;

        // Storing absolute dividend & divisor
        long n = dividend;
        long d = divisor;

        n = Math.abs(n);
        d = Math.abs(d);

        // Variable to store the answer and sum
        long ans = 0, sum = 0;

        /* Looping while sum added to divisor is
        less than or equal to divisor */
        while (sum + d <= n) {

            // Increment the count
            ans++;
            // Update the sum
            sum += d;
        }

        // Handling overflowing condition
        if (ans > Integer.MAX_VALUE && isPositive)
            return Integer.MAX_VALUE;
        if (ans > Integer.MAX_VALUE && !isPositive)
            return Integer.MIN_VALUE;

        /* Returning the quotient
        with proper sign */
        return isPositive ? (int) ans : (int) (-1 * ans);
    }

    // optimal

    /// Time Complexity: O((logN)^2) – (where N is the absolute value of dividend). The outer loop runs for O(logN) times. The inner loop runs for O(logN) (approx.) times as well.
    /// Space Complexity: O(1) – Using a couple of variables i.e., constant space.
    // Importing required package
    static int divideI(int dividend, int divisor) {

        // Base case
        if (dividend == divisor) return 1;
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
        if (divisor == 1) return dividend;

        // Variable to store the sign of result
        boolean isPositive = true;

        // Updating the sign of quotient
        if (dividend >= 0 && divisor < 0)
            isPositive = false;
        else if (dividend < 0 && divisor > 0)
            isPositive = false;

        // Storing absolute dividend & divisor
        long n = dividend;
        long d = divisor;

        n = Math.abs(n);
        d = Math.abs(d);

        // Variable to store the answer and sum
        long ans = 0, sum = 0;

        /* Looping while sum added to divisor is
        less than or equal to dividend */
        while (sum + d <= n) {

            // Increment the count
            ans++;
            // Update the sum
            sum += d;
        }

        // Handling overflowing condition
        if (ans > Integer.MAX_VALUE && isPositive)
            return Integer.MAX_VALUE;
        if (ans > Integer.MAX_VALUE && !isPositive)
            return Integer.MIN_VALUE;

        /* Returning the quotient
        with proper sign */
        return isPositive ? (int) ans : (int) (-1 * ans);
    }
}
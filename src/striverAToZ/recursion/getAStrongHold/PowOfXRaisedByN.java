package striverAToZ.recursion.getAStrongHold;

public class PowOfXRaisedByN {

    /// Problem Statement: Implement the power function pow(x, n) , which calculates the x raised to n i.e. x^n.


    public static void main(String[] args) {
        // Output: 1024.0000
        System.out.printf("%.4f\n", myPow(2.0000, 10));
        // Output: 0.2500
        System.out.printf("%.4f\n", myPow(2.0000, -2));

    }

    /// Time Complexity: O(n), where n is the absolute value of the exponent. This is because we multiply the base x, n times.
    ///
    /// Space Complexity: O(1), as we are using a constant amount of space for the variables used in the computation.
    // Brute force
    static double myPow(double x, int n) {
        // Base case: any number to the power of 0 is 1
        if (n == 0 || x == 1.0) return 1;

        long temp = n; // to avoid integer overflow

        // Handle negative exponents
        if (n < 0) {
            x = 1 / x;
            temp = -1L * n;
        }

        double ans = 1;

        for (long i = 0; i < temp; i++) {
            // Multiply ans by x for n times
            ans *= x;
        }
        return ans;
    }

    /// Time Complexity: O(log n), where n is the absolute value of the exponent. This is because we reduce the problem size by half in each recursive call when n is even.
    ///
    /// Space Complexity: O(log n), due to the recursive call stack. In the worst case, the depth of the recursion can go up to log(n) when n is even.

    // optimal

    // Public method to handle negative exponents as well
    static double myPowI(double x, int n) {
        // If 'n' is negative, take reciprocal of positive exponent result
        if (n < 0) {
            return 1.0 / power(x, -n);
        }
        // If 'n' is non-negative
        return power(x, n);
    }

    private static double power(double x, long n) {
        // Base case: anything raised to 0 is 1
        if (n == 0) return 1.0;

        // Base case: anything raised to 1 is itself
        if (n == 1) return x;

        // If 'n' is even
        if (n % 2 == 0) {
            // Recursive call: square the base and halve the exponent
            return power(x * x, n / 2);
        }

        // If 'n' is odd
        // Recursive call: multiply base once and reduce exponent by 1
        return x * power(x, n - 1);
    }
}

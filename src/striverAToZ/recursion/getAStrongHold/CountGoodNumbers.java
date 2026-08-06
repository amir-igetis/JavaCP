package striverAToZ.recursion.getAStrongHold;

public class CountGoodNumbers {

    /// Problem Statement: A digit string is considered good if the digits at even indices (0-based) are even digits (0, 2, 4, 6, 8) and the digits at odd indices are prime digits (2, 3, 5, 7).
    ///
    /// Given an integer n, return the total number of good digit strings of length n. As the result may be large, return it modulo 109 + 7.
    ///
    /// A digit string is a string consisting only of the digits '0' through '9'. It may contain leading zeros.

    public static void main(String[] args) {
        int n = 1;
        System.out.println(countGoodNumbers(0, n));

    }


    /// Time Complexity (TC): O(2^n) because at each index, we check 5 even digits for even indices and 4 prime digits for odd indices, leading to exponential recursion.
    ///
    /// Space Complexity (SC): O(n) due to the recursion depth, where each function call adds to the call stack.
    static final int MOD = 1_000_000_007;

    // Recursive function to count good numbers
    static int countGoodNumbers(int index, int n) {
        // Base case
        if (index == n) {
            return 1;
        }

        int result = 0;
        // Even index
        if (index % 2 == 0) {
            int[] evenDigits = {0, 2, 4, 6, 8};
            for (int digit : evenDigits) {
                result = (result + countGoodNumbers(index + 1, n)) % MOD;
            }
        }
        // Odd index
        else {
            int[] primeDigits = {2, 3, 5, 7};
            for (int digit : primeDigits) {
                result = (result + countGoodNumbers(index + 1, n)) % MOD;
            }
        }
        return result;
    }
}
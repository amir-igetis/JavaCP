package striverAToZ.bitManipulation.learnBitManipulation;

public class CheckIfANumIsPowOfTwoOrNot {

    /// Question 4
    /// Problem Statement: Given an integer n, return true if it is a power of two. Otherwise, return false. An integer n is a power of two if there exists an integer x such that n == 2ˣ.
    public static void main(String[] args) {
        int num = 8;

        if (isPowerOfTwo(num)) {
            System.out.println(num + " is a power of two.");
        } else {
            System.out.println(num + " is not a power of two.");
        }
    }

    /// Time Complexity: O(1), because bitwise operations take constant time.
    ///
    /// Space Complexity: O(1), no extra space used.
    static boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;  // Check if n is greater than 0 and has only one bit set
    }
}

package striverAToZ.bitManipulation.learnBitManipulation;

public class CheckIfANumIsOddOrNot {

    /// Question 3
    /// Problem Statement: Given a non-negative integer n, determine whether it is odd. Return true if the number is odd, otherwise return false. A number is odd if it is not divisible by 2 (i.e., n % 2 != 0).
    public static void main(String[] args) {
        int num = 7;

        if (isOdd(num)) {
            System.out.println(num + " is odd.");
        } else {
            System.out.println(num + " is not odd.");
        }
    }

    /// Time Complexity: O(1) — The modulus operation takes constant time.
    ///
    /// Space Complexity: O(1) — No extra space is required.
    static boolean isOdd(int n) {
        return n % 2 != 0;  // Return true if the number is odd, else false
    }
}

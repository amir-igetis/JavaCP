package striverAToZ.bitManipulation.learnBitManipulation;

public class CountTheNumberOfSetBits {

    /// Question 5
    ///
    /// Problem Statement: Given an integer n, return the number of set bits (1s) in its binary representation.
    /// Can you solve it in O(log n) time complexity?
    public static void main(String[] args) {
        int n = 29;
        int result = countSetBitsI(n);

        System.out.println("The number of set bits is: " + result);

    }

    // brute

    /// Time Complexity: O(log n), because each bit of the integer is checked once.
    ///
    /// Space Complexity: O(1), only a few variables are used.
    // Solution class containing the countSetBits function
    static int countSetBits(int n) {
        int count = 0;  // Variable to store the count of set bits

        // Step 1: Count the number of set bits using bitwise operations
        while (n > 0) {
            count += (n & 1);  // Check if the least significant bit is set (1)
            n >>= 1;  // Right shift n by 1 to process the next bit
        }

        // Step 2: Return the count of set bits
        return count;
    }

    // optimal

    /// Time Complexity: O(k), where k is the number of set bits (often faster than checking all bits).
    ///
    /// Space Complexity: O(1), only a few variables are used.
    // Solution class containing the countSetBits function
    static int countSetBitsI(int n) {
        int count = 0;  // Variable to store the count of set bits

        // Step 1: While n is non-zero, turn off the rightmost set bit
        while (n > 0) {
            n &= (n - 1);  // Turn off the rightmost set bit
            count++;  // Increment the count
        }

        // Step 2: Return the count of set bits
        return count;
    }

}

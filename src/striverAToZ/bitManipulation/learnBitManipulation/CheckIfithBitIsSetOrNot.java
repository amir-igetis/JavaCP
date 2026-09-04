package striverAToZ.bitManipulation.learnBitManipulation;

public class CheckIfithBitIsSetOrNot {

    ///  Question 2
    ///
    /// Problem Statement: Given two integers n and i, return true if the ith bit in the binary representation of n (counting from the least significant bit, 0-indexed) is set (i.e., equal to 1). Otherwise, return false.

    public static void main(String[] args) {
        int num = 5;  // Binary: 101
        int bitIndex = 2;  // Check the 2nd bit (0-based index)

        if (checkIthBitI(num, bitIndex)) {
            System.out.println("The " + bitIndex + "-th bit of " + num + " is set (1).");
        } else {
            System.out.println("The " + bitIndex + "-th bit of " + num + " is not set (0).");
        }
    }

    // brute

    /// Time Complexity: O(log n), due to integer-to-binary conversion and indexing.
    ///
    /// Space Complexity: O(log n), for the binary string.
    // Solution class containing the checkIthBit function
    static boolean checkIthBit(int n, int i) {
        String binary = Integer.toBinaryString(n);  // Convert the number into binary string representation

        // If the bit index is greater than the length of the binary string, the bit is 0
        if (i >= binary.length()) return false;

        // Return true if the i-th bit is 1, otherwise false
        return binary.charAt(binary.length() - 1 - i) == '1';
    }

    // optimal

    /// Time Complexity: O(1), constant time bitwise operation.
    ///
    /// Space Complexity: O(1), no additional space used.
    static boolean checkIthBitI(int n, int i) {
        // Check if the i-th bit is set using bitwise AND operation
        return (n & (1 << i)) != 0;  // If the i-th bit is 1, the result will be non-zero
    }
}

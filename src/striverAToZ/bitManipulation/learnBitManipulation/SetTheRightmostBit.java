package striverAToZ.bitManipulation.learnBitManipulation;

public class SetTheRightmostBit {

    /// Question 6
    ///
    /// Problem Statement: Given a positive integer n, set the rightmost unset (0) bit of its binary representation to 1 and return the resulting integer.
    /// If all bits are already set, return the number as it is.
    public static void main(String[] args) {
        int n = 10; // binary: 1010

        // Call function
        int result = setRightmostUnsetBit(n);

        // Print output
        System.out.println("Number after setting rightmost unset bit: " + result); // Output: 11

    }

    /// Time Complexity: O(1) since only one bitwise operation is performed.
    ///
    /// Space Complexity: O(1) since no extra space is used.
    static int setRightmostUnsetBit(int n) {
        // OR with n+1 sets the rightmost 0 to 1
        return n | (n + 1);
    }
}

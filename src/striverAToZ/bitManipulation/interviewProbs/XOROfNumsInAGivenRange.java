package striverAToZ.bitManipulation.interviewProbs;

public class XOROfNumsInAGivenRange {

    /// Question 4
    ///
    /// Problem Statement: Given two integers L and R. Find the XOR of the elements in the range (L, R).
    public static void main(String[] args) {
        int l = 3, r = 5;
        int ans = findRangeXOR(l, r);

        System.out.println("The XOR of numbers from " + l + " to " + r + " is: " + ans);

    }

    // brute

    /// Time Complexity: O(N) Traversing through all the numbers take O(N) time.
    ///
    /// Space Complexity: O(1) Using only a couple of variables, i.e., constant space.
    static int findRangeXOR(int l, int r) {

        // To store the XOR of numbers
        int ans = 0;

        // XOR all the numbers
        for (int i = l; i <= r; i++) {
            ans ^= i;
        }

        // Return the result
        return ans;
    }

    // optimal

    /// Time Complexity: O(1) Using constant time operations.
    ///
    /// Space Complexity: O(1) Using a couple of variables i.e., constant space.
    static int findRangeXORI(int l, int r) {
        return XORtillN(l - 1) ^ XORtillN(r);
    }

    private static int XORtillN(int n) {
        if (n % 4 == 1) return 1;
        if (n % 4 == 2) return n + 1;
        if (n % 4 == 3) return 0;
        return n;
    }
}
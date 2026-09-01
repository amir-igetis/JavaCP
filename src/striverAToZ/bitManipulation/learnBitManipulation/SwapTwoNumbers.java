package striverAToZ.bitManipulation.learnBitManipulation;

public class SwapTwoNumbers {

    /// Question 7
    ///
    /// Problem Statement: Given two integers a and b, swap them in-place using only 2 variables (without using a temporary variable).
    public static void main(String[] args) {
        int[] nums = {5, 10};

        swapXOR(nums);

        // Print the result
        System.out.println("a = " + nums[0] + ", b = " + nums[1]);

    }

    /// Time Complexity: O(1) Constant operations.
    ///
    /// Space Complexity: O(1) No extra space used.
    // Swapper class with swapXOR function
    static void swapXOR(int[] arr) {
        // Step 1: XOR first and second
        arr[0] = arr[0] ^ arr[1];

        // Step 2: XOR new first with second, result is original first
        arr[1] = arr[0] ^ arr[1];

        // Step 3: XOR new first with new second, result is original second
        arr[0] = arr[0] ^ arr[1];
    }
}

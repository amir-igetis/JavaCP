package striverAToZ.bitManipulation.advancedMaths;

import java.util.ArrayList;
import java.util.List;

public class DivisorsOfAGivenNum {

    /// Question 2
    ///
    /// Problem Statement: Given an integer N, return all divisors of N.
    /// A divisor of an integer N is a positive integer that divides N without leaving a remainder. In other words, if N is divisible by another integer without any remainder, then that integer is considered a divisor of N.
    public static void main(String[] args) {
        int N = 36;

        // Call the function to get divisors
        List<Integer> result = getDivisors(N);

        // Print the result
        System.out.print("Divisors of " + N + ": ");
        for (int val : result) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    // brute

    /// Time Complexity: O(N), we check for every number from 1 to N.
    /// Space Complexity: O(N), extra space used for storing divisors.
    static List<Integer> getDivisors(int N) {
        // Create list to store divisors
        List<Integer> res = new ArrayList<>();

        // Loop from 1 to N
        for (int i = 1; i <= N; i++) {
            // Check if i is a divisor of N
            if (N % i == 0) {
                // Add i to the result
                res.add(i);
            }
        }
        // Return the list of divisors
        return res;
    }

    // optimal

    /// Time Complexity: O(sqrt(N)), we check for every number between 1 and sqaure root of N.
    /// Space Complexity: O(2*sqrt(N)), extra space used for storing divisors.
    static List<Integer> getDivisorsI(int N) {
        // Create a list to store divisors
        List<Integer> res = new ArrayList<>();

        // Loop from 1 to square root of N
        for (int i = 1; i * i <= N; i++) {
            // Check if i divides N
            if (N % i == 0) {
                // Add i to result
                res.add(i);

                // If N / i is different from i, add N / i too
                if (i != N / i) {
                    res.add(N / i);
                }
            }
        }

        // Return the list of divisors
        return res;
    }

}

package striverAToZ.bitManipulation.interviewProbs;

import java.util.ArrayList;
import java.util.List;

public class PowerSet {

    /// Question 3
    ///
    /// Problem Statement: Given an array of numbers, print all subsets of it using bitwise operators.
    public static void main(String[] args) {
        int[] nums = {5, 7, 8};

        List<List<Integer>> subsets = getPowerSet(nums);

        // Print input array
        System.out.print("Initial Input Array: ");
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Print subsets
        System.out.println("Subsets:");
        for (List<Integer> subset : subsets) {
            System.out.print("[ ");
            for (int num : subset) {
                System.out.print(num + " ");
            }
            System.out.println("]");
        }
    }

    /// Time Complexity: O(N x 2N) where N is the number of elements in the input array. Iterating through all possible numbers from 0 to 2N-1 where N is the number of elements in the input array requires O(2N) iterations.For each iteration, we perform O(N) operations to construct the corresponding subset by interpreting the bits of the number.
    ///
    /// Space Complexity: O(N x 2N) where N is the number of elements in the input array. We store all subsets in a list. Since there are 2N subsets in the power set, each subset can have at most N elements.
    static List<List<Integer>> getPowerSet(int[] nums) {
        // Get the size of the input array
        int n = nums.length;

        // Calculate the total number of subsets (2^n)
        int subsets = 1 << n;

        // List to store all subsets
        List<List<Integer>> ans = new ArrayList<>();

        // Iterate through all numbers from 0 to 2^n - 1
        for (int num = 0; num < subsets; num++) {
            // Temporary list to hold current subset
            List<Integer> subset = new ArrayList<>();

            // Iterate through each bit of the number
            for (int i = 0; i < n; i++) {
                // If ith bit is set, include nums[i] in the subset
                if ((num & (1 << i)) != 0) {
                    subset.add(nums[i]);
                }
            }

            // Add this subset into the final result
            ans.add(subset);
        }

        // Return all subsets
        return ans;
    }
}
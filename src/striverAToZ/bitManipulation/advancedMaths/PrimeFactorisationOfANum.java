package striverAToZ.bitManipulation.advancedMaths;

import java.util.*;

public class PrimeFactorisationOfANum {

    /// Question 4
    ///
    /// Problem Statement: Given an array nums of length n, every integer in the array appears twice except for two integers. Identify and return the two integers that appear only once in the array. Return the two numbers in ascending order.
    ///
    /// For example, if nums = [1, 2, 1, 3, 5, 2], the correct answer is [3, 5], not [5, 3].
    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 3, 5, 2};

        List<Integer> ans = singleNumber(nums);

        System.out.println("The single numbers in given array are: " + ans.get(0) + " and " + ans.get(1));

    }

    // brute

    /// Time Complexity: O(N), where N is the size of the array.
    ///
    /// Traversing the array to update the Hash Map: O(N).
    /// Traversing the map: O(N) (in the worst case).
    /// Sorting the answer array: O(2*log(2)) ~ O(1).
    ///
    /// Hence, the overall time complexity is O(N) + O(N) + O(1) ~ O(N).
    ///
    /// Space Complexity: O(N), since we are using a hashmap data structure, and in the worst case (when all elements in the array are unique), it will store N key-value pairs.
    static List<Integer> singleNumber(int[] nums) {

        // Array to store the answer
        List<Integer> ans = new ArrayList<>();

        /* Map to store the elements
        and their frequencies */
        HashMap<Integer, Integer> mpp = new HashMap<>();

        // Iterate on the array
        for (int num : nums) {
            mpp.put(num, mpp.getOrDefault(num, 0) + 1); // Update the map
        }

        // Iterate on the map
        for (Map.Entry<Integer, Integer> entry : mpp.entrySet()) {
            // If frequency is 1
            if (entry.getValue() == 1) {
                /* Add the element to
                the result array */
                ans.add(entry.getKey());
            }
        }

        // Return the result after sorting
        Collections.sort(ans);
        return ans;
    }

    // optimal

    /// Time Complexity: O(N), traversing the array twice results in O(2*N) time complexity.
    ///
    /// Space Complexity: O(1), using a couple of variables, i.e., constant space.
    static int[] singleNumberI(int[] nums) {
        // Variable to store size of array
        int n = nums.length;

        // Variable to store XOR of all elements
        long XOR = 0;

        // Traverse the array
        for (int i = 0; i < n; i++) {
            // Update the XOR
            XOR = XOR ^ nums[i];
        }

        /* Variable to get the rightmost
        set bit in overall XOR */
        int rightmost = (int) (XOR & (XOR - 1)) ^ (int) XOR;

        /* Variables to stores XOR of
        elements in bucket 1 and 2 */
        int XOR1 = 0, XOR2 = 0;

        // Traverse the array
        for (int i = 0; i < n; i++) {
            /* Divide the numbers among bucket 1
             and 2 based on rightmost set bit */
            if ((nums[i] & rightmost) != 0) {
                XOR1 = XOR1 ^ nums[i];
            } else {
                XOR2 = XOR2 ^ nums[i];
            }
        }

        // Return the result in sorted order
        if (XOR1 < XOR2) return new int[]{XOR1, XOR2};
        return new int[]{XOR2, XOR1};
    }
}
package striverAToZ.recursion.subsequencesPattern;

import java.util.ArrayList;
import java.util.List;

public class CombinationSumIII {

    /// Problem Statement: Determine all possible set of k numbers that can be added together to equal n while meeting the following requirements:
    /// 1. There is only use of numerals 1 through 9.
    /// 2. A single use is made of each number.
    /// Return list of every feasible combination that is allowed. The combinations can be returned in any order, but the list cannot have the same combination twice.
    public static void main(String[] args) {
        int k = 3; // Number of elements in the combination
        int n = 7; // Target sum
        List<List<Integer>> result = combinationSum3(k, n);

        // Print the result
        for (List<Integer> combination : result) {
            for (int num : combination) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    /// Time Complexity: O(2^9 * k), due to the exploration of all subsets of the set {1, 2, ..., 9}..
    ///
    /// Space Complexity: O(k), where k is the number of elements in the combination. This is due to the space used by the recursive call stack and the storage of valid combinations.
    static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> nums = new ArrayList<>();
        // Call the recursive function with initial parameters
        func(n, 1, nums, k, ans);
        return ans;
    }

    private static void func(int sum, int last, List<Integer> nums, int k, List<List<Integer>> ans) {
        // If the sum is zero and the number of elements is k
        if (sum == 0 && nums.size() == k) {
            // Add the current combination to the answer
            ans.add(new ArrayList<>(nums));
            return;
        }
        // If the sum is less than or equal to zero or the number of elements exceeds k
        if (sum <= 0 || nums.size() > k) return;

        // Iterate from the last number to 9
        for (int i = last; i <= 9; i++) {
            // If the current number is less than or equal to the sum
            if (i <= sum) {
                // Add the number to the current combination
                nums.add(i);
                // Recursive call with updated sum and next number
                func(sum - i, i + 1, nums, k, ans);
                // Remove the last number to backtrack
                nums.remove(nums.size() - 1);
            } else {
                // If the number is greater than the sum, break the loop
                break;
            }
        }
    }
}

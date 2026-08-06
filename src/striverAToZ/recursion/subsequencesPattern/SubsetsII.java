package striverAToZ.recursion.subsequencesPattern;

import java.util.*;

public class SubsetsII {

    /// Problem Statement: Given an integer array nums, which can have duplicate entries, provide the power set. Duplicate subsets cannot exist in the solution set. Return the answer in any sequence.
    public static void main(String[] args) {
        int[] nums = {1, 2, 2};
        List<List<Integer>> ans = subsetsWithDup(nums);

        // Print the result
        System.out.println(ans);
    }

    /// Time Complexity: O(N² * 2^N). We generate all 2^N possible subsets, and copying each subset into temporary storage costs up to O(N). Additionally, inserting each subset into a balanced BST-based set costs O(log(2^N)) = O(N), resulting in an extra O(N) factor. Combining these gives O(N * 2^N + N² * 2^N) ≈ O(N² * 2^N).
    ///
    /// Space Complexity: O(N * 2^N). We store up to 2^N subsets in the set, each subset storing up to N elements in the worst case. Additionally, O(N) space is used for the recursion stack during subset generation.
    // brute force
    static List<List<Integer>> subsetsWithDup(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        // Sort the array to handle duplicates consistently
        Arrays.sort(nums);
        findSubsets(0, nums, new ArrayList<>(), result);

        // Convert the set to a list for the final output
        return new ArrayList<>(result);
    }

    private static void findSubsets(int ind, int[] nums, List<Integer> ds, Set<List<Integer>> result) {
        // Base case: if all elements are considered, add the subset to the result set
        if (ind == nums.length) {
            result.add(new ArrayList<>(ds));
            return;
        }

        // Choice 1: Include the current element
        ds.add(nums[ind]);
        findSubsets(ind + 1, nums, ds, result);
        // Backtrack by removing the element to explore the other path
        ds.remove(ds.size() - 1);

        // Choice 2: Do not include the current element
        findSubsets(ind + 1, nums, ds, result);
    }

    /// Time Complexity: O(2^N),In the worst case (all unique elements), we generate all possible subsets, which is 2^N. Sorting takes O(N log N), so total complexity is O(2^N + N log N) ≈ O(2^N).
    ///
    /// Space Complexity: O(N) ,Due to recursion depth and storage of the current subset in the call stack. The output storage is O(2^N) for all subsets.
    // optimal
    static List<List<Integer>> subsetsWithDupI(int[] nums) {
        Arrays.sort(nums); // Sort to handle duplicates
        List<List<Integer>> result = new ArrayList<>();
        backtrack(0, nums, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int start, int[] nums, List<Integer> current, List<List<Integer>> result) {
        // Add current subset to result
        result.add(new ArrayList<>(current));

        // Iterate over array from 'start' index
        for (int i = start; i < nums.length; i++) {
            // Skip duplicates
            if (i > start && nums[i] == nums[i - 1]) continue;

            // Include nums[i] in current subset
            current.add(nums[i]);

            // Recurse for next index
            backtrack(i + 1, nums, current, result);

            // Backtrack: remove last element
            current.remove(current.size() - 1);
        }
    }

}
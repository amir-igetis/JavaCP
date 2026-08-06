package striverAToZ.recursion.subsequencesPattern;

public class CountAllSubsequencesWithSumK {

    /// Problem Statement: Given an array nums and an integer k.Return the number of non-empty subsequences of nums such that the sum of all elements in the subsequence is equal to k.
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        int target = 5;
        System.out.println("Number of subsequences with target sum " + target + ": "
                + countSubsequenceWithTargetSum(nums, target));

    }

    /// Time Complexity: O(2^n), where n is the number of elements in the array. This is because each element can either be included or excluded from the subsequence, leading to 2^n possible combinations.
    ///
    /// Space Complexity: O(n), where n is the depth of the recursion stack. In the worst case, the recursion can go as deep as the number of elements in the array.
    // Function to start counting subsequences
    static int countSubsequenceWithTargetSum(int[] nums, int target) {
        return func(0, target, nums);
    }

    private static int func(int ind, int sum, int[] nums) {
        // Base case: if sum is 0, one valid
        // subsequence is found
        if (sum == 0) return 1;
        // Base case: if sum is negative or
        // index exceeds array size
        if (sum < 0 || ind == nums.length) return 0;
        // Recurse by including current number
        // or excluding it from the sum
        return func(ind + 1, sum - nums[ind], nums) + func(ind + 1, sum, nums);
    }

}
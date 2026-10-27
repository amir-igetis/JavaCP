package leetcodeDiscussProbPatterns.dailyQuestions;

public class MaxKToSortAPermutation {
    public static void main(String[] args) {
        int[] nums = {0, 3, 2, 1};
        System.out.println(sortPermutation(nums));
    }

    // Traverse and Compute the Bitwise AND

    /// Let n be the length of the array nums.
    ///
    /// Time complexity: O(n).
    ///
    /// We only need to traverse the array once, performing constant-time checks and bitwise operations for each element.
    ///
    /// Space complexity: O(1).
    ///
    /// Only a constant amount of extra space is used.
    static int sortPermutation(int[] nums) {
        int n = nums.length;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (nums[i] == i)
                continue;
            res &= nums[i];
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}

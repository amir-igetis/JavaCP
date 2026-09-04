package leetcodeDiscussProbPatterns.dailyQuestions;

public class SmallestStableIdxI {

    public static void main(String[] args) {
        int[] nums = {5, 0, 1, 4};
        int k = 3;
        System.out.println(firstStableIndex(nums, k));
    }

    // prefix and suffix sum
    static int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int[] prefix = new int[n];
        prefix[0] = nums[0];
        for (int i = 1; i < n; i++)
            prefix[i] = Math.max(prefix[i - 1], nums[i]);

        int[] suffix = new int[n];
        suffix[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--)
            suffix[i] = Math.min(suffix[i + 1], nums[i]);

        for (int i = 0; i < n; i++) {
            long instability = (long) prefix[i] - suffix[i];
            if (instability <= k)
                return i;
        }
        return -1;
    }

    // enumeration
    /// Let n be the length of the array nums.
    ///
    /// Time complexity: O(n^2).
    ///
    /// When enumerating each index, it takes O(n) time to compute the prefix maximum and suffix minimum values.
    ///
    /// Space complexity: O(1).
    static int firstStableIndexI(int[] nums, int k) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int maxValue = nums[i];
            int minValue = nums[i];
            for (int j = 0; j < i; j++) {
                maxValue = Math.max(maxValue, nums[j]);
            }
            for (int j = i + 1; j < n; j++) {
                minValue = Math.min(minValue, nums[j]);
            }
            if (maxValue - minValue <= k) {
                return i;
            }
        }
        return -1;
    }
}

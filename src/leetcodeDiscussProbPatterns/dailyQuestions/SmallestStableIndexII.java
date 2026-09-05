package leetcodeDiscussProbPatterns.dailyQuestions;

public class SmallestStableIndexII {
    public static void main(String[] args) {
        int[] nums = {5, 0, 1, 4};
        int k = 3;
        System.out.println(firstStableIndex(nums, k));
    }

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

    // Prefix Maximum + Suffix Minimum

    /// Let n be the length of the array nums.
    ///
    /// Time complexity: O(n).
    ///
    /// The minimum suffix preprocessing and the forward traversal each require O(n) time.
    ///
    /// Space complexity: O(n).
    ///
    /// The space required for the array minValue.
    static int firstStableIndexI(int[] nums, int k) {
        int n = nums.length;
        int[] minValue = new int[n];
        minValue[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            minValue[i] = Math.min(minValue[i + 1], nums[i]);
        }

        int maxValue = 0;
        for (int i = 0; i < n; i++) {
            maxValue = Math.max(maxValue, nums[i]);
            if (maxValue - minValue[i] <= k) {
                return i;
            }
        }
        return -1;
    }
}

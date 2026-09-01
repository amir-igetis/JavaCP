package leetcodeDiscussProbPatterns.dailyQuestions;

public class RemovingMinAndMaxFromArr {

    public static void main(String[] args) {
        int[] arr = { 0, -4, 19, 1, 8, -2, -3, 5 };

        System.out.println("Minimum deletions required to remove min and max from the array: " +
                minimumDeletions(arr));
    }

    // Classification Discussion
    /// Let n be the length of nums.
    ///
    /// Time complexity: O(n).
    ///
    /// We traverse the array once to find the indices of the minimum and maximum
    /// values.
    ///
    /// Space complexity: O(1).
    static int minimumDeletions(int[] nums) {
        int n = nums.length;
        // Find the indices of the minimum and maximum values
        int minidx = 0,
                maxidx = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] < nums[minidx]) {
                minidx = i;
            }
            if (nums[i] > nums[maxidx]) {
                maxidx = i;
            }
        }

        int l = Math.min(minidx, maxidx); // The smaller value in the most valuable index
        int r = Math.max(minidx, maxidx); // The bigger value in the most valuable index

        // Calculate the minimum number of deletions in three cases
        return Math.min(Math.min(r + 1, n - l), l + 1 + n - r);

    }
}
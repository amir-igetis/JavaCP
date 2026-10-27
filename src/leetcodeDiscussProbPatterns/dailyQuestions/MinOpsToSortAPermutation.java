package leetcodeDiscussProbPatterns.dailyQuestions;

public class MinOpsToSortAPermutation {
    public static void main(String[] args) {
        int[] nums = {0, 2, 1};
        System.out.println(minOperations(nums));
    }

    // simulation

    /// Let n be the length of nums.
    ///
    /// Time complexity: O(n).
    ///
    /// Finding the index of 0 and verifying whether nums is cyclically increasing or decreasing each takes O(n) time. The minimum operations calculation takes O(1) time. Thus, the total time complexity is O(n).
    ///
    /// Space complexity: O(1).
    ///
    /// We use only a constant number of auxiliary variables.
    static int minOperations(int[] nums) {
        final int n = nums.length;
        if (n == 1) {
            return 0;
        }

        int start = 0;
        while (nums[start] != 0) {
            start++;
        }

        boolean asc = true;
        boolean desc = true;

        for (int i = 1; i < n; i++) {
            final int value = nums[(start + i) % n];

            if (value != i) {
                asc = false;
            }
            if (value != n - i) {
                desc = false;
            }

            if (!asc && !desc) {
                return -1;
            }
        }

        if (asc) {
            return Math.min(start, n - start + 2);
        }

        return Math.min(n - start, start + 2);
    }
}


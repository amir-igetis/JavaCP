package leetcodeDiscussProbPatterns.dailyQuestions;

public class SmallestIndexWithDigitSumEqualToIndex {
    public static void main(String[] args) {
        int[] nums = {1, 3, 2};
        System.out.println(smallestIndex(nums));
    }

    // traversal

    /// Let n be the length of nums and m be the largest number in nums.
    ///
    /// Time complexity: O(nlogm).
    ///
    /// Calculating the digit sum by repeatedly removing the last digit takes O(logm) time.
    ///
    /// Space complexity: O(1).
    static int smallestIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int digitSum = 0;

            while (num > 0) {
                digitSum += num % 10;
                num /= 10;
            }

            if (digitSum == i) {
                return i;
            }
        }

        return -1;
    }
}

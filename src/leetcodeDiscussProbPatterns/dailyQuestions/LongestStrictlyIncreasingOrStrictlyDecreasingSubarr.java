package leetcodeDiscussProbPatterns.dailyQuestions;

public class LongestStrictlyIncreasingOrStrictlyDecreasingSubarr {
    public static void main(String[] args) {
        int[] nums = {1, 4, 3, 3, 2};
        System.out.println(longestMonotonicSubarray(nums));
    }

    // Brute Force

    /// Let n be the length of the input array.
    ///
    /// Time complexity: O(n
    /// 2
    ///  )
    ///
    /// The solution uses two nested loops to find both increasing and decreasing sequences. For each starting position in the outer loop (which runs n times), the inner loop can potentially examine all remaining elements (up to n elements). This gives us O(n
    /// 2
    ///  ) operations for finding increasing sequences. The same process is repeated for finding decreasing sequences, resulting in another O(n
    /// 2
    ///  ) operations. Therefore, the total time complexity is O(n
    /// 2
    ///  ).
    ///
    /// Space complexity: O(1)
    ///
    /// The solution only uses a constant amount of space to store the variables maxLength and currLength. No additional data structures are created, and the space used does not grow with the input size. Therefore, the space complexity is constant, or O(1)
    static int longestMonotonicSubarrayI(int[] nums) {
        int maxLength = 0;

        // Find longest strictly increasing subarray
        for (int start = 0; start < nums.length; start++) {
            int currLength = 1;
            for (int pos = start + 1; pos < nums.length; pos++) {
                // Extend subarray if next element is larger
                if (nums[pos] > nums[pos - 1]) {
                    currLength++;
                } else {
                    // Break if sequence is not increasing anymore
                    break;
                }
            }
            maxLength = Math.max(maxLength, currLength);
        }

        // Find longest strictly decreasing subarray
        for (int start = 0; start < nums.length; start++) {
            int currLength = 1;
            for (int pos = start + 1; pos < nums.length; pos++) {
                // Extend subarray if next element is smaller
                if (nums[pos] < nums[pos - 1]) {
                    currLength++;
                } else {
                    // Break if sequence is not decreasing anymore
                    break;
                }
            }
            maxLength = Math.max(maxLength, currLength);
        }

        return maxLength; // Return the longer of increasing or decreasing sequences
    }

    // Single Iteration

    /// Let n be the length of the input array nums.
    ///
    /// Time complexity: O(n)
    ///
    /// The algorithm iterates through the array exactly once using a single loop, comparing adjacent elements to determine whether the sequence is increasing or decreasing. Each comparison and update operation is performed in constant time. Therefore, the overall time complexity is O(n).
    ///
    /// Space complexity: O(1)
    ///
    /// The algorithm uses a constant amount of additional space, with variables incLength, decLength, and maxLength to track the lengths of the sequences and the largest length encountered. No extra data structures are used, so the space complexity is O(1)
    static int longestMonotonicSubarray(int[] nums) {
        // Track current lengths of increasing and decreasing sequences
        int incLength = 1, decLength = 1;
        int maxLength = 1;

        // Iterate through array comparing adjacent elements
        for (int pos = 0; pos < nums.length - 1; pos++) {
            if (nums[pos + 1] > nums[pos]) {
                // If next element is larger, extend increasing sequence
                incLength++;
                decLength = 1; // Reset decreasing sequence
            } else if (nums[pos + 1] < nums[pos]) {
                // If next element is smaller, extend decreasing sequence
                decLength++;
                incLength = 1; // Reset increasing sequence
            } else {
                // If elements are equal, reset both sequences
                incLength = 1;
                decLength = 1;
            }

            // Update max length considering both sequences
            maxLength = Math.max(maxLength, Math.max(incLength, decLength));
        }
        return maxLength;
    }
}

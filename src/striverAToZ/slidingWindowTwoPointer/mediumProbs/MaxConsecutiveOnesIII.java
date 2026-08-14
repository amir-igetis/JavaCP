package striverAToZ.slidingWindowTwoPointer.mediumProbs;

public class MaxConsecutiveOnesIII {

    /// Question 2
    /// Problem Statement: Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        int k = 2;

        // Output the result
        System.out.println(longestOnes(nums, k));
    }

    // brute force

    /// Time complexity: O(n^2) , as we are using two nested loops.
    ///
    /// Space complexity: O(1), no extra space used.
    static int longestOnes(int[] nums, int k) {

        // Variable to store maximum length
        int maxLen = 0;

        // Loop to pick each possible start index
        for (int i = 0; i < nums.length; i++) {

            // Counter for zeros in current subarray
            int zeros = 0;

            // Loop to pick each end index for the subarray
            for (int j = i; j < nums.length; j++) {

                // If element is zero, increment zero counter
                if (nums[j] == 0) {
                    zeros++;
                }

                // If number of zeros exceeds allowed flips, break
                if (zeros > k) {
                    break;
                }

                // Update maximum length if current subarray is valid
                maxLen = Math.max(maxLen, j - i + 1);
            }
        }

        // Return the maximum valid subarray length
        return maxLen;
    }

    // better approach

    /// Time Complexity: O(N), We traverse the array only once using two pointers (left and right). Each element is visited at most twice once by the right pointer, and once by the left pointer when shrinking the window.
    ///
    /// Space Complexity: O(1), Only a few integer variables are used to track the window and counters,so the space usage is constant.
    static int longestOnesI(int[] nums, int k) {

        // Left pointer for the window
        int left = 0;

        // Counter for zeros in the window
        int zeros = 0;

        // Variable to store maximum window length
        int maxLen = 0;

        // Right pointer expands the window
        for (int right = 0; right < nums.length; right++) {

            // If element is zero, increase the zero count
            if (nums[right] == 0) {
                zeros++;
            }

            // If zero count exceeds k, shrink the window
            while (zeros > k) {
                if (nums[left] == 0) {
                    zeros--;
                }
                // Move left pointer
                left++;
            }

            // Update the max length of the valid window
            maxLen = Math.max(maxLen, right - left + 1);
        }

        // Return the result
        return maxLen;
    }

    // optimal

    /// Time Complexity: O(N), Each element is processed at most once by the right pointer and once by the left pointer. There’s no nested iteration, so the traversal is strictly linear.
    ///
    /// Space Complexity: O(1), We use only a fixed number of integer variables (left, right, zerocount, maxlen), regardless of input size, so space usage remains constant.
    static int longestOnesII(int[] nums, int k) {

        // Left pointer of the sliding window
        int left = 0;

        // Counter for zeros in the window
        int zerocount = 0;

        // Variable to store maximum window length
        int maxlen = 0;

        // Right pointer expands the window
        for (int right = 0; right < nums.length; right++) {

            // If current element is zero, increment zerocount
            if (nums[right] == 0) {
                zerocount++;
            }

            // If zerocount exceeds k, move left and adjust zerocount
            if (zerocount > k) {
                if (nums[left] == 0) {
                    zerocount--;
                }
                // Shrink window from left
                left++;
            }

            // Update maximum window size
            maxlen = Math.max(maxlen, right - left + 1);
        }

        // Return the final result
        return maxlen;
    }

}
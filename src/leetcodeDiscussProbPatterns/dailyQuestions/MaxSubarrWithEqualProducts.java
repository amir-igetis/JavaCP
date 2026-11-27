package leetcodeDiscussProbPatterns.dailyQuestions;

public class MaxSubarrWithEqualProducts {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 1, 2, 1, 1, 1};
        System.out.println(maxLength(nums));
        System.out.println(maxLengthI(nums));
    }

    // Enumeration

    /// Let n be the length of nums, and M be its maximum element.
    ///
    /// Time complexity: O(n^2logM).
    ///
    /// There are O(n^2) subarrays. Computing the greatest common divisor and least common multiple for each takes O(logM) time, giving O(n^2logM) overall.
    ///
    /// Space complexity: O(1).
    ///
    /// Only a few auxiliary variables are used to maintain the running calculations.
    static int maxLength(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            long prodVal = nums[i];
            long gcdVal = nums[i];
            long lcmVal = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                prodVal = prodVal * nums[j];
                gcdVal = gcd(gcdVal, nums[j]);
                lcmVal = (lcmVal * nums[j]) / gcd(lcmVal, nums[j]);
                if (prodVal > gcdVal * lcmVal) {
                    break;
                }
                if (prodVal == gcdVal * lcmVal) {
                    res = Math.max(res, j - i + 1);
                }
            }
        }
        return res;
    }

    private static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Sliding Window

    /// Let n be the length of nums, and M be its maximum element.
    ///
    /// Time complexity: O(nlogM).
    ///
    /// Each endpoint of the sliding window traverses the array at most once. Computing the greatest common divisor each time an endpoint moves takes O(logM) time, so the total is O(nlogM).
    ///
    /// Space complexity: O(1).
    ///
    /// Only a few variables are used to maintain the sliding window state.
    static int maxLengthI(int[] nums) {
        int res = 2;
        int prodVal = 1;
        int n = nums.length;
        int start = 0,
                end = 0;

        while (end < n) {
            while (gcdI(prodVal, nums[end]) > 1) {
                prodVal /= nums[start];
                start++;
            }
            prodVal *= nums[end];
            res = Math.max(res, end - start + 1);
            end++;
        }
        return res;
    }

    private static int gcdI(int num1, int num2) {
        while (num2 != 0) {
            int temp = num1;
            num1 = num2;
            num2 = temp % num2;
        }
        return num1;
    }
}

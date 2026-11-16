package leetcodeDiscussProbPatterns.dailyQuestions;

public class NumOfSubArrWithOddSum {
    public static void main(String[] args) {
        int[] nums = {1, 3, 5};
        System.out.println(numOfSubarrays(nums));
    }

    /// Let n be the size of the input array arr.
    ///
    /// Time complexity: O(n^2)
    ///
    /// The algorithm uses a nested loop to generate all possible subarrays. The outer loop runs n times, and for each iteration of the outer loop, the inner loop runs up to n times in the worst case. Therefore, the total number of iterations is n×n=n^2
    ///  . Each iteration involves a constant amount of work (addition and modulo operation), so the time complexity is O(n^2)
    ///
    /// Space complexity: O(1)
    ///
    /// The space used by the algorithm is constant, as it only uses a few integer variables (count, currentSum, startIndex, endIndex, and MOD). No additional data structures that scale with the input size are used. Therefore, the space complexity is O(1).
    static int numOfSubarrays(int[] arr) {
        final int MOD = (int) (1e9 + 7);
        int n = arr.length;
        int count = 0;

        // Generate all possible subarrays
        for (int startIndex = 0; startIndex < n; startIndex++) {
            int currentSum = 0;
            // Iterate through each subarray starting at startIndex
            for (int endIndex = startIndex; endIndex < n; endIndex++) {
                currentSum += arr[endIndex];
                // Check if the sum is odd
                if (currentSum % 2 != 0) {
                    count++;
                }
            }
        }

        return count % MOD;
    }

    // DP

    /// Let n be the size of the input array arr.
    ///
    /// Time complexity: O(n)
    ///
    /// The algorithm processes the array in a single pass, iterating through each element exactly once. During each iteration, we perform constant-time operations, including updating the dp table and computing the count of odd-sum subarrays. Since all operations inside the loop are O(1), the overall time complexity remains O(n).
    ///
    /// Space complexity: O(n)
    ///
    /// The algorithm maintains only a fixed-size dp[2][2] table. Since this table occupies only constant space regardless of n, the overall space complexity is O(1). All other variables also use constant space.
    static int numOfSubarraysI(int[] arr) {
        final int MOD = 1_000_000_007;
        int n = arr.length;

        // dp to track counts for even and odd sum subarrays
        int[][] dp = new int[2][2];

        // Stores the final count of subarrays with an odd sum
        int count = dp[1][0];

        for (int i = 0; i < n; i++) {
            // Alternates between 0 and 1 for even/odd index tracking
            int idx = i & 1;
            // Determines if the current element is odd (1) or even (0)
            int parity = arr[i] & 1;

            // If the current element is odd, it contributes to odd subarrays
            // If the current element is even, it contributes to even subarrays
            dp[parity][idx] = (1 + dp[0][1 - idx]) % MOD;
            dp[1 - parity][idx] = dp[1][1 - idx] % MOD;

            // Accumulate the count of odd subarrays
            count = (count + dp[1][idx]) % MOD;
        }
        return count;
    }

    // Prefix Sum with Odd Even Counting

    /// Let n be the size of the input array arr.
    ///
    /// Time complexity: O(n)
    ///
    /// The algorithm processes the array in a single pass. For each element in the array, it updates the prefixSum, checks whether the current prefix sum is even or odd, and updates the count, oddCount, and evenCount variables accordingly. Each of these operations takes constant time. Since the loop iterates through the array once, the overall time complexity is O(n).
    ///
    /// Space complexity: O(1)
    ///
    /// The algorithm uses a constant amount of extra space. It only maintains a few integer variables (count, prefixSum, oddCount, and evenCount), regardless of the size of the input array. No additional data structures that scale with the input size are used. Therefore, the space complexity is O(1).
    static int numOfSubarraysII(int[] arr) {
        final int MOD = 1_000_000_007;
        int count = 0, prefixSum = 0;
        // evenCount starts as one since the initial sum (0) is even
        int oddCount = 0, evenCount = 1;

        for (int num : arr) {
            prefixSum += num;
            // If current prefix sum is even, add the number of odd subarrays
            if (prefixSum % 2 == 0) {
                count += oddCount;
                evenCount++;
            } else {
                // If current prefix sum is odd, add the number of even
                // subarrays
                count += evenCount;
                oddCount++;
            }

            count %= MOD; // To handle large results
        }

        return count;
    }
}

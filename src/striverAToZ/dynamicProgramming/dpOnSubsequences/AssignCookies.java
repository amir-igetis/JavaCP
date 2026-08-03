package striverAToZ.dynamicProgramming.dpOnSubsequences;

import java.util.Arrays;

public class AssignCookies {

    /// Problem Statement: Consider a scenario where a teacher wants to distribute cookies to students, with each
    /// student receiving at most one cookie. Given two arrays, student and cookie, the ith value in the
    /// student array describes the minimum size of cookie that the ith student can be assigned. The jth value in
    /// the cookie array represents the size of the jth cookie. If cookie(j) >= student(i), the jth cookie can be
    /// assigned to the ith student. Maximize the number of students assigned with cookies and output the maximum number.

    public static void main(String[] args) {
        int[] student = {1, 2, 3};
        int[] cookie = {1, 1};


        // Get the number of content students and print it
        int result = findContentChildren(student, cookie);
        System.out.println("Maximum number of content students: " + result);
    }


    /// Time Complexity: O(n*m), every pair of student and cookie is checked exactly once.
    /// Space Complexity: O(n*m) + O(n+m), A 2D memoization table is used to store result of subproblems
    /// and an additional O(n+m) stack space is used.

    // memoization
    static int findContentChildren(int[] student, int[] cookie) {
        // Sort both arrays to apply the greedy strategy
        Arrays.sort(student);
        Arrays.sort(cookie);

        // Initialize memo table
        Integer[][] memo = new Integer[student.length][cookie.length];

        // Start recursion from index 0 for both arrays
        return helper(0, 0, student, cookie, memo);
    }

    // Recursive helper function with memoization
    private static int helper(int studentIndex, int cookieIndex, int[] student, int[] cookie, Integer[][] memo) {
        // Base case: if we reach end of either list
        if (studentIndex >= student.length || cookieIndex >= cookie.length)
            return 0;

        // Return memoized result if already computed
        if (memo[studentIndex][cookieIndex] != null)
            return memo[studentIndex][cookieIndex];

        int result = 0;

        // If the cookie satisfies the student's greed
        if (cookie[cookieIndex] >= student[studentIndex]) {
            // Option 1: assign this cookie and move to next student and cookie
            result = Math.max(result, 1 + helper(studentIndex + 1, cookieIndex + 1, student, cookie, memo));
        }

        // Option 2: skip this cookie and try the next one for the same student
        result = Math.max(result, helper(studentIndex, cookieIndex + 1, student, cookie, memo));

        // Store the result in memo table
        return memo[studentIndex][cookieIndex] = result;
    }

    /// Time Complexity: O(n*m), every pair of student and cookie is checked exactly once.
    /// Space Complexity: O(n*m), A 2D memoization table is used to store result of subproblems.

    // tabulation
    static int findContentChildrenI(int[] student, int[] cookie) {
        int n = student.length;
        int m = cookie.length;

        // Sort both arrays
        Arrays.sort(student);
        Arrays.sort(cookie);

        // Create a DP table
        int[][] dp = new int[n + 1][m + 1];

        // Fill DP table from bottom up
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                // Skip current cookie
                int skip = dp[i][j + 1];

                // Take current cookie if it satisfies student's greed
                int take = 0;
                if (cookie[j] >= student[i]) {
                    take = 1 + dp[i + 1][j + 1];
                }

                // Take the best of both choices
                dp[i][j] = Math.max(skip, take);
            }
        }

        return dp[0][0];
    }

    /// Time Complexity: O(n*logn + m*logm), Both the arrays are sorted in increasing order.
    /// Space Complexity: O(1), No extra space is used.

    // optimal
    static int findContentChildrenII(int[] student, int[] cookie) {
        // Sort both arrays to apply the greedy strategy
        Arrays.sort(student);
        Arrays.sort(cookie);

        int studentIndex = 0;
        int cookieIndex = 0;

        // Try to assign cookies until any one list is fully processed
        while (studentIndex < student.length && cookieIndex < cookie.length) {
            // If the cookie satisfies the student's greed
            if (cookie[cookieIndex] >= student[studentIndex]) {
                studentIndex++;
            }
            // Move to next cookie in both cases
            cookieIndex++;
        }

        // Number of students satisfied is equal to studentIndex
        return studentIndex;
    }

}

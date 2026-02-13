package striverAToZ.learnTheBasics.greedyAlgorithm.easyProbs;

import java.util.Arrays;

public class AssignCookies {
    public static void main(String[] args) {
        int[] student = {1, 2, 3}, cookie = {1, 1};
        System.out.println(findContentChildrenII(student, cookie));
    }

    //    memoization tc o(n * m) sc o(n * m) + o(n + m)
    static int findContentChildren(int[] student, int[] cookie) {
        Arrays.sort(student);
        Arrays.sort(cookie);
        Integer[][] memo = new Integer[student.length][cookie.length];
        return helper(0, 0, student, cookie, memo);
    }

    private static int helper(int studentIdx, int cookieIdx, int[] student, int[] cookie, Integer[][] memo) {
        if (studentIdx >= student.length || cookieIdx >= cookie.length)
            return 0;

        if (memo[studentIdx][cookieIdx] != null)
            return memo[studentIdx][cookieIdx];

        int res = 0;

        if (cookie[cookieIdx] >= student[studentIdx])
            res = Math.max(res, 1 + helper(studentIdx + 1, cookieIdx + 1, student, cookie, memo));

        res = Math.max(res, helper(studentIdx, cookieIdx + 1, student, cookie, memo));

        return memo[studentIdx][cookieIdx] = res;
    }

    //    tabulation tc o (n * m) sc o(n * m)
    static int findContentChildrenI(int[] student, int[] cookie) {
        int n = student.length, m = cookie.length;
        Arrays.sort(student);
        Arrays.sort(cookie);

        int[][] dp = new int[n + 1][m + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                int skip = dp[i][j + 1];
                int take = 0;
                if (cookie[j] >= student[i])
                    take = 1 + dp[i + 1][j + 1];

                dp[i][j] = Math.max(skip, take);
            }
        }

        return dp[0][0];
    }

//    optimal approach tc o(n * logn + m * logm) sc o(1)
    static int findContentChildrenII(int[] student, int[] cookie) {
        Arrays.sort(student);
        Arrays.sort(cookie);
        int studentIdx = 0, cookieIdx = 0;

        while (studentIdx < student.length && cookieIdx< cookie.length) {
            if (cookie[cookieIdx] >= student[studentIdx])
                studentIdx++;

            cookieIdx++;
        }

        return studentIdx;
    }
}

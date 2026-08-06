package striverAToZ.recursion.hadQuestions;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {

    /// Question 1
    public static void main(String[] args) {
        String s = "aabb";
        List<List<String>> ans = partition(s);
        int n = ans.size();
        System.out.println("The palindromic partitions are :-");
        System.out.print(" [ ");
        for (int i = 0; i < ans.size(); i++) {
            System.out.print("[");
            for (int j = 0; j < ans.get(i).size(); j++) {
                System.out.print(ans.get(i).get(j) + " ");
            }
            System.out.print("] ");
        }
        System.out.print("]");
    }

    // striver soln
    // soln for https://leetcode.com/problems/palindrome-partitioning/
    static List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        List<String> path = new ArrayList<>();
        partitionHelper(0, s, path, res);
        return res;
    }

    private static void partitionHelper(int index, String s, List<String> path, List<List<String>> res) {
        if (index == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < s.length(); ++i) {
            if (isPalindrome(s, index, i)) {
                path.add(s.substring(index, i + 1));
                partitionHelper(i + 1, s, path, res);
                path.remove(path.size() - 1);
            }
        }
    }

    private static boolean isPalindrome(String s, int start, int end) {
        while (start <= end) {
            if (s.charAt(start++) != s.charAt(end--)) {
                return false;
            }
        }
        return true;
    } // tc O((2^n)*k*(n/2)) & sc O(k*x)


    // soln for https://practice.geeksforgeeks.org/problems/palindromic-patitioning4845/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=palindromic-patitioning
    // main func
    static int palindromicPartition(String str) {
        int dp[][] = new int[501][501];
        for (int i = 0; i < 501; i++) {
            for (int j = 0; j < 501; j++)
                dp[i][j] = -1;
        }
        // Arrays.fill(dp,-1);
        return solve(str, 0, str.length() - 1, dp);
    }

    private static boolean palid(String str, int i, int j) {
        if (i >= j) return true;
        while (i < j) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    // func to solving
    private static int solve(String str, int i, int j, int dp[][]) {
        if (i >= j) return 0; // base condition

        if (palid(str, i, j)) return 0; // if alredy palindrome then no need of cuts

        if (dp[i][j] != -1) return dp[i][j]; // if alredy solved

        int left = 0, right = 0;
        int min = Integer.MAX_VALUE;
        for (int k = i; k <= j - 1; k++) {
            if (dp[i][k] != -1) {
                left = dp[i][k];
            } else {
                left = solve(str, i, k, dp);
                dp[i][k] = left;
            }
            if (dp[k + 1][j] != -1) {
                right = dp[k + 1][j];
            } else {
                right = solve(str, k + 1, j, dp);
                dp[k + 1][j] = right;
            }
            int temp = 1 + left + right;
            min = Math.min(min, temp);
        }
        return dp[i][j] = min;
    }

}

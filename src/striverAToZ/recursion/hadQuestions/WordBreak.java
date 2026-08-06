package striverAToZ.recursion.hadQuestions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    // soln for https://leetcode.com/problems/word-break/description/
    static boolean wordBreakFunc(String str, List<String> dict) {
        return dfs(str, new HashSet(dict), 0, new Boolean[str.length()]);
    }

    private static boolean dfs(String s, Set<String> wordDict, int start, Boolean[] memo) {
        if (start == s.length()) {
            return true;
        }

        if (memo[start] != null) {
            return memo[start];
        }

        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end))
                    && dfs(s, wordDict, end, memo)) {
                return memo[start] = true;
            }
        }

        return memo[start] = false;
    } // tc O(n^2)

    // soln for https://practice.geeksforgeeks.org/problems/word-break-part-23249/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=word-break-part-2
    static List<String> wordBreakI(int n, List<String> dict, String s) {
        // code here
        List<String> ans = new ArrayList<>();
        solveI(dict, s, ans, "");
        return ans;
    }

    private static void solveI(List<String> dict, String s, List<String> ans, String temp) {
        if (s.isEmpty()) {
            ans.add(temp.substring(1));
            return;
        }
        for (int i = 1; i <= s.length(); i++)
            if (dict.contains(s.substring(0, i)))
                solveI(dict, s.substring(i), ans,
                        temp + " " + s.substring(0, i));
    }
}


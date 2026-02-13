package LeetcodeInterview.dp;

import java.util.*;

public class WordBreak {
    public static void main(String[] args) {
        String s = "applepenapple";
        List<String> wordDict = new ArrayList<>(List.of("apple", "pen"));

        System.out.println(wordBreak(s, wordDict));

    }

    // using dp
    static boolean wordBreak(String s, List<String> wordDict) {

        Set<String> dict = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];

    }

    static boolean wordBreakI(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        Queue<Integer> q = new LinkedList<>();
        boolean[] vis = new boolean[s.length()];

        q.add(0);
        while (!q.isEmpty()) {
            int start = q.poll();
            if (vis[start])
                continue;

            for (int end = start + 1; end <= s.length(); end++) {
                if (dict.contains(s.substring(start, end))) {
                    if (end == s.length())
                        return true;
                    q.add(end);
                }
            }
            vis[start] = true;
        }
        return false;
    }

//    static Map<Integer, Boolean> memo = new HashMap<>();
//    static Set<String> dict;
//
//    static boolean wordBreakI(String s, List<String> wordDict) {
//        dict = new HashSet<>(wordDict);
//        return dfs(s, 0);
//    }
//
//    private static boolean dfs(String s, int start) {
//        if (start == s.length()) return true;
//        if (memo.containsKey(start)) return memo.get(start);
//
//        for (int end = start + 1; end <= s.length(); end++) {
//            if (dict.contains(s.substring(start, end)) && dfs(s, end)) {
//                memo.put(start, true);
//                return true;
//            }
//        }
//        memo.put(start, false);
//        return false;
//    }
}

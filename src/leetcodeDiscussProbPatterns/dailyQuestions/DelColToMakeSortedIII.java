package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;

public class DelColToMakeSortedIII {
    public static void main(String[] args) {
        String[] strs = {"babca", "bbazb"};
        System.out.println(minDeletionSize(strs));
    }

    //    Dynamic programming
    static int minDeletionSize(String[] strs) {
        int n = strs.length;
        int w = strs[0].length();
        int[] dp = new int[w];
        int maxKept = 1;
        Arrays.fill(dp, 1);
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < i; j++) {
                if (canExtend(strs, j, i)) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxKept = Math.max(maxKept, dp[i]);
        }
        return w - maxKept;
    }

    private static boolean canExtend(String[] strs, int j, int i) {
        for (String s : strs) {
            if (s.charAt(j) > s.charAt(i))
                return false;
        }

        return true;
    }

}

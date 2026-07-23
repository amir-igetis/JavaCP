package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.ArrayList;
import java.util.List;

public class MaximizeActiveSectionWithTradeI {
    public static void main(String[] args) {
        String s = "1000100";
        System.out.println(maxActiveSectionsAfterTrade(s));
    }

    // greedy tc O(n) sc O(k)
    static int maxActiveSectionsAfterTrade(String s) {
        int n = s.length();
        int cnt1 = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') cnt1++;
        }

        List<Integer> zeroBlocks = new ArrayList<>();
        int i = 0;
        while (i < n) {
            int start = i;
            while (i < n && s.charAt(i) == s.charAt(start)) {
                i++;
            }
            if (s.charAt(start) == '0') {
                zeroBlocks.add(i - start);
            }
        }

        int m = zeroBlocks.size();
        if (m < 2) {
            return cnt1;
        }
        int bestGain = 0; // Optimal Increment
        for (int j = 0; j < m - 1; j++) {
            bestGain = Math.max(
                    bestGain,
                    zeroBlocks.get(j) + zeroBlocks.get(j + 1)
            );
        }

        return cnt1 + bestGain;
    }

    // space optimization tc O(n) and sc O(1)
    static int maxActiveSectionsAfterTradeI(String s) {
        int n = s.length();
        int cnt1 = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') cnt1++;
        }

        int i = 0;
        int bestGain = 0;
        int prev = Integer.MIN_VALUE;
        int cur = 0;

        while (i < n) {
            int start = i;
            while (i < n && s.charAt(i) == s.charAt(start)) {
                i++;
            }
            if (s.charAt(start) == '0') {
                cur = i - start;
                if (prev != Integer.MIN_VALUE) {
                    bestGain = Math.max(bestGain, prev + cur);
                }
                prev = cur;
            }
        }

        return cnt1 + bestGain;
    }

}


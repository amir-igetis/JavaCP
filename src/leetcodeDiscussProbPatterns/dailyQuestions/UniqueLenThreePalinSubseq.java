package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;

public class UniqueLenThreePalinSubseq {
    public static void main(String[] args) {
        String s = "aabca";
        System.out.println(countPalindromicSubsequence(s));
    }

    static int countPalindromicSubsequence(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, -1);
        Arrays.fill(last, -1);

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (first[c] == -1)
                first[c] = i;
            last[c] = i;
        }

        int result = 0;

        for (int c = 0; c < 26; c++) {
            if (first[c] != -1 && last[c] != -1 && first[c] < last[c]) {
                int l = first[c], r = last[c];
                boolean[] seen = new boolean[26];

                for (int i = l + 1; i < r; i++)
                    seen[s.charAt(i) - 'a'] = true;

                for (boolean b : seen)
                    if (b)
                        result++;
            }
        }

        return result;
    }
}

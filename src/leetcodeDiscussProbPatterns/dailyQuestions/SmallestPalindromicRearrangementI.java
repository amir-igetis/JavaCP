package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;

public class SmallestPalindromicRearrangementI {
    public static void main(String[] args) {
        String s = "z";
        System.out.println(smallestPalindromeI(s));
    }

    /// sorting tc O(nlogn) sc O(n) or O(logn)
    static String smallestPalindrome(String s) {
        int len = s.length();
        int partition = len / 2;
        char[] chars = s.toCharArray();
        Arrays.sort(chars, 0, partition);

        for (int i = 0; i < partition; i++)
            chars[len - 1 - i] = chars[i];


        return new String(chars);
    }

    /// counting sort tc O(n) sc O(1)
    static String smallestPalindromeI(String s) {
        int partition = s.length() / 2;
        int[] bucket = new int[26];

        for (int i = 0; i < partition; i++) {
            bucket[s.charAt(i) - 'a'] += 1;
        }

        StringBuilder left = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (bucket[i] > 0) {
                left.append(String.valueOf((char) (i + 'a')).repeat(bucket[i]));
            }
        }

        String mid =
                s.length() % 2 != 0 ? String.valueOf(s.charAt(partition)) : "";
        String right = new StringBuilder(left).reverse().toString();

        return left.toString() + mid + right;
    }
}

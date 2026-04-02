package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;

public class CheckIfStrCanBeMadeEqualWithOpsI {
    public static void main(String[] args) {
        String s1 = "abcdba", s2 = "cabdab";
        System.out.println(checkStrings(s1, s2));
    }

    //    hash table
    static boolean checkStrings(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        int[] count1 = new int[256];
        int[] count2 = new int[256];

        for (int i = 0; i < s1.length(); i++) {
            int offset = (i & 1) << 7;
            count1[offset + s1.charAt(i)]++;
            count2[offset + s2.charAt(i)]++;
        }

        return Arrays.equals(count1, count2);
    }
}

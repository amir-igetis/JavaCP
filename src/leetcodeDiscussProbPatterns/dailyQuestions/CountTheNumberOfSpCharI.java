package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.*;

public class CountTheNumberOfSpCharI {
    public static void main(String[] args) {
        String word = "aaAbcBC";
        System.out.println(numberOfSpecialCharsI(word));
    }

    static int numberOfSpecialChars(String word) {

        Map<Character, int[]> mp = new HashMap<>();
        for (char ch : word.toCharArray()) {
            char lower = Character.toLowerCase(ch);
            mp.putIfAbsent(lower, new int[2]);

            if (Character.isLowerCase(ch)) {
                mp.get(lower)[0]++;
            } else {
                mp.get(lower)[1]++;
            }
        }

        int count = 0;
        for (int[] arr : mp.values()) {
            if (arr[0] > 0 && arr[1] > 0) {
                count++;
            }
        }

        return count;
    }

    static int numberOfSpecialCharsI(String word) {
        Set<Character> s = new HashSet<>();
        for (char c : word.toCharArray()) {
            s.add(c);
        }
        int ans = 0;
        for (char c = 'a'; c <= 'z'; c++) {
            if (s.contains(c) && s.contains((char) (c - 'a' + 'A'))) {
                ans++;
            }
        }
        return ans;
    }
}
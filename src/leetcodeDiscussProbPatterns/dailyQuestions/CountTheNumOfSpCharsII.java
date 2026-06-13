package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;

public class CountTheNumOfSpCharsII {
    public static void main(String[] args) {
        String word = "aaAbcBC";
        System.out.println(numberOfSpecialChars(word));
    }

    static int numberOfSpecialChars(String word) {
        int[] lastLow = new int[26];
        int[] firstUp = new int[26];
        Arrays.fill(lastLow, -1);
        Arrays.fill(firstUp, -1);
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (Character.isLowerCase(c)) {
                lastLow[c - 'a'] = i;
            } else {
                if (firstUp[c - 'A'] == -1) {
                    firstUp[c - 'A'] = i;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < 26; i++) {
            if (
                    lastLow[i] != -1 && firstUp[i] != -1 && lastLow[i] < firstUp[i]
            ) {
                ans++;
            }
        }
        return ans;
    }

}

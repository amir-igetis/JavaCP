package LeetcodeInterview.slidingWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubstrWithConcatOfAllWrods {
    public static void main(String[] args) {
//
        String s = "barfoothefoobarman";
        String[] words = {"foo", "bar"};
        List<Integer> ans = findSubstring(s, words);
        for (int i : ans) {
            System.out.print(i + ", ");
        }
        System.out.println();
    }

    static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0)
            return res;

        int wordLen = words[0].length();
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;

        Map<String, Integer> wordMp = new HashMap<>();
        for (String word : words)
            wordMp.put(word, wordMp.getOrDefault(word, 0) + 1);

        for (int i = 0; i < wordLen; i++) {
            int left = i, right = i;
            Map<String, Integer> seenWords = new HashMap<>();
            int count = 0;
            while (right + wordLen <= s.length()) {
                String word = s.substring(right, right + wordLen);
                right += wordLen;

                if (wordMp.containsKey(word)) {
                    seenWords.put(word, seenWords.getOrDefault(word, 0) + 1);
                    count++;

                    while (seenWords.get(word) > wordMp.get(word)) {
                        String leftWord = s.substring(left, left + wordLen);
                        seenWords.put(leftWord, seenWords.get(leftWord) - 1);
                        left += wordLen;
                        count--;
                    }

                    if (count == wordCount)
                        res.add(left);
                } else {
                    seenWords.clear();
                    left = right;
                    count = 0;
                }
            }
        }
        return res;
    }
}

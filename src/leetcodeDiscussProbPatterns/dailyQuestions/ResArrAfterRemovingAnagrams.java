package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResArrAfterRemovingAnagrams {

    public static void main(String[] args) {
        String[] words = {"abba", "baba", "bbaa", "cd", "cd"};
        List<String> ans = removeAnagrams(words);
        for (String i : ans) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    static List<String> removeAnagrams(String[] words) {
        List<String> res = new ArrayList<>();
        String prevSorted = "";
        for (String word : words) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String sortedWord = new String(chars);
            if (!sortedWord.equals(prevSorted)) {
                res.add(word);
                prevSorted = sortedWord;
            }
        }
        return res;
    }
}

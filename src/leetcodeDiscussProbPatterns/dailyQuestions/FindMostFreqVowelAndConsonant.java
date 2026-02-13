package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FindMostFreqVowelAndConsonant {
    public static void main(String[] args) {
        String s = "successes";
        System.out.println(maxFreqSum(s));
    }

    static int maxFreqSum(String s) {
        int[] freq = new int[26];
        for (char ch : s.toCharArray())
            freq[ch - 'a']++;

        Set<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');

        int maxVow = 0, maxConst = 0;

        for (int i = 0; i < 26; i++) {
            char ch = (char) ('a' + i);
            if (freq[i] > 0) {
                if (vowels.contains(ch))
                    maxVow = Math.max(maxVow, freq[i]);
                else maxConst = Math.max(maxConst, freq[i]);
            }
        }
        return maxVow + maxConst;
    }

    static int maxFreqSumI(String s) {
        Map<Character, Integer> freqMp = new HashMap<>();
        for (char ch : s.toCharArray())
            freqMp.put(ch, freqMp.getOrDefault(ch, 0) + 1);

        Set<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');

        // same as
//        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');

        int maxVowels = 0, maxConsonant = 0;
        for (Map.Entry<Character, Integer> entry : freqMp.entrySet()) {
            char ch = entry.getKey();
            int count = entry.getValue();
            if (vowels.contains(ch))
                maxVowels = Math.max(maxVowels, count);
            else maxConsonant = Math.max(maxConsonant, count);
        }

        return maxVowels + maxConsonant;

    }
}

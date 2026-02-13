package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.*;

public class VowelSpellchecker {
    public static void main(String[] args) {
        String[] wordlist = {"KiTe", "kite", "hare", "Hare"},
                queries = {"kite", "Kite", "KiTe", "Hare", "HARE", "Hear", "hear", "keti", "keet", "keto"};


        String[] ans = spellchecker(wordlist, queries);
        for (String i : ans)
            System.out.print(i + " ");
        System.out.println();
    }

    static String[] spellchecker(String[] wordlist, String[] queries) {
//        exact matches
        Set<String> exactWords = new HashSet<>(Arrays.asList(wordlist));

//        case-insensitive matches
        Map<String, String> caseInsensitive = new HashMap<>();

//        vowel-error matches
        Map<String, String> vowelInsensitive = new HashMap<>();

        for (String word : wordlist) {
            String lower = word.toLowerCase();
            String devoweled = devowel(lower);

            caseInsensitive.putIfAbsent(lower, word);
            vowelInsensitive.putIfAbsent(devoweled, word);
        }

        String[] res = new String[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];
            if (exactWords.contains(query))
                res[i] = query;
            else {
                String lower = query.toLowerCase();
                String devoweled = devowel(lower);

                if (caseInsensitive.containsKey(lower))
                    res[i] = caseInsensitive.get(lower);
                else if (vowelInsensitive.containsKey(devoweled))
                    res[i] = vowelInsensitive.get(devoweled);
                else res[i] = "";
            }
        }
        return res;
    }

    private static String devowel(String word) {
        StringBuilder sb = new StringBuilder();
        for (char ch : word.toCharArray()) {
            if (isVowel(ch))
                sb.append('*');
            else sb.append(ch);
        }
        return sb.toString();
    }

    private static boolean isVowel(char ch) {
        return "aeiou".indexOf(ch) != -1;
    }

}

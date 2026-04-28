package leetcodeContest;

import java.util.*;

public class B {

    public static void main(String[] args) {
        String s = "leetcode";
        System.out.println(sortVowels(s));
    }

    static String sortVowels(String s) {
        int n = s.length();

        int[] freq = new int[26];
        int[] idx = new int[26];
        Arrays.fill(idx, -1);

        Set<Character> st = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (st.contains(ch)) {
                freq[ch - 'a']++;
                if (idx[ch - 'a'] == -1)
                    idx[ch - 'a'] = i;
            }
        }

        List<Character> vowel = new ArrayList<>();
        for (char ch : s.toCharArray())
            if (st.contains(ch))
                vowel.add(ch);


        Collections.sort(vowel, (a, b) -> {
            int fa = freq[a - 'a'];
            int fb = freq[b - 'a'];

            if (fa != fb) return fb - fa;
            return idx[a - 'a'] - idx[b - 'a'];
        });

        StringBuilder res = new StringBuilder(s);
        int j = 0;

        for (int i = 0; i < n; i++) {
            if (st.contains(s.charAt(i)))
                res.setCharAt(i, vowel.get(j++));
        }


        return res.toString();
    }
}
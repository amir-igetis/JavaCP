package LeetcodeInterview.hashMap;

import java.util.HashMap;
import java.util.Map;

public class ValidAnagram {
    public static void main(String[] args) {
//
        String s = "anagram", t = "nagaram";
        System.out.println(isAnagram(s, t));
    }

    static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        Map<Character, Integer> countMp = new HashMap<>();
        for (char ch : s.toCharArray())
            countMp.put(ch, countMp.getOrDefault(ch, 0) + 1);

        for (char ch : t.toCharArray()) {
            if (!countMp.containsKey(ch) || countMp.get(ch) == 0)
                return false;

            countMp.put(ch, countMp.get(ch) - 1);
        }

        return true;
    }

    static boolean isAnagramI(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] count = new int[26];
        for (char ch : s.toCharArray())
            count[ch - 'a']++;

        for (char ch : t.toCharArray()) {
            count[ch - 'a']--;
            if (count[ch - 'a'] < 0)
                return false;
        }

        return true;
    }
}

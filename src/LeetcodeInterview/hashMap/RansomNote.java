package LeetcodeInterview.hashMap;

import java.util.HashMap;
import java.util.Map;

public class RansomNote {
    public static void main(String[] args) {
//
        String ransomNote = "a", magazine = "b";
        System.out.println(canConstruct(ransomNote, magazine));
    }

    static boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> letterCount = new HashMap<>();
        for (char ch : magazine.toCharArray()) {
            letterCount.put(ch, letterCount.getOrDefault(ch, 0) + 1);
        }

        for (char ch : ransomNote.toCharArray()) {
            if (!letterCount.containsKey(ch) || letterCount.get(ch) == 0)
                return false;

            letterCount.put(ch, letterCount.get(ch) - 1);
        }

        return true;
    }

    static boolean canConstructI(String ransomNote, String magazine) {
        int[] count = new int[26];
        for (char ch : magazine.toCharArray()) {
            count[ch - 'a']++;
        }

        for (char ch : ransomNote.toCharArray()) {
            if (count[ch - 'a'] == 0)
                return false;

            count[ch - 'a']--;
        }

        return true;
    }
}

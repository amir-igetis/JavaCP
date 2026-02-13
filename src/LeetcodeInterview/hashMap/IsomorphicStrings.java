package LeetcodeInterview.hashMap;

import java.util.HashMap;
import java.util.Map;

public class IsomorphicStrings {
    public static void main(String[] args) {
//
        String s = "egg", t = "add";
        System.out.println(isIsomorphic(s, t));
    }

    static boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;

        HashMap<Character, Character> sToT = new HashMap<>();
        HashMap<Character, Character> tToS = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);

            if (sToT.containsKey(sChar) && sToT.get(sChar) != tChar)
                return false;
            if (tToS.containsKey(tChar) && tToS.get(tChar) != sChar)
                return false;
            sToT.put(sChar, tChar);
            tToS.put(tChar, sChar);
        }

        return true;
    }

    static boolean isIsomorphicI(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] sTot = new int[256];
        int[] tTos = new int[256];

        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);

            if (sTot[sChar] != 0 && sTot[sChar] != tChar)
                return false;

            if (tTos[tChar] != 0 && tTos[tChar] != sChar)
                return false;

            sTot[sChar] = tChar;
            tTos[tChar] = sChar;
        }

        return true;
    }
}

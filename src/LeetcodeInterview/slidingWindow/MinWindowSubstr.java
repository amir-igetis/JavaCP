package LeetcodeInterview.slidingWindow;

import java.util.HashMap;
import java.util.Map;

public class MinWindowSubstr {
    public static void main(String[] args) {
//
        String s = "ADOBECODEBANC", t = "ABC";
        System.out.println(minWindowI(s, t));
    }

    static String minWindowI(String s, String t) {
        int[] tFreq = new int[128];

        // Traverse through string t and update the frequencies
        for (char c : t.toCharArray()) {
            tFreq[c]++;
        }

        // Initialize variables for window pointers, minimum window indices, and other tracking variables
        int left = 0;
        int right = 0;
        int required = t.length();
        int formed = 0;
        int minLen = Integer.MAX_VALUE;
        int minLeft = 0;
        int minRight = 0;

        // Start moving the right pointer to the right until it reaches the end of string s
        while (right < s.length()) {
            char c = s.charAt(right);
            tFreq[c]--;

            // Check if the current window contains a required character
            if (tFreq[c] >= 0) {
                formed++;
            }

            // Try to minimize the window by moving the left pointer to the right
            while (formed == required) {
                // Update the minimum window if a smaller one is found
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minLeft = left;
                    minRight = right;
                }

                // Move the left pointer to the right and update frequencies
                tFreq[s.charAt(left)]++;

                // Check if the character at left was a required character
                if (tFreq[s.charAt(left)] > 0) {
                    formed--;
                }

                left++;
            }

            right++;
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minLeft, minRight + 1);
    }

    static String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";
        Map<Character, Integer> targetMp = new HashMap<>();
        for (char ch : t.toCharArray())
            targetMp.put(ch, targetMp.getOrDefault(ch, 0) + 1);

        int left = 0, right = 0, minStart = 0, minLen = Integer.MAX_VALUE;
        int required = targetMp.size(), formed = 0;
        Map<Character, Integer> windowMp = new HashMap<>();

        while (right < s.length()) {
            char c = s.charAt(right);
            windowMp.put(c, windowMp.getOrDefault(c, 0) + 1);

            if (targetMp.containsKey(c) && windowMp.get(c).intValue() == targetMp.get(c).intValue())
                formed++;

            while (formed == required) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minStart = left;
                }

                char leftChar = s.charAt(left);
                windowMp.put(leftChar, windowMp.get(leftChar) - 1);

                if (targetMp.containsKey(leftChar) && windowMp.get(leftChar) < targetMp.get(leftChar))
                    formed--;

                left++;
            }
            right++;
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }
}

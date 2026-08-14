package striverAToZ.slidingWindowTwoPointer.hardProbs;

public class MinWindowSubstring {

    /// Question 3
    ///
    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(smallestWindow(s, t));
    }

    // soln for https://leetcode.com/problems/minimum-window-substring/
    // soln for https://practice.geeksforgeeks.org/problems/smallest-window-in-a-string-containing-all-the-characters-of-another-string-1587115621/1
    static String smallestWindow(String s, String t) {
        int[] arr = new int[128];
        for (char c : t.toCharArray()) {
            arr[c]++;
        }
        int start = 0, end = 0, minStart = 0;
        int minLen = Integer.MAX_VALUE, counter = t.length();
        while (end < s.length()) {
            char c1 = s.charAt(end);
            if (arr[c1] > 0) {
                counter--;
            }
            arr[c1]--;
            end++;
            while (counter == 0) {
                if (minLen > end - start) {
                    minLen = end - start;
                    minStart = start;
                }
                char c2 = s.charAt(start);
                arr[c2]++;
                if (arr[c2] > 0) {
                    counter++;
                }
                start++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "-1" : s.substring(minStart, minStart + minLen);
        // for leetcode
        // return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }
}

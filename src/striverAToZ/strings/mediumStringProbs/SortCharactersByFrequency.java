package striverAToZ.strings.mediumStringProbs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortCharactersByFrequency {

    /// Question 1
    ///
    /// Problem Statement: You are given a string s. Return the array of unique characters, sorted by highest to lowest occurring characters.
    /// If two or more characters have same frequency then arrange them in alphabetic order.
    public static void main(String[] args) {

        // Input string
        String s = "tree";

        // Get characters sorted by frequency
        List<Character> result = frequencySort(s);

        // Print the result
        System.out.println(result);
    }

    /// Time Complexity: O(n + k log k), where n is the length of the string and k is the constant 26 for the alphabet.
    ///
    /// Space Complexity: O(k) , where k is the constant 26 for the frequency array.
    static List<Character> frequencySort(String s) {
        // Array to hold frequency and character for 'a' to 'z'
        Pair[] freq = new Pair[26];

        // Initialize the frequency array
        for (int i = 0; i < 26; i++) {
            freq[i] = new Pair(0, (char) (i + 'a'));
        }

        // Count frequency of each character in the string
        for (char ch : s.toCharArray()) {
            freq[ch - 'a'].freq++;
        }

        // Sort array by frequency descending, then by character ascending
        Arrays.sort(freq, (p1, p2) -> {
            if (p1.freq != p2.freq) return p2.freq - p1.freq;
            return p1.ch - p2.ch;
        });

        // Collect characters with non-zero frequency into result list
        List<Character> result = new ArrayList<>();
        for (Pair p : freq) {
            if (p.freq > 0) result.add(p.ch);
        }

        // Return the final list
        return result;
    }

    // Inner class to store frequency and character
    private static class Pair {
        int freq;
        char ch;

        Pair(int f, char c) {
            this.freq = f;
            this.ch = c;
        }
    }
}

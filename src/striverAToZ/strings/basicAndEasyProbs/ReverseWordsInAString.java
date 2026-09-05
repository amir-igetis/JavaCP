package striverAToZ.strings.basicAndEasyProbs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReverseWordsInAString {

    /// Question 2
    ///
    /// Problem Statement: Given an input string, containing upper-case and lower-case letters, digits, and spaces( ' ' ). A word is defined as a sequence of non-space characters. The words in s are separated by at least one space. Return a string with the words in reverse order, concatenated by a single space.
    public static void main(String[] args) {
        String s = " amazing coding skills ";
        System.out.println(reverseWords(s));
    }

    // brute

    /// Time Complexity: O(N),We traverse the string once to collect words (O(N)) and once more to reverse and join them (O(N)). Hence total time is O(N).
    ///
    /// Space Complexity: O(N),We store all words in a separate list/array, requiring extra space proportional to the number of characters.
    static String reverseWords(String s) {
        // List to store words
        List<String> words = new ArrayList<>();

        // StringBuilder to store a single word
        StringBuilder word = new StringBuilder();

        // Traverse each character in the string
        for (int i = 0; i < s.length(); i++) {
            // If it's not a space, add it to current word
            if (s.charAt(i) != ' ') {
                word.append(s.charAt(i));
            }
            // If space and a word is formed
            else if (word.length() > 0) {
                // Add word to list
                words.add(word.toString());
                // Reset word
                word.setLength(0);
            }
        }

        // Add the last word if present
        if (word.length() > 0) {
            words.add(word.toString());
        }

        // Reverse the list
        Collections.reverse(words);

        // Join with single space
        return String.join(" ", words);
    }

    // optimal

    /// Time Complexity: O(N), We traverse the string once from right to left and construct the result directly without extra passes.
    ///
    /// Space Complexity: O(1),Ignoring the output string, no additional data structures proportional to input size are used.
    static String reverseWordsI(String s) {
        // StringBuilder for final result
        StringBuilder result = new StringBuilder();

        // Pointer starting from the end
        int i = s.length() - 1;

        // Traverse from right to left
        while (i >= 0) {
            // Skip spaces
            while (i >= 0 && s.charAt(i) == ' ') {
                i--;
            }

            // If pointer goes out of bounds, break
            if (i < 0) break;

            // Mark end of word
            int end = i;

            // Move left until space or start of string
            while (i >= 0 && s.charAt(i) != ' ') {
                i--;
            }

            // Extract the word
            String word = s.substring(i + 1, end + 1);

            // Add space before appending if result is not empty
            if (result.length() > 0) {
                result.append(" ");
            }

            // Append word
            result.append(word);
        }

        return result.toString();
    }

}

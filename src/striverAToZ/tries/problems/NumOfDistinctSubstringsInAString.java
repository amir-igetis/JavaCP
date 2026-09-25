package striverAToZ.tries.problems;

import java.util.HashSet;
import java.util.Set;

public class NumOfDistinctSubstringsInAString {
    /// Question 3
    ///
    /// Problem Description: Implement a program that takes a string 'S' as input and returns the number of distinct substrings of the given string, including the empty substring. Use a trie data structure to accomplish this.
    ///
    /// Note:
    /// 1. A string ‘B’ is considered a substring of a string ‘A’ if ‘B’ can be obtained by deleting zero or more characters from the start and end of ‘A’.
    /// 2. Two strings ‘X’ and ‘Y’ are considered different if either their length is different or there is at least one index ‘i’ such that the character of ‘X’ at index ‘i’ is different from the character of ‘Y’ at index ‘i’ (i.e., X(i) != Y(i)).
    public static void main(String[] args) {
        String s = "striver";
        System.out.println("Given String: " + s);

        // Call the function to
        // count distinct substrings
        Set<String> substrings = countDistinctSubstrings(s);

        int count = 0;

        // Print the distinct substrings
        System.out.println("Distinct Substrings:");
        for (String substr : substrings) {
            System.out.println(substr);
            count++;
        }

        // Count + 1 as we have to count
        // the empty string as well
        System.out.println("Number of distinct substrings: " + (count + 1));

    }

    // brute

    /// Time Complexity: O(N*N)., we generate all possible substrings starting from all possible indices.
    /// Space Complexity: O(N*N), maximum number of substrings that the set can hold.
    static Set<String> countDistinctSubstrings(String s) {
        // Set to store
        // distinct substrings
        Set<String> st = new HashSet<>();

        // Length of the
        // input string
        int n = s.length();

        // Iterate over each
        // character in the string
        for (int i = 0; i < n; i++) {
            // Initialize an empty string
            // to store the current substring
            StringBuilder str = new StringBuilder();

            // Iterate over the remaining characters
            // in the string starting from index i
            for (int j = i; j < n; j++) {
                // Append the current
                // character to the substring
                str.append(s.charAt(j));

                // Insert the current
                // substring into the set
                st.add(str.toString());
            }
        }

        // Return the set containing
        // all distinct substrings
        return st;
    }

    // optimal

    /// Time Complexity: O(N^2), We consider all substrings of a string with length n. For each substring, inserting characters into the Trie takes up to O(n) in the worst case.
    /// Space Complexity: O(N^2), additional space used for storing total number of substring in the trie.


    private static class Node {
        Node[] links = new Node[26];
        // Array of pointers to child nodes,
        // each corresponding to a letter
        // of the alphabet
        boolean flag = false;
        // Flag indicating if the current
        // node represents the end of a substring

        // Method to check if a specific character key
        // exists in the children of the current node
        public boolean containsKey(char ch) {
            // Check if the current node has a child node
            // corresponding to character 'ch'
            return links[ch - 'a'] != null;
        }

        // Method to get the child node corresponding
        // to a specific character key
        public Node get(char ch) {
            // Get the child node
            // corresponding to character 'ch'
            return links[ch - 'a'];
        }

        // Method to insert a new child
        // node with a specific character key
        public void put(char ch, Node node) {
            // Insert a new child
            // node for character 'ch'
            links[ch - 'a'] = node;
        }

        // Method to mark the current
        // node as the end of a substring
        public void setEnd() {
            // Mark the current node
            // as the end of a substring
            flag = true;
        }

        // Method to check if the current
        // node marks the end of a substring
        public boolean isEnd() {
            // Check if the current node
            // marks the end of a substring
            return flag;
        }
    }

    static int countDistinctSubstringsI(String s) {
        // Function to count distinct
        // substrings in the input string 's'
        Node root = new Node();
        // Creating the root
        // node of the trie
        int cnt = 0;
        // Counter to keep track
        // of distinct substrings
        int n = s.length();
        // Length of the input string

        // Nested loops to iterate through all
        // possible substrings of the input string
        for (int i = 0; i < n; i++) {
            // Iterate through each
            // starting position of the substring
            Node node = root;
            // Start from the root for each substring
            for (int j = i; j < n; j++) {
                // Iterate through each character of the substring
                // If the current character is not a child
                // of the current node, insert it as a new child node
                if (!node.containsKey(s.charAt(j))) {
                    node.put(s.charAt(j), new Node());
                    // Insert a new child
                    // node for character s[j]
                    cnt++;
                    // Increment the counter
                    // since a new substring is found
                }
                node = node.get(s.charAt(j));
                // Move to the child node
                // corresponding to character s[j]
            }
        }
        // Return the total count of distinct substrings
        // (+1 to account for the input string itself)
        return cnt + 1;
    }
}
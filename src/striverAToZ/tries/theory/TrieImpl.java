package striverAToZ.tries.theory;

import java.util.ArrayList;
import java.util.List;

public class TrieImpl {

    /// Problem Statement: Implement the Trie class:
    /// Trie(): Initializes the trie object. void insert (String word): Inserts the string word into the trie.
    /// boolean search (String word): Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
    /// boolean startsWith (String prefix): Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.
    public static void main(String[] args) {
        Trie trie = new Trie();
        String[] operations = {"Trie", "insert", "search", "search", "startsWith", "insert", "search"};
        String[][] arguments = {{}, {"apple"}, {"apple"}, {"app"}, {"app"}, {"app"}, {"app"}};

        List<String> output = new ArrayList<>();
        for (int i = 0; i < operations.length; i++) {
            switch (operations[i]) {
                case "Trie":
                    output.add("null");
                    break;
                case "insert":
                    trie.insert(arguments[i][0]);
                    output.add("null");
                    break;
                case "search":
                    output.add(trie.search(arguments[i][0]) ? "true" : "false");
                    break;
                case "startsWith":
                    output.add(trie.startsWith(arguments[i][0]) ? "true" : "false");
                    break;
            }
        }

        for (String res : output) {
            System.out.println(res);
        }
    }

    private static class Node {
        /* Array to store links to child nodes,
        each index represents a letter */
        Node[] links = new Node[26];

        /* Flag indicating if
        the node marks the end
        of a word */
        boolean flag = false;

        /* Check if the node contains
        a specific key (letter) */
        boolean containsKey(char ch) {
            return links[ch - 'a'] != null;
        }

        /* Insert a new node with a specific
        key (letter) into the Trie */
        void put(char ch, Node node) {
            links[ch - 'a'] = node;
        }

        /* Get the node with a specific
        key (letter) from the Trie */
        Node get(char ch) {
            return links[ch - 'a'];
        }

        /* Set the current node
        as the end of a word */
        void setEnd() {
            flag = true;
        }

        /* Check if the
        current node marks
        the end of a word */
        boolean isEnd() {
            return flag;
        }
    }

    // Trie class
    private static class Trie {
        private Node root;

        /* Constructor to
        initialize the
        Trie with an
        empty root node */
        public Trie() {
            root = new Node();
        }

        /* Inserts a word into the Trie
        Time Complexity O(len), where len
        is the length of the word */
        public void insert(String word) {
            Node node = root;
            for (char ch : word.toCharArray()) {
                if (!node.containsKey(ch)) {
                /* Create a new node for
                the letter if not present */
                    node.put(ch, new Node());
                }
                // Move to the next node
                node = node.get(ch);
            }
            // Mark the end of the word
            node.setEnd();
        }

        /* Returns if the word
        is in the trie */
        public boolean search(String word) {
            Node node = root;
            for (char ch : word.toCharArray()) {
                if (!node.containsKey(ch)) {
                /* If a letter is
                not found, the word
                is not in the Trie */
                    return false;
                }
                // Move to the next node
                node = node.get(ch);
            }
        /* Check if the last node
        marks the end of a word */
            return node.isEnd();
        }

        /* Returns if there is any word in the
        trie that starts with the given prefix */
        public boolean startsWith(String prefix) {
            Node node = root;
            for (char ch : prefix.toCharArray()) {
                if (!node.containsKey(ch)) {
                /* If a letter is not
                found, there is
                no word with the
                given prefix */
                    return false;
                }
                // Move to the next node
                node = node.get(ch);
            }
            // Prefix Found
            return true;
        }
    }
}



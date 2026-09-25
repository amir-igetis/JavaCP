package striverAToZ.tries.problems;

public class TrieImplAndAdvanceOps {

    /// Question 1
    ///
    /// Problem Statement: Implement "TRIE” data structure from scratch with the following functions.
    ///
    /// Trie(): Initialize the object of this “TRIE” data structure.
    /// insert(“WORD”): Insert the string “WORD” into this “TRIE” data structure.
    /// countWordsEqualTo(“WORD”): Return how many times this “WORD” is present in this “TRIE”.
    /// countWordsStartingWith(“PREFIX”): Return how many words are there in this “TRIE” that have the string “PREFIX” as a prefix.
    /// erase(“WORD”): Delete one occurrence of the string “WORD” from the “TRIE”.
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("apple");
        System.out.println("Inserting strings 'apple' twice into Trie");
        System.out.println("Count Words Equal to 'apple': " + trie.countWordsEqualTo("apple"));
        System.out.println("Count Words Starting With 'app': " + trie.countWordsStartingWith("app"));
        System.out.println("Erasing word 'apple' from Trie");
        trie.erase("apple");
        System.out.println("Count Words Equal to 'apple': " + trie.countWordsEqualTo("apple"));
        System.out.println("Count Words Starting With 'app': " + trie.countWordsStartingWith("app"));
        System.out.println("Erasing word 'apple' from Trie");
        trie.erase("apple");
        System.out.println("Count Words Starting With 'app': " + trie.countWordsStartingWith("app"));

    }


    /// Time Complexity: O(N), where N is the length of the word or prefix being processed. Each method (inserting a word, counting words equal to a given word, counting words starting with a prefix, and erasing a word) involves traversing the Trie for each character of the input word or prefix. Thus, the time complexity is linear relative to the length of the word or prefix being processed.
    ///
    /// Space Complexity: O(N), where N is the total number of characters across all words inserted into the Trie. The space complexity depends on the number of unique words added to the Trie and the average length of these words.
    private static class Node {
        /* Array to store links to child nodes,
        each index represents a letter */
        private Node[] links;
        /* Counter for number of words
        that end at this node */
        private int cntEndWith;
        /* Counter for number of words
        that have this node as a prefix */
        private int cntPrefix;

        // Constructor
        public Node() {
            links = new Node[26];
            cntEndWith = 0;
            cntPrefix = 0;
        }

        /* Check if the node contains
        a specific key (letter) */
        public boolean containsKey(char ch) {
        /* Check if the link corresponding
        to the character exists */
            return links[ch - 'a'] != null;
        }

        /* Get the node with a specific
        key (letter) from the Trie */
        public Node get(char ch) {
        /* Return the link
        corresponding to the character */
            return links[ch - 'a'];
        }

        /* Insert a new node with a specific
        key (letter) into the Trie */
        public void put(char ch, Node node) {
        /* Set the link corresponding to
        the character to the provided node */
            links[ch - 'a'] = node;
        }

        /* Increment the count of words
        that end at this node */
        public void increaseEnd() {
            /* Increment the counter */
            cntEndWith++;
        }

        /* Increment the count of words
        that have this node as a prefix */
        public void increasePrefix() {
            /* Increment the counter */
            cntPrefix++;
        }

        /* Decrement the count of words
        that end at this node */
        public void deleteEnd() {
            /* Decrement the counter */
            cntEndWith--;
        }

        /* Decrement the count of words
        that have this node as a prefix */
        public void reducePrefix() {
            /* Decrement the counter */
            cntPrefix--;
        }

        public int getEnd() {
            return cntEndWith;
        }

        public int getPrefix() {
            return cntPrefix;
        }
    }

    // Trie class
    private static class Trie {
        private Node root;

        /* Constructor to initialize the
        Trie with an empty root node */
        public Trie() {
            root = new Node();
        }

        /* Inserts a word into the Trie
        Time Complexity O(len), where len
        is the length of the word */
        public void insert(String word) {
            /* Start from the root node */
            Node node = root;
        /* Iterate over each
        character in the word */
            for (int i = 0; i < word.length(); i++) {
                if (!node.containsKey(word.charAt(i))) {
                /* Create a new node
                for the character */
                    node.put(word.charAt(i), new Node());
                }
            /* Move to the child node
            corresponding to the character */
                node = node.get(word.charAt(i));
            /* Increment the prefix
            count for the node */
                node.increasePrefix();
            }
        /* Increment the end count
        for the last node of the word */
            node.increaseEnd();
        }

        /* Returns the number of words
        equal to a given word */
        public int countWordsEqualTo(String word) {
            /* Start from the root node */
            Node node = root;
            /* Iterate over each character in the word */
            for (int i = 0; i < word.length(); i++) {
                if (node.containsKey(word.charAt(i))) {
                /* Move to the child node
                corresponding to the character */
                    node = node.get(word.charAt(i));
                } else {
                /* Return 0 if the
                character is not found */
                    return 0;
                }
            }
        /* Return the count of
        words ending at the node */
            return node.getEnd();
        }

        /* Returns the number of words
        starting with a given prefix */
        public int countWordsStartingWith(String word) {
            /* Start from the root node */
            Node node = root;
            /* Iterate over each character in the prefix */
            for (int i = 0; i < word.length(); i++) {
                if (node.containsKey(word.charAt(i))) {
                /* Move to the child node
                corresponding to the character */
                    node = node.get(word.charAt(i));
                } else {
                /* Return 0 if the
                character is not found */
                    return 0;
                }
            }
        /* Return the count of
        words with the prefix */
            return node.getPrefix();
        }

        /* Erases a word from the Trie */
        public void erase(String word) {
            /* Start from the root node */
            Node node = root;
            /* Iterate over each character in the word */
            for (int i = 0; i < word.length(); i++) {
                if (node.containsKey(word.charAt(i))) {
                /* Move to the child node
                corresponding to the character */
                    node = node.get(word.charAt(i));
                /* Decrement the prefix
                count for the node */
                    node.reducePrefix();
                } else {
                /* Return if the
                character is not found */
                    return;
                }
            }
        /* Decrement the end count
        for the last node of the word */
            node.deleteEnd();
        }
    }
}
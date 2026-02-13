package LeetcodeInterview.trie;

import java.util.HashMap;

public class DesignAddAndSearchWordsDataStruct {
    private class TrieNode {
        HashMap<Character, TrieNode> children = new HashMap<>();
        boolean isEnd = false;
    }

    private TrieNode root;

    public DesignAddAndSearchWordsDataStruct() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node.children.putIfAbsent(c, new TrieNode());
            node = node.children.get(c);
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        return dfsSearch(word, 0, root);
    }

    private boolean dfsSearch(String word, int index, TrieNode node) {
        if (index == word.length()) return node.isEnd;

        char c = word.charAt(index);
        if (c == '.') {
            for (TrieNode child : node.children.values()) {
                if (dfsSearch(word, index + 1, child)) return true;
            }
        } else {
            if (!node.children.containsKey(c)) return false;
            return dfsSearch(word, index + 1, node.children.get(c));
        }
        return false;
    }
}

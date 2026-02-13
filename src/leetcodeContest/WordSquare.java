package leetcodeContest;

import java.util.*;

public class WordSquare {
    public static void main(String[] args) {
        String[] words = {"able", "area", "echo", "also"};
        List<List<String>> ans = wordSquares(words);
        for (List<String> i : ans) {
            for (String j : i)
                System.out.print(j + " ");
            System.out.println();
        }
    }

//    static List<List<String>> wordSquares(String[] words) {
//        Map<Character, Integer> mp = new HashMap<>();
//        for (int i = 0; i < words.length; i++) {
//            char first = words[i].charAt(0);
//            char end = words[i].charAt(words[i].length() - 1);
//            mp.put(first, mp.getOrDefault(first, 0) + 1);
//            mp.put(end, mp.getOrDefault(end, 0) + 1);
//        }
//        for (Entry e : mp.entrySet()) {
//            if (e % 2 != 0)
//                return false;
//        }
//        return true;
//    }

    private static class Node {
        Node[] child = new Node[26];
        List<String> words = new ArrayList<>();

    }

    private static class Trie {
        Node root = new Node();

        void insert(String word) {
            Node node = root;
            for (char ch : word.toCharArray()) {
                int idx = ch - 'a';
                if (node.child[idx] == null) {
                    node.child[idx] = new Node();
                }
                node = node.child[idx];
                node.words.add(word);
            }
        }

        List<String> prefix(String prefix) {
            Node node = root;
            for (char ch : prefix.toCharArray()) {
                int idx = ch - 'a';
                if (node.child[idx] == null)
                    return Collections.emptyList();
                node = node.child[idx];
            }
            return node.words;
        }
    }

    static List<List<String>> res = new ArrayList<>();
    static int n;
    static Trie trie = new Trie();

    public static List<List<String>> wordSquares(String[] words) {
        n = words[0].length();
        for (String word : words)
            trie.insert(word);
        for (String word : words) {
            List<String> square = new ArrayList<>();
            square.add(word);
            backtrack(square);
        }
        return res;
    }

    private static void backtrack(List<String> square) {
        if (square.size() == n) {
            res.add(new ArrayList<>(square));
            return;
        }

        int idx = square.size();
        StringBuilder prefix = new StringBuilder();
        for (String s : square)
            prefix.append(s.charAt(idx));

        for (String i : trie.prefix(prefix.toString())) {
            square.add(i);
            backtrack(square);
            square.remove(square.size() - 1);
        }
    }
}

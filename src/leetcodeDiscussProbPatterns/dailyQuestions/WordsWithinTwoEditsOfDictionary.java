package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.ArrayList;
import java.util.List;

public class WordsWithinTwoEditsOfDictionary {
    public static void main(String[] args) {
        String[] queries = {"word", "note", "ants", "wood"}, dictionary = {"wood", "joke", "moat"};
        System.out.println(twoEditWordsI(queries, dictionary));
    }

    // brute force tc O(qkn) sc O(1)
    static List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> ans = new ArrayList<>();
        for (String query : queries) {
            for (String s : dictionary) {
                int dis = 0;
                for (int i = 0; i < query.length(); i++) {
                    if (query.charAt(i) != s.charAt(i)) {
                        dis++;
                    }
                }
                if (dis <= 2) {
                    ans.add(query);
                    break;
                }
            }
        }
        return ans;
    }

    // using Trie


    private static class TrieNode {

        TrieNode[] child = new TrieNode[26];
        boolean isEnd = false;
    }

    static TrieNode root = new TrieNode();

    static void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.child[idx] == null) node.child[idx] = new TrieNode();
            node = node.child[idx];
        }
        node.isEnd = true;
    }

    static boolean dfs(String word, int i, TrieNode node, int cnt) {
        if (cnt > 2 || node == null) return false;

        if (i == word.length()) return node.isEnd;

        int idx = word.charAt(i) - 'a';

        // no changes made
        if (node.child[idx] != null) {
            if (dfs(word, i + 1, node.child[idx], cnt)) return true;
        }

        // made changes
        if (cnt < 2) {
            for (int c = 0; c < 26; c++) {
                if (c == idx) continue;
                if (node.child[c] != null) {
                    if (dfs(word, i + 1, node.child[c], cnt + 1)) return true;
                }
            }
        }

        return false;
    }

    static List<String> twoEditWordsI(String[] queries, String[] dictionary) {
        for (String w : dictionary) insert(w);

        List<String> res = new ArrayList<>();
        for (String q : queries) {
            if (dfs(q, 0, root, 0)) {
                res.add(q);
            }
        }
        return res;
    }
}



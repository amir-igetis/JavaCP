package striverAToZ.graphs.dfsBfsProbs;

import java.util.*;

// Question No. - 10
public class WordLadder {
    public static void main(String[] args) {
        List<String> wordList = Arrays.asList("des", "der", "dfr", "dgt", "dfs");
        String startWord = "der", targetWord = "dfs";

        System.out.println(wordLadderLength(startWord, targetWord, wordList));

    }

    static int wordLadderLength(String startWord, String targetWord, List<String> wordList) {
        Queue<Pair<String, Integer>> q = new LinkedList<>();
        q.add(new Pair<>(startWord, 1));

        Set<String> st = new HashSet<>(wordList);
        st.remove(startWord);

        while (!q.isEmpty()) {
            String word = q.peek().getKey();
            int steps = q.peek().getValue();
            q.poll();

            // If target word is found
            if (word.equals(targetWord)) return steps;

            // Try changing every character
            for (int i = 0; i < word.length(); i++) {
                char[] arr = word.toCharArray();
                char original = arr[i];
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    arr[i] = ch;
                    String newWord = new String(arr);
                    if (st.contains(newWord)) {
                        st.remove(newWord);
                        q.add(new Pair<>(newWord, steps + 1));
                    }
                }
                arr[i] = original;
            }
        }
        return 0;
    }

    // soln for https://leetcode.com/problems/word-ladder/description/
    static int ladderLengthI(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        if (!set.contains(endWord))
            return 0;

        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);

        Set<String> visited = new HashSet<>();
        queue.add(beginWord);

        int changes = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                if (word.equals(endWord))
                    return changes;

                for (int j = 0; j < word.length(); j++) {
                    for (int k = 'a'; k <= 'z'; k++) {
                        char arr[] = word.toCharArray();
                        arr[j] = (char) k;

                        String str = new String(arr);
                        if (set.contains(str) && !visited.contains(str)) {
                            queue.add(str);
                            visited.add(str);
                        }
                    }
                }
            }
            ++changes;
        }
        return 0;
    }

    // Utility class to simulate pair in Java
    private static class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}

package striverAToZ.graphs.dfsBfsProbs;

import striverAToZ.heaps.hardProbs.KthElemInAStreamOfRunningInt;

import java.util.*;

public class WordLadderII {
    public static void main(String[] args) {
        List<String> wordList = Arrays.asList("des", "der", "dfr", "dgt", "dfs");
        String startWord = "der", targetWord = "dfs";

        List<List<String>> ans = findSequences(startWord, targetWord, new ArrayList<>(wordList));

        if (ans.isEmpty()) {
            System.out.println(-1);
        } else {
            ans.sort((a, b) -> String.join("", a).compareTo(String.join("", b)));
            for (List<String> sequence : ans) {
                System.out.println(String.join(" ", sequence));
            }
        }
    }

    static List<List<String>> findSequences(String beginWord, String endWord, List<String> wordList) {
        // Use a HashSet for fast lookup and removal
        Set<String> st = new HashSet<>(wordList);

        // Queue stores paths (each path is a list of words)
        Queue<List<String>> q = new LinkedList<>();
        q.offer(new ArrayList<>(Arrays.asList(beginWord)));

        // Track words used at the current BFS level
        List<String> usedOnLevel = new ArrayList<>();
        usedOnLevel.add(beginWord);
        int level = 0;

        // Resultant list of sequences
        List<List<String>> ans = new ArrayList<>();

        while (!q.isEmpty()) {
            List<String> vec = q.poll();

            // Remove used words when moving to a deeper BFS level
            if (vec.size() > level) {
                level++;
                for (String used : usedOnLevel) {
                    st.remove(used);
                }
            }

            String word = vec.get(vec.size() - 1);

            // If we found a sequence that reaches the endWord
            if (word.equals(endWord)) {
                if (ans.isEmpty()) {
                    ans.add(new ArrayList<>(vec));
                } else if (ans.get(0).size() == vec.size()) {
                    ans.add(new ArrayList<>(vec));
                }
            }

            // Try changing each character of the current word
            char[] wordArr = word.toCharArray();
            for (int i = 0; i < wordArr.length; i++) {
                char original = wordArr[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    wordArr[i] = c;
                    String newWord = new String(wordArr);

                    // If this transformed word exists in the set
                    if (st.contains(newWord)) {
                        vec.add(newWord);
                        q.offer(new ArrayList<>(vec));
                        usedOnLevel.add(newWord);
                        vec.remove(vec.size() - 1);
                    }
                }
                wordArr[i] = original;
            }
        }

        return ans;
    }
}



package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.*;

public class EvaluateTheBucketPairsOfAString {
    public static void main(String[] args) {
        String s = "(name)is(age)yearsold";
        List<List<String>> knowledge = List.of(
                new ArrayList<>(Arrays.asList("name", "bob")),
                new ArrayList<>(Arrays.asList("age", "two"))
        );
        System.out.println(evaluate(s, knowledge));
    }

    // Hash Table

    /// Let n be the length of s, and let m be the total length of all strings in knowledge.
    ///
    /// Time complexity: O(n+m).
    ///
    /// Building the hash table takes O(m) time. Traversing s, extracting keys, looking them up in the hash table, and appending characters and values to the result string takes O(n+m) time. Thus, the total time complexity is O(n+m).
    ///
    /// Space complexity: O(n+m).
    ///
    /// The hash table stores all key-value pairs from knowledge, which requires O(m) space. The output string (and key buffer) requires O(n+m) space.
    static String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> dict = new HashMap<>();
        for (List<String> kd : knowledge) {
            dict.put(kd.get(0), kd.get(1));
        }
        boolean addKey = false;
        StringBuilder key = new StringBuilder();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                addKey = true;
            } else if (c == ')') {
                if (dict.containsKey(key.toString())) {
                    res.append(dict.get(key.toString()));
                } else {
                    res.append('?');
                }
                addKey = false;
                key.setLength(0);
            } else if (addKey) {
                key.append(c);
            } else {
                res.append(c);
            }
        }
        return res.toString();

    }
}

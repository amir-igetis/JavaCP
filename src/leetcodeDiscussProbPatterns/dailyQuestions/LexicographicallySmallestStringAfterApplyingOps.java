package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class LexicographicallySmallestStringAfterApplyingOps {
    public static void main(String[] args) {
        String s = "5525";
        int a = 9, b = 2;
        System.out.println(findLexSmallestString(s, a, b));
    }

    static String findLexSmallestString(String s, int a, int b) {
        Set<String> vis = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        String smallest = s;

        q.offer(s);
        vis.add(s);

        while (!q.isEmpty()) {
            String curr = q.poll();
            if (curr.compareTo(smallest) < 0)
                smallest = curr;

            String added = addOperation(curr, a);
            if (vis.add(added))
                q.offer(added);

            String rotated = rotateOperations(curr, b);
            if (vis.add(rotated))
                q.offer(rotated);
        }
        return smallest;
    }

    private static String addOperation(String s, int a) {
        char[] ch = s.toCharArray();
        for (int i = 1; i < ch.length; i += 2) {
            int num = (ch[i] - '0' + a) % 10;
            ch[i] = (char) (num + '0');
        }
        return new String(ch);
    }

    private static String rotateOperations(String s, int b) {
        int n = s.length();
        b = b % n;
        return s.substring(n - b) + s.substring(0, n - b);
    }
}

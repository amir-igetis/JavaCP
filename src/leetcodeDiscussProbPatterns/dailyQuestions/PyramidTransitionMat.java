package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.*;

public class PyramidTransitionMat {
    public static void main(String[] args) {
        String bottom = "BCD";
        List<String> allowed = List.of("BCC", "CDE", "CEA", "FFF");
        System.out.println(pyramidTransition(bottom, allowed));
    }

    //    DFS
    static int[][] T = new int[7][7];
    static Set<String> seen = new HashSet<>();

    static boolean pyramidTransition(String bottom, List<String> allowed) {
        T = new int[7][7];
        for (String a : allowed)
            T[a.charAt(0) - 'A'][a.charAt(1) - 'A'] |=
                    1 << (a.charAt(2) - 'A');
        return dfs(bottom, "");

    }

    private static boolean dfs(String curr, String next) {
        if (curr.length() == 1) return true;

        if (next.length() + 1 == curr.length()) {
            if (seen.contains(next)) return false;
            seen.add(next);
            return dfs(next, "");
        }

        int i = next.length();
        int mask = T[curr.charAt(i) - 'A'][curr.charAt(i + 1) - 'A'];

        for (int b = 0; b < 7; b++) {
            if (((mask >> b) & 1) != 0) {
                if (dfs(curr, next + (char) ('A' + b)))
                    return true;
            }
        }
        return false;
    }

    // using map

    static Map<String, List<Character>> mp = new HashMap<>();
    static Set<String> seenI = new HashSet<>();

    static boolean pyramidTransitionI(String bottom, List<String> allowed) {
        for (String s : allowed) {
            String key = s.substring(0, 2);
            mp.computeIfAbsent(key, k -> new ArrayList<>())
                    .add(s.charAt(2));
        }
        return dfsI(bottom, "");
    }

    private static boolean dfsI(String curr, String next) {
        if (curr.length() == 1) return true;

        if (next.length() + 1 == curr.length()) {
            if (!seenI.add(next)) return false;
            return dfsI(next, "");
        }

        int i = next.length();
        String key = curr.substring(i, i + 2);

        if (!mp.containsKey(key)) return false;

        for (char c : mp.get(key)) {
            if (dfsI(curr, next + c)) return true;
        }
        return false;
    }
}

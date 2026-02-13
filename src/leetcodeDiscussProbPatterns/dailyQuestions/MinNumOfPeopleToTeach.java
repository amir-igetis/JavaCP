package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class MinNumOfPeopleToTeach {
    public static void main(String[] args) {
        int n = 2;
        int[][] languages = {{1}, {2}, {1, 2}}, friendships = {{1, 2}, {1, 3}, {2, 3}};

        System.out.println(minimumTeachings(n, languages, friendships));
    }

    static int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        int m = languages.length;
        Set<Integer>[] userLang = new HashSet[m + 1];
        for (int i = 0; i < m; i++) {
            userLang[i + 1] = new HashSet<>();
            for (int lang : languages[i]) {
                userLang[i + 1].add(lang);
            }
        }

        Set<Integer> candidates = new HashSet<>();
        for (int[] f : friendships) {
            int u = f[0], v = f[1];
            if (!canCommunicate(userLang[u], userLang[v])) {
                candidates.add(u);
                candidates.add(v);
            }
        }

        if (candidates.isEmpty()) return 0;

        int[] count = new int[n + 1];
        for (int user : candidates) {
            for (int lang : userLang[user])
                count[lang]++;
        }

        int maxKnown = 0;
        for (int lang = 1; lang <= n; lang++) {
            maxKnown = Math.max(maxKnown, count[lang]);
        }

        return candidates.size() - maxKnown;
    }

    private static boolean canCommunicate(Set<Integer> a, Set<Integer> b) {
        for (int lang : a) {
            if (b.contains(lang))
                return true;
        }
        return false;
    }
}

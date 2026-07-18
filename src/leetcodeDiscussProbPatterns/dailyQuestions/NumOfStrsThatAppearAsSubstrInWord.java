package leetcodeDiscussProbPatterns.dailyQuestions;

public class NumOfStrsThatAppearAsSubstrInWord {
    public static void main(String[] args) {
        String[] patterns = {"a", "abc", "bc", "d"};
        String word = "abc";
        System.out.println(numOfStrings(patterns, word));
    }

    // brute force O(n * sigma_i m_i) and sc O(1)
    static int numOfStrings(String[] patterns, String word) {
        int res = 0;
        for (String pattern : patterns) {
            if (check(pattern, word)) {
                res++;
            }
        }
        return res;
    }

    private static boolean check(String pattern, String word) {
        int m = pattern.length();
        int n = word.length();
        for (int i = 0; i + m <= n; i++) {
            boolean flag = true;
            for (int j = 0; j < m; j++) {
                if (word.charAt(i + j) != pattern.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return true;
            }
        }
        return false;
    }


    // kmp algo tc O(n*k + sigma_i m_i) and sc O(max_i(m_i))
    static int numOfStringsI(String[] patterns, String word) {
        int res = 0;
        for (String pattern : patterns) {
            if (checkI(pattern, word)) {
                res++;
            }
        }
        return res;
    }

    private static boolean checkI(String pattern, String word) {
        int m = pattern.length();
        int n = word.length();

        // generate the prefix array of the pattern
        int[] pi = new int[m];
        for (int i = 1, j = 0; i < m; i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            pi[i] = j;
        }

        // using prefix arrays for matching
        for (int i = 0, j = 0; i < n; i++) {
            while (j > 0 && word.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];
            }
            if (word.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            if (j == m) {
                return true;
            }
        }
        return false;
    }
}

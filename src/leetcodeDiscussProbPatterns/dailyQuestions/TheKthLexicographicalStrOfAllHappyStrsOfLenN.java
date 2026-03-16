package leetcodeDiscussProbPatterns.dailyQuestions;

public class TheKthLexicographicalStrOfAllHappyStrsOfLenN {
    public static void main(String[] args) {
        int n = 1, k = 3;
        System.out.println(getHappyStringI(n, k));
    }

    static String getHappyString(int n, int k) {
        int prem = 1 << (n - 1);
        if (k > 3 * prem)
            return "";
        int ch = 'a' + (k - 1) / prem;
        StringBuilder sb = new StringBuilder(Character.toString(ch));
        while (prem > 1) {
            k = (k - 1) % prem + 1;
            prem >>= 1;
            ch = (k - 1) / prem == 0 ?
                    'a' + (ch == 'a' ? 1 : 0) : 'b' + (ch != 'c' ? 1 : 0);
            sb.append((char) ch);
        }
        return sb.toString();
    }

    // another way using dfs


    static int count = 0;
    static String result = "";

    static String getHappyStringI(int n, int k) {
        dfs("", n, k);
        return result;
    }

    private static void dfs(String curr, int n, int k) {
        if (curr.length() == n) {
            count++;
            if (count == k) {
                result = curr;
            }
            return;
        }

        char[] chars = {'a', 'b', 'c'};

        for (char c : chars) {
            if (curr.length() > 0 && curr.charAt(curr.length() - 1) == c)
                continue;

            dfs(curr + c, n, k);

            if (!result.equals(""))  // stop early once found
                return;
        }
    }
}

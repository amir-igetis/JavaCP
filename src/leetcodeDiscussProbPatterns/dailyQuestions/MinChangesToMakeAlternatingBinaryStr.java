package leetcodeDiscussProbPatterns.dailyQuestions;

public class MinChangesToMakeAlternatingBinaryStr {
    public static void main(String[] args) {
        String s = "0100";
        System.out.println(minOperationsI(s));
    }

    static int minOperations(String s) {
        int res = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) - '0' != i % 2)
                res++;

        }

        return Math.min(res, n - res);
    }

    // another soln

    static int minOperationsI(String s) {
        char ch = s.charAt(0);
        int count1 = count(s, ch);
        int count2 = count(s, ch == '0' ? '1' : '0') + 1;
        return Math.min(count1, count2);
    }

    private static int count(String s, char ch) {
        int count = 0;
        for (int i = 1; i < s.length(); i++) {
            char curr = s.charAt(i);
            if (curr == ch) {
                count++;
                ch = ch == '0' ? '1' : '0';
            } else {
                ch = curr;
            }
        }
        return count;
    }
}

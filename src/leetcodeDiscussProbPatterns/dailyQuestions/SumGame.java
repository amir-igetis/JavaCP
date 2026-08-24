package leetcodeDiscussProbPatterns.dailyQuestions;

public class SumGame {
    public static void main(String[] args) {
        String num = "?3295???";
        System.out.println(sumGame(num));
    }

    // Guess + Mathematical Induction Verification

    /// Let n be the length of the string num.
    ///
    /// Time complexity: O(n).
    ///
    /// Space complexity: O(n).

    static boolean sumGame(String num) {
        int n = num.length();
        int[] left = get(num.substring(0, n / 2));
        int[] right = get(num.substring(n / 2, n));

        int n0 = left[0],
                q0 = left[1];
        int n1 = right[0],
                q1 = right[1];

        return (q0 + q1) % 2 == 1 || n0 - n1 != ((q1 - q0) * 9) / 2;
    }

    private static int[] get(String s) {
        int nn = 0,
                qq = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '?') {
                qq++;
            } else {
                nn += ch - '0';
            }
        }
        return new int[]{nn, qq};

    }
}

package leetcodeDiscussProbPatterns.dailyQuestions;

public class NumOfSubstrWithOnlyOnes {
    public static void main(String[] args) {
        String s = "0110111";
        System.out.println(numSub(s));
    }

    static int numSub(String s) {
        long mod = 1_000_000_007;
        long count = 0, res = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '1') {
                count++;
                res = (res + count) % mod;
            } else {
                count = 0;
            }
        }

        return (int) res;
    }
}

package leetcodeDiscussProbPatterns.dailyQuestions;

public class CountBinarySubstr {
    public static void main(String[] args) {
        String s = "00110011";
        System.out.println(countBinarySubstrings(s));
    }

    static int countBinarySubstrings(String s) {
        int curr = 1, pre = 0, res = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1))
                curr++;
            else {
                res += Math.min(curr, pre);
                pre = curr;
                curr = 1;
            }
        }
        return res + Math.min(curr, pre);
    }
}

package leetcodeDiscussProbPatterns.dailyQuestions;

public class CheckIfDigitsAreEqualInStrAfterOpsI {
    public static void main(String[] args) {
        String s = "39020";
        System.out.println(hasSameDigits(s));
    }

    static boolean hasSameDigits(String s) {
        while (s.length() > 2) {
            StringBuilder next = new StringBuilder();
            for (int i = 0; i < s.length() - 1; i++) {
                int a = s.charAt(i) - '0';
                int b = s.charAt(i + 1) - '0';
                int sum = (a + b) % 10;
                next.append(sum);
            }
            s = next.toString();
        }

        return s.charAt(0) == s.charAt(1);
    }
}

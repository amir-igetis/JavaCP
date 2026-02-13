package leetcodeContest;

public class LargestEvenNumber {
    public static void main(String[] args) {
        String s = "1112111";
        System.out.println(largestEven(s));
    }

    static String largestEven(String s) {
        for (int i = s.length() - 1; i >= 0; i--) {
            int digit = s.charAt(i) - '0';
            if (digit % 2 == 0)
                return s.substring(0, i + 1);
        }
        return "";
    }
}

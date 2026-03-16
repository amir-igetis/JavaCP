package leetcodeDiscussProbPatterns.dailyQuestions;

public class NumberOfStepsToReduceANumInBinaryRepresentationToOne {
    public static void main(String[] args) {
        String s = "1101";
        System.out.println(numSteps(s));
    }

    static int numSteps(String s) {
        int res = 0, carry = 0;
        for (int i = s.length() - 1; i > 0; i--) {
            res++;
            if (s.charAt(i) - '0' + carry == 1) {
                carry = 1;
                res++;
            }
        }
        return res + carry;
    }
}

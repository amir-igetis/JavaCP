package leetcodeDiscussProbPatterns.dailyQuestions;

public class CheckIfBinaryStrHasAtMostOneSegmentOfOnes {
    public static void main(String[] args) {
        String s = "1001";
        System.out.println(checkOnesSegment(s));
    }

    static boolean checkOnesSegment(String s) {
        return !s.contains("01");
    }
}

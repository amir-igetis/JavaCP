package leetcodeDiscussProbPatterns.dailyQuestions;

public class CountOddNumsInAnIntervalRange {
    public static void main(String[] args) {
        int low = 8, high = 10;
        System.out.println(countOdds(low, high));
    }


//    using number theory
    static int countOdds(int low, int high) {
        int ans = ((high + 1 ) / 2) - (low / 2);
        return ans;
    }

}

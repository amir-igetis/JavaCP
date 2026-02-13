package leetcodeDiscussProbPatterns.dailyQuestions;

public class CalculateMoneyInLeetcodeBank {
    public static void main(String[] args) {
        int n = 4;
        System.out.println(totalMoney(n));
    }

    static int totalMoney(int n) {
        int fullWeeks = n / 7, remainingDays = n % 7;
        int totalMoney = 28 * fullWeeks + 7 * (fullWeeks - 1) * fullWeeks / 2;
        totalMoney += (fullWeeks + 1 + fullWeeks + remainingDays) * remainingDays / 2;
        return totalMoney;
    }
}

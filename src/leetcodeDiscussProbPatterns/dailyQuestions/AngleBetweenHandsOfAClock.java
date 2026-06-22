package leetcodeDiscussProbPatterns.dailyQuestions;

public class AngleBetweenHandsOfAClock {
    public static void main(String[] args) {
        int hour = 12, minutes = 30;
        System.out.println(angleClock(hour, minutes));
    }

    static double angleClock(int hour, int minutes) {
        double x = hour + (minutes / 60.0);
        double diff = (11.0 * x) % 12.0;
        return Math.min(diff, 12.0 - diff) * 30.0;
    }
}

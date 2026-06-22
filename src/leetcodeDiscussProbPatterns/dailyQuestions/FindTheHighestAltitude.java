package leetcodeDiscussProbPatterns.dailyQuestions;

public class FindTheHighestAltitude {
    public static void main(String[] args) {
        int[] gain = {-5, 1, 5, 0, -7};
        System.out.println(largestAltitude(gain));
    }

    //    prefix sum tc O(n) & sc O(1)
    static int largestAltitude(int[] gain) {
        int currAltitude = 0;
        int highestPoint = currAltitude;
        for (int altitudeGain : gain) {
            currAltitude += altitudeGain;
            highestPoint = Math.max(highestPoint, currAltitude);
        }
        return highestPoint;
    }
}

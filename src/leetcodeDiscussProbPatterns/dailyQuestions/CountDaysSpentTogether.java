package leetcodeDiscussProbPatterns.dailyQuestions;

public class CountDaysSpentTogether {
    public static void main(String[] args) {
        String arriveAlice = "08-15", leaveAlice = "08-18", arriveBob = "08-16", leaveBob = "08-19";
        System.out.println(countDaysTogether(arriveAlice, leaveAlice, arriveBob, leaveBob));
    }


    // Convert Dates to Days of the Year and Calculate the Difference

    /// Time complexity: O(1).
    ///
    /// Computing the prefix sums takes constant time since there are only 12 months in a year. Parsing the four fixed-length date strings (5 characters each) and finding the overlap also takes constant time. Thus, the total time complexity is O(1).
    ///
    /// Space complexity: O(1).
    ///
    /// The array storing the number of days in each month and its prefix sum array require fixed sizes of 12 and 13, so the space complexity is constant
    static int countDaysTogether(
            String arriveAlice,
            String leaveAlice,
            String arriveBob,
            String leaveBob
    ) {
        int[] datesOfMonths = {
                31,
                28,
                31,
                30,
                31,
                30,
                31,
                31,
                30,
                31,
                30,
                31,
        };
        int[] prefixSum = new int[13];
        for (int i = 0; i < 12; i++) {
            prefixSum[i + 1] = prefixSum[i] + datesOfMonths[i];
        }

        int arriveAliceDay = calculateDayOfYear(arriveAlice, prefixSum);
        int leaveAliceDay = calculateDayOfYear(leaveAlice, prefixSum);
        int arriveBobDay = calculateDayOfYear(arriveBob, prefixSum);
        int leaveBobDay = calculateDayOfYear(leaveBob, prefixSum);
        return Math.max(
                0,
                Math.min(leaveAliceDay, leaveBobDay) -
                        Math.max(arriveAliceDay, arriveBobDay) +
                        1
        );
    }

    private static int calculateDayOfYear(String day, int[] prefixSum) {
        int month = Integer.parseInt(day.substring(0, 2));
        int date = Integer.parseInt(day.substring(3));
        return prefixSum[month - 1] + date;

    }
}

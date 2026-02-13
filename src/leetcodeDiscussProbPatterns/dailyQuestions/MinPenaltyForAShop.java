package leetcodeDiscussProbPatterns.dailyQuestions;

public class MinPenaltyForAShop {
    public static void main(String[] args) {
        String customers = "YYNY";
        System.out.println(bestClosingTimeII(customers));
    }

    static int bestClosingTime(String customers) {
        int res = 0, profit = 0, maxProfit = 0;
        for (int i = 0; i < customers.length(); i++) {
            if (customers.charAt(i) == 'Y') {
                profit += 1;

            } else {
                profit -= 1;
            }
            if (profit > maxProfit) {
                maxProfit = profit;
                res = i + 1;
            }
        }
        return res;
    }

    //    Two passes (leetcode)
    static int bestClosingTimeI(String customers) {
        int currPenalty = 0;
        for (int i = 0; i < customers.length(); i++) {
            if (customers.charAt(i) == 'Y')
                currPenalty++;
        }

        int minPenalty = currPenalty;
        int earliestHour = 0;
        for (int i = 0; i < customers.length(); i++) {
            char ch = customers.charAt(i);

            if (ch == 'Y')
                currPenalty--;
            else currPenalty++;

            if (currPenalty < minPenalty) {
                earliestHour = i + 1;
                minPenalty = currPenalty;
            }
        }
        return earliestHour;
    }

    //    One Pass
    static int bestClosingTimeII(String customers) {
        int minPenalty = 0, currPenalty = 0;
        int earliestHour = 0;
        for (int i = 0; i < customers.length(); i++) {
            char ch = customers.charAt(i);
            if (ch == 'Y') {
                currPenalty--;
            } else {
                currPenalty++;
            }

            if (currPenalty < minPenalty) {
                earliestHour = i + 1;
                minPenalty = currPenalty;
            }
        }
        return earliestHour;
    }
}

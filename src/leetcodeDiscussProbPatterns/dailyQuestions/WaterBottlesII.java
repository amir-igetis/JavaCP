package leetcodeDiscussProbPatterns.dailyQuestions;

public class WaterBottlesII {
    public static void main(String[] args) {
        int numBottles = 13, numExchange = 6;
        System.out.println(maxBottlesDrunk(numBottles, numExchange));
    }

    static int maxBottlesDrunk(int numBottles, int numExchange) {
        int full = numBottles, empty = 0, totalDrunk = 0;

        while (full > 0) {
            totalDrunk += full;
            empty += full;
            full = 0;

            if (empty >= numExchange) {
                empty -= numExchange;
                full += 1;
                numExchange++;
            }
        }
        return totalDrunk;
    }
}

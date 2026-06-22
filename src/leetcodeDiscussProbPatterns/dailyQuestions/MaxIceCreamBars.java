package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;

public class MaxIceCreamBars {
    public static void main(String[] args) {
        int[] costs = {1, 3, 2, 4, 1};
        int coins = 7;
        System.out.println(maxIceCream(costs, coins));
    }

    static int maxIceCream(int[] costs, int coins) {
        int maxCost = 0;
        for (int cost : costs) {
            maxCost = Math.max(maxCost, cost);
        }

        // Step 2: Build the counting sort frequency array
        int[] freq = new int[maxCost + 1];
        for (int cost : costs) {
            freq[cost]++;
        }

        int iceCreamsBought = 0;

        // Step 3: Greedily buy ice creams from the cheapest to the most expensive
        for (int cost = 1; cost <= maxCost; cost++) {
            // If there are no ice creams at this price, skip
            if (freq[cost] == 0) {
                continue;
            }

            // If we can't even afford one ice cream at the current cheapest price, stop
            if (coins < cost) {
                break;
            }

            // Figure out how many we can actually buy:
            // Either we buy all available at this price, or as many as our coins allow
            int count = Math.min(freq[cost], coins / cost);

            // Deduct the total price from our coins and add to our tally
            coins -= cost * count;
            iceCreamsBought += count;
        }

        return iceCreamsBought;
    }
}

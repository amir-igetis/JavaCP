package striverAToZ.greedyAlgo.mediumHard;

import java.util.Arrays;

public class Candy {
    public static void main(String[] args) {
        int[] ratings = {1, 0, 5};
        System.out.println(candyII(ratings));

    }

    // brute force tc O(n^2) sc O(n)
    static int candy(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];
        Arrays.fill(candies, 1);
        boolean updated = true;
        while (updated) {
            updated = false;
            for (int i = 1; i < n; i++) {
                if (ratings[i] > ratings[i - 1] && candies[i] <= candies[i - 1]) {
                    candies[i] = candies[i - 1] + 1;
                    updated = true;
                }
            }
            for (int i = n - 2; i >= 0; i--) {
                if (ratings[i] > ratings[i + 1] && candies[i] <= candies[i + 1]) {
                    candies[i] = candies[i + 1] + 1;
                    updated = true;
                }
            }
        }
        int sum = 0;
        for (int i : candies)
            sum += i;
        return sum;

//        return accumulate(candies.begin(), candies.end(), 0);
    }

    // better approach tc O(n) sc O(n)
    static int candyI(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];
        Arrays.fill(candies, 1);
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1])
                candies[i] = candies[i - 1] + 1;
        }

        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1])
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
        }

        int sum = 0;
        for (int i : candies)
            sum += i;
        return sum;
//        return accumulate(candies.begin(), candies.end(), 0);
    }

    // optimal tc O(n) & sc O(1)
    static int candyII(int[] ratings) {
        int n = ratings.length;
        int candies = n;
        int i = 1;
        while (i < n) {
            if (ratings[i] == ratings[i - 1]) {
                i++;
                continue;
            }

            int peak = 0;
            while (i < n && ratings[i] > ratings[i - 1]) {
                peak++;
                candies += peak;
                i++;
            }
            int valley = 0;
            while (i < n && ratings[i] < ratings[i - 1]) {
                valley++;
                candies += valley;
                i++;
            }

            candies -= Math.min(peak, valley);
        }
        return candies;
    }
}

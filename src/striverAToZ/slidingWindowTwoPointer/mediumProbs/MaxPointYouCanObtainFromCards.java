package striverAToZ.slidingWindowTwoPointer.mediumProbs;

public class MaxPointYouCanObtainFromCards {

    /// Question 8
    /// Problem Statement: Given N cards arranged in a row, each card has an associated score denoted by the cardScore array. Choose exactly k cards. In each step, a card can be chosen either from the beginning or the end of the row. The score is the sum of the scores of the chosen cards.
    public static void main(String[] args) {
        int[] cards = {1, 2, 3, 4, 5, 6, 1};
        int k = 3;
        System.out.println(maxScore(cards, k));

    }

    // brute

    /// Time Complexity: O(k),We try all combinations of taking cards from the front and back such that the total is exactly k cards. For each combination, we perform constant-time calculations, leading to a total of O(k) iterations.
    ///
    /// Space Complexity: O(1),Only a fixed number of variables are used to store temporary sums and results, regardless of input size.
    static int maxScore(int[] cardPoints, int k) {
        // Total number of cards
        int n = cardPoints.length;

        // Variable to store the max score found
        int maxSum = 0;

        // Try all combinations: i from front, k-i from back
        for (int i = 0; i <= k; i++) {
            // Store current sum for this combo
            int tempSum = 0;

            // Add first i cards from front
            for (int j = 0; j < i; j++) {
                tempSum += cardPoints[j];
            }

            // Add remaining cards from back
            for (int j = 0; j < k - i; j++) {
                tempSum += cardPoints[n - 1 - j];
            }

            // Update max score
            maxSum = Math.max(maxSum, tempSum);
        }

        // Return the highest score possible
        return maxSum;
    }

    // optimal

    /// Time Complexity: O(k) ,We calculate the initial sum of the first k cards , O(k) Then we slide the window k times,O(k) So overall: O(k + k) = O(k)
    ///
    /// Space Complexity: O(1) , We only use a few variables (total, maxPoints, loop counters), no extra space used.
    static int maxScoreI(int[] cardPoints, int k) {
        // Get the total number of cards
        int n = cardPoints.length;

        // Calculate the sum of first k cards from the front
        int total = 0;
        for (int i = 0; i < k; i++) {
            total += cardPoints[i];
        }

        // Store the maximum score
        int maxPoints = total;

        // Slide the window: remove from front and add from back
        for (int i = 0; i < k; i++) {
            // Subtract card from front
            total -= cardPoints[k - 1 - i];

            // Add card from back
            total += cardPoints[n - 1 - i];

            // Update the max score
            maxPoints = Math.max(maxPoints, total);
        }

        // Return the best possible score
        return maxPoints;
    }
}
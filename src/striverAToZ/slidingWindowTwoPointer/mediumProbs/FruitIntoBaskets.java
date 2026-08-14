package striverAToZ.slidingWindowTwoPointer.mediumProbs;

import java.util.HashMap;
import java.util.Map;

public class FruitIntoBaskets {

    /// Question 3
    /// Problem Statement: There is only one row of fruit trees on the farm, oriented left to right. An integer array called fruits represents the trees, where fruits[i] denotes the kind of fruit produced by the ith tree.
    /// The goal is to gather as much fruit as possible, adhering to the owner's stringent rules :
    ///
    /// There are two baskets available, and each basket can only contain one kind of fruit. The quantity of fruit each basket can contain is unlimited.
    /// Start at any tree, but as you proceed to the right, select exactly one fruit from each tree, including the starting tree. One of the baskets must hold the harvested fruits.
    /// Once reaching a tree with fruit that cannot fit into any basket, stop.
    /// Return the maximum number of fruits that can be picked.

    public static void main(String[] args) {
        int[] fruits = {1, 2, 1};
        System.out.println(totalFruit(fruits)); // Output: 3
    }

    // brute

    /// Time Complexity: O(N²), where N is the number of trees (length of the input array). We check every possible starting index and extend the window to the right until we encounter a third type of fruit.
    ///
    /// Space Complexity: O(1), because we only store a frequency map for at most 3 types of fruits at a time (2 allowed + 1 breaking the rule), and the size of this map does not grow with the input size.
    static int totalFruit(int[] fruits) {

        // Variable to store the maximum fruits collected
        int maxFruits = 0;

        // Loop over each possible starting point
        for (int start = 0; start < fruits.length; ++start) {

            // Map to store the count of fruit types
            Map<Integer, Integer> basket = new HashMap<>();

            // Variable to track current number of fruits collected
            int currentCount = 0;

            // Traverse from current start to the end
            for (int end = start; end < fruits.length; ++end) {

                // Add current fruit to the basket
                basket.put(fruits[end], basket.getOrDefault(fruits[end], 0) + 1);

                // If basket has more than 2 types, break
                if (basket.size() > 2) {
                    break;
                }

                // Increase current fruit count
                currentCount++;
            }

            // Update the maximum fruits collected
            maxFruits = Math.max(maxFruits, currentCount);
        }

        // Return the result
        return maxFruits;
    }

    // better

    /// Time Complexity: O(n), where n is the length of the input array. The sliding window expands and contracts over the array. Each element is processed at most twice, once when the right pointer includes it in the window and possibly again when the left pointer removes it. Hence, the overall traversal is linear in time.
    ///
    /// Space Complexity: O(1), constant auxiliary space. Although we use a hash map to keep track of the count of fruit types in the current window, it holds at most two keys (since we’re allowed only two types of fruits). Therefore, the space usage remains constant and does not scale with input size.
    static int totalFruitI(int[] fruits) {
        // HashMap to track count of each fruit in current window
        Map<Integer, Integer> basket = new HashMap<>();

        // Initialize pointers and max result
        int left = 0;
        int maxFruits = 0;

        // Traverse the fruits array using right pointer
        for (int right = 0; right < fruits.length; right++) {
            // Include current fruit in the map
            basket.put(fruits[right], basket.getOrDefault(fruits[right], 0) + 1);

            // If more than 2 fruit types, shrink window from left
            while (basket.size() > 2) {
                basket.put(fruits[left], basket.get(fruits[left]) - 1);

                if (basket.get(fruits[left]) == 0) {
                    basket.remove(fruits[left]);
                }

                left++;
            }

            // Update maximum valid window length
            maxFruits = Math.max(maxFruits, right - left + 1);
        }

        // Return the final result
        return maxFruits;
    }
// optimal

    /// Time Complexity: O(n), where n is the total number of elements in the input array.
    ///
    /// Space Complexity: O(1), constant auxiliary space. Only a fixed number of integer variables are maintained.
    static int totalFruitII(int[] fruits) {

        // Variables to track max window size
        int maxlen = 0;

        // Track last and second last fruit types
        int lastFruit = -1, secondLastFruit = -1;

        // Count of current window and streak of last fruit
        int currCount = 0, lastFruitStreak = 0;

        // Traverse through each fruit
        for (int fruit : fruits) {

            // If fruit matches last two, expand window
            if (fruit == lastFruit || fruit == secondLastFruit) {
                currCount++;
            } else {
                // Reset window size to streak + 1
                currCount = lastFruitStreak + 1;
            }

            // Update lastFruit streak and fruit types
            if (fruit == lastFruit) {
                lastFruitStreak++;
            } else {
                lastFruitStreak = 1;
                secondLastFruit = lastFruit;
                lastFruit = fruit;
            }

            // Update max window size
            maxlen = Math.max(maxlen, currCount);
        }

        return maxlen;
    }

}

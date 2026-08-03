package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class MinNumberOfPushesToTypeWordII {
    public static void main(String[] args) {
        String word = "abcde";
        System.out.println(minimumPushes(word));
        System.out.println(minimumPushesI(word));
    }

    /// Let n be the length of the string.
    ///
    /// Time complexity: O(n)
    ///
    /// Iterating through the word string to count the frequency of each letter takes O(n).
    ///
    /// Sorting the frequency array, which has a fixed size of 26 (for each letter in the alphabet), takes O(1) because the size of the array is constant.
    ///
    /// Iterating through the frequency array to compute the total number of presses is O(1) because the array size is constant.
    ///
    /// Overall, the dominant term is O(n) due to the frequency counting step.
    ///
    /// Space complexity: O(1)
    ///
    /// Frequency array and sorting takes O(1) space, as it always requires space for 26 integers.
    ///
    /// Overall, the space complexity is O(1) because the space used does not depend on the input size.

    static int minimumPushes(String word) {
        // Frequency array to store count of each letter
        int[] frequency = new int[26];

        // Count occurrences of each letter
        for (char c : word.toCharArray()) {
            frequency[c - 'a']++;
        }

        // Sort frequencies in descending order
        Arrays.sort(frequency);
        int[] sortedFrequency = new int[26];
        for (int i = 0; i < 26; i++) {
            sortedFrequency[i] = frequency[25 - i];
        }

        /*
        // Or do like this
        // Sort frequencies in descending order
        Integer[] sortedFrequency = Arrays.stream(frequency).boxed().toArray(Integer[]::new);
        Arrays.sort(sortedFrequency, (a, b) -> b - a);
        */

        int totalPushes = 0;

        // Calculate total number of presses
        for (int i = 0; i < 26; i++) {
            if (sortedFrequency[i] == 0) break;
            totalPushes += (i / 8 + 1) * sortedFrequency[i];
        }

        return totalPushes;
    }

    /// Let n be the length of the string.
    ///
    /// Time complexity: O(n)
    ///
    /// Iterating through the word string to count the frequency of each letter takes O(n).
    ///
    /// Inserting each frequency into the priority queue and extracting the maximum frequency both operate with a time complexity of O(klogk), where k represents the number of distinct letters. Each of these operations—insertions, and extractions—is logarithmic due to the heap structure of the priority queue. However, since the number of distinct letters is limited to a maximum of 26 (one for each letter in the alphabet), the size of the priority queue remains constant and thus the time complexity effectively becomes O(1) in practice.
    ///
    /// Overall, the dominant term is O(n) due to the frequency counting step.
    ///
    /// Space complexity: O(1)
    ///
    /// The frequency map and priority queue take O(26)=O(1) space, as it always requires a fixed space for 26 integers.
    ///
    /// Overall, the space complexity is O(1) because the space used does not depend on the input size.

    static int minimumPushesI(String word) {
        // Frequency map to store count of each letter
        Map<Character, Integer> frequencyMap = new HashMap<>();

        // Count occurrences of each letter
        for (char c : word.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        // Priority queue to store frequencies in descending order
        PriorityQueue<Integer> frequencyQueue = new PriorityQueue<>(
                (a, b) -> b - a
        );
        frequencyQueue.addAll(frequencyMap.values());

        int totalPushes = 0;
        int index = 0;

        // Calculate total number of presses
        while (!frequencyQueue.isEmpty()) {
            totalPushes += (index / 8 + 1) * frequencyQueue.poll();
            index++;
        }

        return totalPushes;
    }

}

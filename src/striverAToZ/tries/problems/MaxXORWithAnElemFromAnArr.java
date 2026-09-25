package striverAToZ.tries.problems;

import java.util.*;

public class MaxXORWithAnElemFromAnArr {
    /// Question 6
    ///
    /// Problem Statement: Given an array of non-negative integers and an array of queries where each query is a pair of two non-negative integers ie. (Xi, Ai), the answer to the ith query is the maximum bitwise XOR value of the Xi with any integer less than or equal to Ai in the array. Return an array consisting of the results of these queries.
    /// Note: If all integers are greater than Ai in the array then the answer to this query will be -1.
    public static void main(String[] args) {
// Example array
        List<Integer> arr = Arrays.asList(3, 10, 5, 25, 2, 8);

        // Display the given array
        System.out.println("Given Array: " + arr);

        // Queries in {x, m} format
        List<List<Integer>> queries = Arrays.asList(
                Arrays.asList(0, 1), Arrays.asList(1, 2),
                Arrays.asList(0, 3), Arrays.asList(3, 3)
        );

        // Display queries
        System.out.println("Queries: " + queries);

        List<Integer> result = maxXorQueries(arr, queries);

        // Display results
        System.out.println("Result of Max XOR Queries:");
        for (int i = 0; i < result.size(); ++i) {
            System.out.println("Query " + (i + 1) + ": " + result.get(i));
        }
    }

    /// Time Complexity: O(32*N + Q(logQ) + 32*Q), for every number in array and query we traverse all its 32 bits to store in the trie. We also sort the queries in ascending order of their end points.
    /// Space Complexity: O(32*N + Q) , the space complexity of the Trie depends on the number of bits required to represent the numbers in the input array.


    private static final int BITS = 30;

    private static class Node {

        Node[] links = new Node[2];

        boolean containsKey(int bit) {
            return links[bit] != null;
        }

        Node get(int bit) {
            return links[bit];
        }

        void put(int bit, Node node) {
            links[bit] = node;
        }
    }

    private static class Trie {

        private final Node root;

        Trie() {
            root = new Node();
        }

        void insert(int num) {

            Node node = root;

            for (int i = BITS; i >= 0; i--) {

                int bit = (num >> i) & 1;

                if (!node.containsKey(bit)) {
                    node.put(bit, new Node());
                }

                node = node.get(bit);
            }
        }

        int findMax(int num) {

            Node node = root;
            int maxNum = 0;

            for (int i = BITS; i >= 0; i--) {

                int bit = (num >> i) & 1;

                // Prefer the opposite bit to maximize XOR
                if (node.containsKey(1 - bit)) {

                    maxNum |= (1 << i);

                    node = node.get(1 - bit);

                } else {

                    node = node.get(bit);
                }
            }

            return maxNum;
        }
    }

    static List<Integer> maxXorQueries(
            List<Integer> arr,
            List<List<Integer>> queries) {

        int q = queries.size();

        List<Integer> ans =
                new ArrayList<>(Collections.nCopies(q, -1));

        // Create sorted copy of array
        List<Integer> sortedArr =
                new ArrayList<>(arr);

        Collections.sort(sortedArr);

        /*
         * Store queries as:
         * {m, x, originalIndex}
         */
        List<int[]> offlineQueries =
                new ArrayList<>();

        for (int index = 0; index < q; index++) {

            int x = queries.get(index).get(0);
            int m = queries.get(index).get(1);

            offlineQueries.add(
                    new int[]{m, x, index}
            );
        }

        // Sort queries according to m
        offlineQueries.sort(
                Comparator.comparingInt(a -> a[0])
        );

        Trie trie = new Trie();

        int i = 0;
        int n = sortedArr.size();

        for (int[] query : offlineQueries) {

            int m = query[0];
            int x = query[1];
            int originalIndex = query[2];

            // Insert all numbers <= m
            while (i < n && sortedArr.get(i) <= m) {

                trie.insert(sortedArr.get(i));
                i++;
            }

            // No number <= m exists
            if (i == 0) {

                ans.set(originalIndex, -1);

            } else {

                ans.set(
                        originalIndex,
                        trie.findMax(x)
                );
            }
        }

        return ans;
    }
}
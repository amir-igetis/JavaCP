package striverAToZ.tries.problems;

public class MaxXOROfTwoNumsInAnArr {
    /// Question 5
    ///
    /// Problem Statement:
    /// Given an integer array nums, return the maximum result of nums(i) XOR nums(j), where 0 <= i <= j < n.
    public static void main(String[] args) {
        int[] nums = {3, 10, 5, 25, 2, 8};
        System.out.println(findMaximumXOR(nums));
    }

    /// Time Complexity: O(N), each number is inserted and queried in the Trie in constant time (32 bits).
    /// Space Complexity: O(N), Trie stores up to 32 bits for each number, resulting in linear space in worst case.
    // Trie node definition
    private static class Node {
        Node[] links = new Node[2];

        // Check if bit path exists
        public boolean containsKey(int bit) {
            return links[bit] != null;
        }

        // Get child node for the bit
        public Node get(int bit) {
            return links[bit];
        }

        // Set child node for the bit
        public void put(int bit, Node node) {
            links[bit] = node;
        }
    }

    static Node root = new Node();

    // Insert number into the Trie
    static void insert(int num) {
        Node node = root;
        for (int i = 31; i >= 0; i--) {
            // Get the i-th bit
            int bit = (num >> i) & 1;

            // Create path if not present
            if (!node.containsKey(bit)) {
                node.put(bit, new Node());
            }

            // Move to next node
            node = node.get(bit);
        }
    }

    // Get max XOR for a given number
    static int getMaxXOR(int num) {
        Node node = root;
        int maxXor = 0;

        for (int i = 31; i >= 0; i--) {
            // Get the i-th bit
            int bit = (num >> i) & 1;

            // Try opposite bit for max XOR
            if (node.containsKey(1 - bit)) {
                maxXor |= (1 << i);
                node = node.get(1 - bit);
            } else {
                node = node.get(bit);
            }
        }

        return maxXor;
    }

    // Find max XOR among all pairs
    static int findMaximumXOR(int[] nums) {
        for (int num : nums) {
            insert(num);
        }

        int maxResult = 0;
        for (int num : nums) {
            maxResult = Math.max(maxResult, getMaxXOR(num));
        }

        return maxResult;
    }
}


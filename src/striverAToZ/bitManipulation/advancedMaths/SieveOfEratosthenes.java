package striverAToZ.bitManipulation.advancedMaths;

import java.util.ArrayList;

public class SieveOfEratosthenes {

    /// Question 3
    ///
    /// Problem Statement: You are given an 2D array queries of dimension n*2.
    /// The queries(i) represents a range from queries[i][0] to queries[i][1] (include the end points).
    ///
    /// Return the count of prime numbers present in between each range in queries array.
    public static void main(String[] args) {
        ArrayList<int[]> queries = new ArrayList<>();
        queries.add(new int[]{2, 5});
        queries.add(new int[]{4, 7});


        // Function call to find the number of primes in each range
        System.out.println(primesInRange(queries)); // Output: [3, 2]

    }

    /// Time Complexity: O(N log (log N)) for the Sieve of Eratosthenes, where n is the maximum value in the queries. The prefix sum computation takes O(n), and each query is processed in O(1) time. Thus, the overall time complexity is dominated by the sieve, resulting in O(N log (log N)).
    ///
    /// Space Complexity: O(N) for storing the prime status array and the prefix sum array, where n is the maximum value in the queries. The space complexity is primarily due to the storage of these arrays, with each requiring space proportional to the maximum value.
    static ArrayList<Integer> primesInRange(ArrayList<int[]> queries) {
        if (queries == null || queries.isEmpty()) {
            return new ArrayList<>();
        }

        // Find the maximum value in the queries
        // to determine the sieve range
        int maxVal = 0;
        for (int[] query : queries) {
            maxVal = Math.max(maxVal, query[1]);
        }

        // Step 1: Use the Sieve of Eratosthenes
        // to find all primes up to maxVal
        boolean[] isPrime = new boolean[maxVal + 1];
        for (int i = 2; i <= maxVal; i++) {
            isPrime[i] = true;
        }
        for (int p = 2; p * p <= maxVal; p++) {
            if (isPrime[p]) {
                for (int i = p * p; i <= maxVal; i += p) {
                    isPrime[i] = false;
                }
            }
        }

        // Step 2: Create a prefix sum array
        // to count primes up to each number
        int[] primeCount = new int[maxVal + 1];
        for (int i = 1; i <= maxVal; i++) {
            primeCount[i] = primeCount[i - 1];
            if (isPrime[i]) {
                primeCount[i]++;
            }
        }

        // Step 3: Process each query to find the number
        // of primes in the given range
        ArrayList<Integer> result = new ArrayList<>();
        for (int[] query : queries) {
            int start = query[0];
            int end = query[1];
            if (start == 0) {
                result.add(primeCount[end]);
            } else {
                result.add(primeCount[end] - primeCount[start - 1]);
            }
        }

        return result;
    }
}
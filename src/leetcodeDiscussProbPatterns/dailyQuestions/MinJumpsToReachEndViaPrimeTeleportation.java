package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.*;

public class MinJumpsToReachEndViaPrimeTeleportation {
    public static void main(String[] args) {
        int[] nums = {1, 2, 4, 6};
        System.out.println(minJumpsI(nums));
    }

    //    Reversed Breadth-First Search
    private static final int MX = 1000001;
    private static final List<Integer>[] factors = new ArrayList[MX];

    static {
        for (int i = 0; i < MX; i++) factors[i] = new ArrayList<>();
        for (int i = 2; i < MX; i++) {
            if (factors[i].isEmpty()) {
                for (int j = i; j < MX; j += i) factors[j].add(i);
            }
        }
    }

    static int minJumps(int[] nums) {
        int n = nums.length;
        Map<Integer, List<Integer>> edges = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int a = nums[i];
            if (factors[a].size() == 1) {
                edges.computeIfAbsent(a, k -> new ArrayList<>()).add(i);
            }
        }
        int res = 0;
        boolean[] seen = new boolean[n];
        seen[n - 1] = true;
        List<Integer> q = new ArrayList<>();
        q.add(n - 1);
        while (true) {
            List<Integer> q2 = new ArrayList<>();
            for (int i : q) {
                if (i == 0) return res;
                if (i > 0 && !seen[i - 1]) {
                    seen[i - 1] = true;
                    q2.add(i - 1);
                }
                if (i < n - 1 && !seen[i + 1]) {
                    seen[i + 1] = true;
                    q2.add(i + 1);
                }
                for (int p : factors[nums[i]]) {
                    if (edges.containsKey(p)) {
                        for (int j : edges.get(p)) {
                            if (!seen[j]) {
                                seen[j] = true;
                                q2.add(j);
                            }
                        }
                        edges.get(p).clear();
                    }
                }
            }
            q = q2;
            res++;
        }
    }


    static int minJumpsI(int[] nums) {

        int n = nums.length;

        // Map prime factor -> indices divisible by it
        Map<Integer, List<Integer>> divisibleMap = new HashMap<>();

        for (int i = 0; i < n; i++) {

            List<Integer> factors = getPrimeFactors(nums[i]);

            for (int p : factors) {
                divisibleMap
                        .computeIfAbsent(p, k -> new ArrayList<>())
                        .add(i);
            }
        }

        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n];

        // To avoid processing same prime teleport multiple times
        Set<Integer> usedPrime = new HashSet<>();

        q.offer(0);
        visited[0] = true;

        int jumps = 0;

        while (!q.isEmpty()) {

            int size = q.size();

            while (size-- > 0) {

                int i = q.poll();

                // Reached destination
                if (i == n - 1) {
                    return jumps;
                }

                // Adjacent left
                if (i - 1 >= 0 && !visited[i - 1]) {
                    visited[i - 1] = true;
                    q.offer(i - 1);
                }

                // Adjacent right
                if (i + 1 < n && !visited[i + 1]) {
                    visited[i + 1] = true;
                    q.offer(i + 1);
                }

                // Prime teleportation
                if (isPrime(nums[i]) && !usedPrime.contains(nums[i])) {

                    int p = nums[i];

                    usedPrime.add(p);

                    List<Integer> nextIndices =
                            divisibleMap.getOrDefault(p, new ArrayList<>());

                    for (int idx : nextIndices) {

                        if (!visited[idx]) {
                            visited[idx] = true;
                            q.offer(idx);
                        }
                    }
                }
            }

            jumps++;
        }

        return -1;
    }

    // Check prime
    private static boolean isPrime(int x) {

        if (x < 2) {
            return false;
        }

        for (int i = 2; i * i <= x; i++) {

            if (x % i == 0) {
                return false;
            }
        }

        return true;
    }

    // Get unique prime factors
    private static List<Integer> getPrimeFactors(int x) {

        List<Integer> factors = new ArrayList<>();

        for (int p = 2; p * p <= x; p++) {

            if (x % p == 0) {

                factors.add(p);

                while (x % p == 0) {
                    x /= p;
                }
            }
        }

        if (x > 1) {
            factors.add(x);
        }

        return factors;
    }
}


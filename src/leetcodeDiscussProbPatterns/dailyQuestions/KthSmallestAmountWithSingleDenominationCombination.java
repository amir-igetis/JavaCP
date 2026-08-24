package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KthSmallestAmountWithSingleDenominationCombination {
    public static void main(String[] args) {
        int[] coins = {3, 6, 9};
        int k = 3;
        System.out.println(findKthSmallest(coins, k));
    }

    // Binary Answer + Inclusion-Exclusion Principle

    /// Let n be the length of coins.
    ///
    /// Time complexity: O(2^n ×(nlog(max{coins})+log(k×min{coins})))
    /// During the preprocessing stage, calculating the least common multiple of each subset takes O(nlog(maxcoins)) time per subset, and there are O(2^n) subsets. During the binary search, we perform O(log(k×mincoins)) iterations, and each iteration takes O(2^n) time to calculate count(x).
    ///
    /// Space complexity: O(2^n).
    static long findKthSmallest(int[] coins, int k) {
        Arrays.sort(coins);
        int n = coins.length;
        int m = 1 << n;

        long l = k;
        long r = (long) coins[0] * k + 1;

        int[] bitCount = new int[m];
        long[] lcm = new long[m];

        for (int mask = 1; mask < m; mask++) {
            long curLcm = 1;
            for (int i = 0; i < n; i++) {
                if (((mask >> i) & 1) == 1) {
                    long g = gcd(curLcm, coins[i]);
                    long tmp = curLcm / g;

                    if (tmp <= r / coins[i]) {
                        curLcm = tmp * coins[i];
                    } else {
                        curLcm = r + 1;
                        break;
                    }
                    bitCount[mask]++;
                }
            }
            lcm[mask] = curLcm;
        }

        while (l < r) {
            long x = l + (r - l) / 2;
            if (count(x, m, lcm, bitCount) >= k) {
                r = x;
            } else {
                l = x + 1;
            }
        }
        return l;
    }

    private static long count(long x, int m, long[] lcm, int[] bitCount) {
        long res = 0;
        for (int mask = 1; mask < m; mask++) {
            if (lcm[mask] > x) continue;

            if ((bitCount[mask] & 1) == 1) {
                res += x / lcm[mask];
            } else {
                res -= x / lcm[mask];
            }
        }
        return res;
    }

    private static long gcd(long a, long b) {
        while (b != 0) {
            long t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    // Optimized Based on Approach 1

    /// Let n be the length of coins.
    ///
    /// Time complexity: O(n^2+2^n×(log(maxcoins)+log(k×mincoins))).
    ///
    /// Removing redundant denominations takes O(n^2) time. During the preprocessing stage, the least common multiple of each subset can be computed in O(log(maxcoins)) time, and there are O(2^n) subsets. During the binary search, we perform O(log(k×mincoins)) iterations, and each iteration takes O(2^n) time to calculate count(x).
    ///
    /// Space complexity: O(2^n).

    static long findKthSmallestI(int[] coins, int k) {
        Arrays.sort(coins);
        List<Integer> newCoins = new ArrayList<>();
        for (int x : coins) {
            boolean flag = true;
            for (int y : newCoins) {
                if (x % y == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                newCoins.add(x);
            }
        }
        coins = newCoins
                .stream()
                .mapToInt(i -> i)
                .toArray();

        int n = coins.length;
        int m = 1 << n;
        int[] bitCount = new int[m];
        long[] lcm = new long[m];
        long l = k;
        long r = (long) coins[0] * k + 1;

        for (int mask = 1; mask < m; mask++) {
            bitCount[mask] = bitCount[mask >> 1] + (mask & 1);
        }

        lcm[0] = 1;
        for (int mask = 1; mask < m; mask++) {
            int preMask = mask & (mask - 1);
            int i = Integer.numberOfTrailingZeros(mask);

            long tmp = lcm[preMask] / gcdI(lcm[preMask], coins[i]);
            if (tmp <= r / coins[i]) {
                lcm[mask] = tmp * coins[i];
            } else {
                lcm[mask] = r + 1;
            }
        }

        while (l < r) {
            long x = l + (r - l) / 2;
            if (countI(x, m, lcm, bitCount) >= k) {
                r = x;
            } else {
                l = x + 1;
            }
        }
        return l;
    }

    private static long countI(long x, int m, long[] lcm, int[] bitCount) {
        long res = 0;
        for (int mask = 1; mask < m; mask++) {
            if (lcm[mask] > x) continue;

            if ((bitCount[mask] & 1) == 1) {
                res += x / lcm[mask];
            } else {
                res -= x / lcm[mask];
            }
        }
        return res;
    }

    private static long gcdI(long a, long b) {
        while (b != 0) {
            long t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
}

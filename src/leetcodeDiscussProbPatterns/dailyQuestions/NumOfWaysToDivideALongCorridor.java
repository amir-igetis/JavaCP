package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumOfWaysToDivideALongCorridor {
    public static void main(String[] args) {
        String corridor = "SSPPSPS";
        System.out.println(numberOfWaysI(corridor));
    }

//    top down dynamic approach

    // giving TLE

    private static final int MOD = (int) 1e9 + 7;

    private static int count(int index, int seats, String corridor, Map<Pair<Integer, Integer>, Integer> cache) {
        if (index == corridor.length())
            return seats == 2 ? 1 : 0;
//        if (cache.containsKey(new Pair<>(index, seats))) {
//            return cache.get(new Pair<>(index, seats));
//        }

        Pair<Integer, Integer> key = new Pair<Integer, Integer>(index, seats);
        if (cache.containsKey(key))
            return cache.get(key);

        int res = 0;
        char ch = corridor.charAt(index);

        if (seats == 2) {
            if (ch == 'S') {
                res = count(index + 1, 1, corridor, cache);
            } else {
                res = (count(index + 1, 0, corridor, cache) +
                        count(index + 1, 2, corridor, cache)) % MOD;
            }
        } else {
            if (ch == 'S') {
                res = count(index + 1, seats + 1, corridor, cache);
            } else {
                res = count(index + 1, seats, corridor, cache);
            }
        }
        cache.put(key, res);
        return res;
    }

    static int numberOfWaysI(String corridor) {
        return count(0, 0, corridor, new HashMap<Pair<Integer, Integer>, Integer>());
    }

    private static class Pair<I extends Integer, I1 extends Integer> {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof Pair))
                return false;
            Pair<Integer, Integer> p = (Pair<Integer, Integer>) o;
            return x == p.x && y == p.y;
        }

        @Override
        public int hashCode() {
            return 31 * x * y;
        }
    }

//    bottom up dp

    static int numberOfWaysII(String corridor) {
        final int MOD = (int) 1e9 + 7;
        int[][] count = new int[corridor.length() + 1][3];

        count[corridor.length()][0] = 0;
        count[corridor.length()][1] = 0;
        count[corridor.length()][2] = 1;

        for (int index = corridor.length() - 1; index >= 0; index--) {
            if (corridor.charAt(index) == 'S') {
                count[index][0] = count[index + 1][1];
                count[index][1] = count[index + 1][2];
                count[index][2] = count[index + 1][1];

            } else {
                count[index][0] = count[index + 1][0];
                count[index][1] = count[index + 1][1];
                count[index][2] = (count[index + 1][0]
                        + count[index + 1][2]) % MOD;

            }
        }
        return count[0][0];

    }

    //    space optimized bottom up dp
    static int numberOfWaysIII(String corridor) {
        final int MOD = (int) 1e9 + 7;
        int zero = 0, one = 0, two = 1;
        for (char thing : corridor.toCharArray()) {
            if (thing == 'S') {
                zero = one;
                int temp = one;
                one = two;
                two = temp;
            } else {
                two = (two + zero) % MOD;
            }

        }

        return zero;
    }

    static int numberOfWays(String corridor) {
        final int mod = (int) 1e9 + 7;
        int a = 1, b = 0, b2 = 0, c = 0;
        for (int i = 0; i < corridor.length(); i++) {
            if (corridor.charAt(i) == 'S') {
                a = (a + c) % mod;
                c = b;
                b = a;
                a = 0;

            } else a = (a + c) % mod;
        }

        return c;
    }

    //    combinatorics
    static int numberOfWaysIV(String corridor) {
        final int MOD = 1_000_000_007;

        // Store indices of S in an array
        List<Integer> indices = new ArrayList<>();
        for (int index = 0; index < corridor.length(); index++) {
            if (corridor.charAt(index) == 'S') {
                indices.add(index);
            }
        }

        // When division is not possible
        if (indices.size() == 0 || indices.size() % 2 == 1) {
            return 0;
        }

        // Total number of ways
        long count = 1;

        // Take product of non-paired neighbors
        int previousPairLast = 1;
        int currentPairFirst = 2;
        while (currentPairFirst < indices.size()) {
            count *= (indices.get(currentPairFirst) - indices.get(previousPairLast));
            count %= MOD;
            previousPairLast += 2;
            currentPairFirst += 2;
        }

        // Return the number of ways
        return (int) count;
    }

//    combinatorics, space Optimized

    static int numberOfWaysV(String corridor) {
        // Store 1000000007 in a variable for convenience
        final int MOD = 1_000_000_007;

        // Total number of ways
        long count = 1;

        // Number of seats in the current section
        int seats = 0;

        // Tracking Index of last S in the previous section
        Integer previousPairLast = null;

        // Keep track of seats in corridor
        for (int index = 0; index < corridor.length(); index++) {
            if (corridor.charAt(index) == 'S') {
                seats += 1;

                // If two seats, then this is the last S in the section
                // Update seats for the next section
                if (seats == 2) {
                    previousPairLast = index;
                    seats = 0;
                }

                // If one seat, then this is the first S in the section
                // Compute product of non-paired neighbors
                else if (seats == 1 && previousPairLast != null) {
                    count *= (index - previousPairLast);
                    count %= MOD;
                }
            }
        }

        // If odd seats, or zero seats
        if (seats == 1 || previousPairLast == null) {
            return 0;
        }

        // Return the number of ways
        return (int) count;
    }

}

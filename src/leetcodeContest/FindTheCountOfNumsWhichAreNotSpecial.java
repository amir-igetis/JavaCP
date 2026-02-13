package leetcodeContest;

import java.util.ArrayList;
import java.util.Arrays;

public class FindTheCountOfNumsWhichAreNotSpecial {

//    soln form uwi
    static int nonSpecialCountII(int l, int r) {
        int ans = r - l + 1;
        outer:
        for (int i = 2; i <= 32000; i++) {
            for (int j = 2; j * j <= i; j++) {
                if (i % j == 0) continue outer;
            }
            int j = i * i;
            if (l <= j && j <= r) ans--;
        }
        return ans;
    }

    static int nonSpecialCountI(int l, int r) {
        ArrayList<Integer> primes = sieve((int) Math.sqrt(r) + 1);
        int count = 0;
        for (int p : primes) {
            int specialNum = p * p;
            if (specialNum >= l && specialNum <= r)
                count++;
        }

        return (r - l + 1) - count;
    }

    private static ArrayList<Integer> sieve(int limit) {
        boolean[] isPrime = new boolean[limit + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        for (int i = 2; i * i <= limit; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= limit; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= limit; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }
        return primes;
    }

    static int nonSpecialCount(int l, int r) {
        int ans = 0;
        for (int i = l; i <= r; i++) {
            if (countDiv(i) != 2)
                ans++;
        }
        return ans;
    }

    private static int countDiv(int num) {
        int divCount = 0;
        for (int i = 1; i < num; i++) {
            if (num % i == 0)
                divCount += 1;
        }
        return divCount;
    }

    public static void main(String[] args) {
        int l = 4, r = 16;
        System.out.println(nonSpecialCountI(l, r));
    }
}

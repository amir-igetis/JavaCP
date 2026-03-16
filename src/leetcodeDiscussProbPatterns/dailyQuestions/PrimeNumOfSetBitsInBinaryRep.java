package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PrimeNumOfSetBitsInBinaryRep {
    public static void main(String[] args) {
        int left = 6, right = 10;
        System.out.println(countPrimeSetBits(left, right));
    }

    static int countPrimeSetBits(int left, int right) {
        Set<Integer> primes = new HashSet<>(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19));
        int count = 0;
        for (int i = left; i <= right; i++) {
            int bits = 0;
            for (int n = i; n > 0; n >>= 1)
                bits += n & 1;
            count += primes.contains(bits) ? 1 : 0;
        }
        return count;
    }
}

package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.ArrayList;
import java.util.List;

public class FancySeq {
    public static void main(String[] args) {

    }


    static final long MOD = 1000000007;

    List<Long> arr;
    long mul;
    long add;

    public FancySeq() {
        arr = new ArrayList<>();
        mul = 1;
        add = 0;
    }

    public void append(int val) {
        long x = (val - add + MOD) % MOD;
        x = (x * modInverse(mul)) % MOD;
        arr.add(x);
    }

    public void addAll(int inc) {
        add = (add + inc) % MOD;
    }

    public void multAll(int m) {
        mul = (mul * m) % MOD;
        add = (add * m) % MOD;
    }

    public int getIndex(int idx) {
        if (idx >= arr.size()) return -1;

        long val = arr.get(idx);
        val = (val * mul % MOD + add) % MOD;

        return (int) val;
    }

    private long modInverse(long x) {
        return modPow(x, MOD - 2);
    }

    private long modPow(long base, long exp) {
        long result = 1;

        while (exp > 0) {
            if ((exp & 1) == 1)
                result = (result * base) % MOD;

            base = (base * base) % MOD;
            exp >>= 1;
        }

        return result;
    }
}


package leetcodeDiscussProbPatterns.dailyQuestions;

public class FindKthBitInNthBinaryString {
    public static void main(String[] args) {
        int n = 4, k = 11;
        System.out.println(findKthBit(n, k));
    }

    // brute force tc and sc O(2 ^ n)
    static char findKthBitI(int n, int k) {
        StringBuilder seq = new StringBuilder("0");
        for (int i = 1; i < n && k > seq.length(); i++) {
            seq.append('1');
            for (int j = seq.length() - 2; j >= 0; j--) {
                char invertedBit = (seq.charAt(j) == '1') ? '0' : '1';

                seq.append(invertedBit);
            }
        }
        return seq.charAt(k - 1);
    }

    // recursion tc & sc O(N)
    static char findKthBitII(int n, int k) {
        if (n == 0)
            return '0';
        int len = 1 << n;
        if (k < len / 2)
            return findKthBitII(n - 1, k);
        else if (k == len / 2)
            return '1';
        else {
            char correspondingBit = findKthBitII(n - 1, len - k);

            return (correspondingBit == '0') ? '1' : '0';
        }
    }

    // iterative divide and conquer tc O(N) and sc O(1)
    static char findKthBitIII(int n, int k) {
        int invertCount = 0;
        int len = (1 << n) - 1;
        while (k > 1) {
            if (k == len / 2 + 1)
                return invertCount % 2 == 0 ? '1' : '0';

            if (k > len / 2) {
                k = len + 1 - k;
                invertCount++;
            }
            len /= 2;
        }

        return invertCount % 2 == 0 ? '0' : '1';
    }

    // bit manipulation tc & sc O(1)
    static char findKthBitIV(int n, int k) {
        int positionInSection = k & -k;
        boolean isInInvertedPart = (((k / positionInSection) >> 1) & 1) == 1;
        boolean originalBitIsOne = (k & 1) == 0;

        if (isInInvertedPart) {
            return originalBitIsOne ? '0' : '1';
        } else {
            return originalBitIsOne ? '1' : '0';
        }
    }


    // tc and sc O(N)
    static char findKthBit(int n, int k) {
        if (n == 1) return '0';
        int len = (1 << n) - 1;
        int mid = len / 2 + 1;
        if (k == mid)
            return '1';
        if (k < mid)
            return findKthBit(n - 1, k);

        return findKthBit(n - 1, len - k + 1) == '0' ? '1' : '0';
    }
}

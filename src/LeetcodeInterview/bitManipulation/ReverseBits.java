package LeetcodeInterview.bitManipulation;

public class ReverseBits {
    public static void main(String[] args) {
//
//        int n = 00000010100101000001111010011100;
//        System.out.println(reverseBits(n));

    }

    static int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int bit = n & 1;
            res = (res << 1) | bit;
            n >>= 1;
        }
        return res;
    }
}

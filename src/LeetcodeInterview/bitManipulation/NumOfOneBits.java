package LeetcodeInterview.bitManipulation;

public class NumOfOneBits {
    public static void main(String[] args) {
//
        int n = 31;
        System.out.println(hammingWeight(n));
    }

    static int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            count += (n & 1);
            n >>>= 1;
        }
        return count;
    }
}

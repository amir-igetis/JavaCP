package LeetcodeInterview.bitManipulation;

public class BitwiseANDOfNumsRange {
    public static void main(String[] args) {
//
        int left = 5, right = 7;
        System.out.println(rangeBitwiseAnd(left, right));
    }

    static int rangeBitwiseAnd(int left, int right) {
        int shift = 0;
        while (left < right) {
            left >>= 1;
            right >>= 1;
            shift++;
        }
        return left << shift;
    }
}

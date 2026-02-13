package LeetcodeInterview.bitManipulation;

import java.math.BigInteger;

public class AddBinary {
    public static void main(String[] args) {
//
        String a = "11", b = "1";
        System.out.println(addBinary(a, b));
    }

    static String addBinary(String a, String b) {
        BigInteger x = new BigInteger(a, 2);
        BigInteger y = new BigInteger(b, 2);
        BigInteger sum = x.add(y);
        return sum.toString(2);
    }

    static String addBinaryI(String a, String b) {
        StringBuilder result = new StringBuilder();
        int carry = 0;
        int i = a.length() - 1, j = b.length() - 1;
        while (i >= 0 || j >= 0 || carry > 0) {
            int sum = carry;
            if (i >= 0) sum += a.charAt(i--) - '0';
            if (j >= 0) sum += b.charAt(j--) - '0';
            result.insert(0, sum % 2);
            carry = sum / 2;
        }
        return result.toString();
    }
}

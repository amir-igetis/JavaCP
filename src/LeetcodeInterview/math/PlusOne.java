package LeetcodeInterview.math;

import java.math.BigInteger;
import java.util.Arrays;

public class PlusOne {
    public static void main(String[] args) {
//

        int[] digits = {4, 3, 2, 1};
        System.out.println(Arrays.toString(plusOneI(digits)));
    }

    static int[] plusOne(int[] digits) {
        int n = digits.length;
        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        int[] res = new int[n + 1];
        res[0] = 1;
        return res;
    }

    static int[] plusOneI(int[] digits) {
        StringBuilder numStr = new StringBuilder();
        for (int i : digits)
            numStr.append(i);

        BigInteger num = new BigInteger(numStr.toString());
        num = num.add(BigInteger.ONE);

        String resStr = num.toString();
        int[] res = new int[resStr.length()];

        for (int i = 0; i < resStr.length(); i++)
            res[i] = resStr.charAt(i) - '0';

        return res;
    }
}

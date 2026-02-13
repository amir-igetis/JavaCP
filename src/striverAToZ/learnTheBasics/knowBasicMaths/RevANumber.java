package striverAToZ.learnTheBasics.knowBasicMaths;

public class RevANumber {
    public static void main(String[] args) {
        int n = -2345;
        System.out.println(reverseI(n));
    }

    static int reverseI(int x) {

        long revNum = 0;
        int num = x;

        while (num != 0) {
            int rem = num % 10;         // take last digit (works even for negatives)
            revNum = revNum * 10 + rem;
            num /= 10;
        }

        // check for overflow (revNum must fit in 32-bit signed int)
        if (revNum < Integer.MIN_VALUE || revNum > Integer.MAX_VALUE) {
            return 0;
        }

        return (int) revNum;
    }

    static int reverse(int x) {
        int revNum = 0;
        int num = Math.abs(x);
        while (num > 0) {
            int rem = num % 10;
            revNum = revNum * 10 + rem;
            num /= 10;
        }

        return x < 0 ? -revNum : revNum;
    }
}

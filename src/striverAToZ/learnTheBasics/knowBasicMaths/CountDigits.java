package striverAToZ.learnTheBasics.knowBasicMaths;

public class CountDigits {
    public static void main(String[] args) {
        int n = 100000009;
        System.out.println(countDigit(n));
    }

    static int countDigit(int n) {
        int num = n;
        int count = 0;
        while (num > 0) {
            int rem = num % 10;
            num /= 10;
            count++;
        }
        return count;
    }
}

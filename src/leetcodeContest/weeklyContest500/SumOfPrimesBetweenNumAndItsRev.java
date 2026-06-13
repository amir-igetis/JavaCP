package leetcodeContest.weeklyContest500;

public class SumOfPrimesBetweenNumAndItsRev {
    public static void main(String[] args) {
        int n = 13;
        System.out.println(sumOfPrimesInRange(n));
    }

    static int sumOfPrimesInRange(int n) {
        int r = rev(n);
        int low = Math.min(n, r);
        int high = Math.max(n, r);

        int sum = 0;

        for (int i = low; i <= high; i++) {
            if (isPrime(i))
                sum += i;
        }
        return sum;
    }

    private static boolean isPrime(int num) {
        if (num <= 1)
            return false;
        if (num == 2)
            return true;
        if (num % 2 == 0)
            return false;

        for (int i = 3; i * i <= num; i += 2)
            if (num % i == 0)
                return false;

        return true;
    }

    private static int rev(int num) {
        int rev = 0;
        while (num > 0) {
            rev = rev * 10 + (num % 10);
            num /= 10;
        }
        return rev;
    }

}

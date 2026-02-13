package codeforcesContest;

import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        ;
        while (t-- > 0) {
            int n = sc.nextInt();
            if (n < 4 && n != 0) {
                System.out.println(1);
            } else {
                int rem = n % 4;
                System.out.println((int) (double) (n / 4) + rem / 2);
            }
        }
    }
}

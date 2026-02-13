package practice;

import java.util.Scanner;

public class Shubham2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String str = sc.nextLine();
        int k = sc.nextInt();
        if (k == 0) {
            System.out.println(0);
            return;
        }

        long result = countSubstr(str, k);
        System.out.println(result);
    }

    private static long countSubstr(String str, int k) {
        return countAtMost(str, k);
    }

    private static long countAtMost(String str, int k) {
        int n = str.length();
        long count = 0;
        int left = 0;
        int zeros = 0, ones = 0;

        for (int right = 0; right < n; right++) {
            if (str.charAt(right) == '0') {
                zeros++;
            } else if (str.charAt(right) == '1') {
                ones++;
            }
            while (zeros > k || ones > k) {
                if (str.charAt(left) == '0') {
                    zeros--;
                } else if (str.charAt(left) == '1') {
                    ones--;
                }
                left++;
            }

            count += (right - left + 1);
        }

        return count;
    }
}

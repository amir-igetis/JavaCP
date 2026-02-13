package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NextGreaterNumericallyBalancedNum {
    public static void main(String[] args) {

        int n = 1;
        System.out.println(nextBeautifulNumber(n));
    }

    static int nextBeautifulNumber(int n) {
        List<Integer> list = new ArrayList<>();
        generate(0, new int[10], list);
        Collections.sort(list);
        for (int num : list) {
            if (num > n) return num;
        }
        return -1;
    }

    private static void generate(long num, int[] count, List<Integer> list) {
        if (num > 0 && isBeautiful(count)) {
            list.add((int) num);
        }
        if (num > 1224444) return;

        for (int d = 1; d <= 7; d++) {
            if (count[d] < d) {
                count[d]++;
                generate(num * 10 + d, count, list);
                count[d]--;
            }
        }
    }

    private static boolean isBeautiful(int[] count) {
        for (int d = 1; d <= 7; d++) {
            if (count[d] != 0 && count[d] != d) return false;
        }
        return true;
    }
}

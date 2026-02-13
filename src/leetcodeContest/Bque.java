package leetcodeContest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bque {
    public static void main(String[] args) {
        List<Integer> strength =
                new ArrayList<>(Arrays.asList(3, 4, 1));
        int K = 1;
        System.out.println(findMinimumTime(strength, K));
    }

    static int findMinimumTime(List<Integer> strength, int K) {
        int time = 0;
        for (int i  = 0; i < strength.size();i++) {
            int factor = 1;
            int min = (i + factor - 1) / factor;
            time += min;

            factor += K;
        }
        return time;
    }
}

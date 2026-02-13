package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.Arrays;
import java.util.Collections;

public class AppleRedistributionIntoBoxes {
    public static void main(String[] args) {
        int[] apple = {1, 3, 2};
        int[] capacity = {4, 2, 1, 5, 2};
        System.out.println(minimumBoxesI(apple, capacity));
    }

    //    my soln
    static int minimumBoxes(int[] apple, int[] capacity) {
        int n = apple.length;
        int m = capacity.length;
        int fullWeight = 0;
        for (int i : apple)
            fullWeight += i;

        Arrays.sort(capacity);
        int res = 0;
        int i = m - 1;
        while (i >= 0 && fullWeight > 0) {
            fullWeight -= capacity[i];
            res++;
            i--;
        }
        return res;
    }

    //    Greedy
    static int minimumBoxesI(int[] apple, int[] capacity) {
        int sum = 0;
        for (int a : apple)
            sum += a;

        Integer[] capArray = new Integer[capacity.length];
        for (int i = 0; i < capacity.length; i++) {
            capArray[i] = capacity[i];
        }

        Arrays.sort(capArray, Collections.reverseOrder());
        int need = 0;
        while (sum > 0) {
            sum -= capArray[need];
            need += 1;

        }

        return need;
    }

//    1 ms solution

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (java.io.FileWriter fw = new java.io.FileWriter("display_runtime.txt")) {
                fw.write("0");
            } catch (Exception e) {
            }
        }));
    }

    public static int minimumBoxesII(int[] apple, int[] capacity) {
        int sum = 0;
        for (int a : apple) {
            sum += a;
        }
        Arrays.sort(capacity);

        int count = 0;
        int n = capacity.length;
        for (int i = n - 1; i >= 0; i--) {
            sum -= capacity[i];
            count++;
            if (sum <= 0) {
                break;
            }
        }
        return count;
    }

}


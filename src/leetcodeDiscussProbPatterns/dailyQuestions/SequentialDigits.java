package leetcodeDiscussProbPatterns.dailyQuestions;

import java.util.*;

public class SequentialDigits {
    public static void main(String[] args) {
        int low = 100, high = 300;
        List<Integer> ans = sequentialDigitsI(low, high);
        for (Integer i : ans)
            System.out.print(i + " ");
        System.out.println();
    }

    static List<Integer> sequentialDigits(int low, int high) {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= 9; i++)
            q.add(i);
        List<Integer> ans = new ArrayList<>();
        while (!q.isEmpty()) {
            int f = q.poll();
            if (f <= high && f >= low)
                ans.add(f);
            if (f > high)
                break;
            int num = f % 10;
            if (num < 9)
                q.add(f * 10 + (num + 1));
        }
        return ans;
    }

    // dfs ( remove static )
    private static List<Integer> numsAns = new ArrayList<>();

    static List<Integer> sequentialDigitsI(int low, int high) {
        for (int i = 1; i <= 9; i++) {
            dfs(low, high, i, 0);
        }
        Collections.sort(numsAns);
        return numsAns;
    }

    private static void dfs(int low, int high, int i, int num) {
        if (num >= low && num <= high)
            numsAns.add(num);
        // case 2: [1000, 13000] if i=10 and num is 6789, then we should stop
        if (num > high || i > 9) return;
        dfs(low, high, i + 1, num * 10 + i);
    }

}

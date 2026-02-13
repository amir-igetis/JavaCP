package leetcodeDiscussProbPatterns.dailyQuestions;

public class FindNUniqueIntSumUpToZero {

    public static void main(String[] args) {
        int n = 5;
        int[] ans = sumZero(n);
        for (int i : ans)
            System.out.print(i + " ");
        System.out.println();
    }

    static int[] sumZero(int n) {
        int[] res = new int[n];
        int index = 0;
        for (int i = 1; i <= n / 2; i++) {
            res[index++] = i;
            res[index++] = -i;

        }

        if (n % 2 == 1)
            res[index] = 0;

        return res;
    }
}

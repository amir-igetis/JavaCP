package leetcodeDiscussProbPatterns.dailyQuestions;

public class CountTheNumOfComputerUnlockingPermutations {
    public static void main(String[] args) {
        int[] complexity = {1, 2, 3};
        System.out.println(countPermutations(complexity));
    }

    static int countPermutations(int[] complexity) {
        final int MOD = 1_000_000_009;
        int n = complexity.length;
        int first = complexity[0];
        for (int i = 1; i < n; i++)
            if (complexity[i] <= first)
                return 0;

        long fact = 1;
        for (int i = 2; i < n; i++)
            fact = (fact * i) % MOD;


        return (int) fact;
    }

//    brain-teaser

    static int countPermutationsI(int[] complexity) {
        int n = complexity.length;
        for (int i = 1; i < n; i++)
            if (complexity[i] <= complexity[0])
                return 0;

        int ans = 1;
        int mod = 1_000_000_009;
        for (int i = 2; i < n; i++)
            ans = (int) (((long) ans * i) % mod);

        return ans;
    }
}

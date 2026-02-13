package leetcodeDiscussProbPatterns.dailyQuestions;

public class TakingMaxEnergyFromTheMysticDungeon {
    public static void main(String[] args) {
        int[] energy = {5, 2, -10, -5, 1};
        int k = 3;
        System.out.println(maximumEnergyII(energy, k));
    }

    //    dynamic programming
    static int maximumEnergy(int[] energy, int k) {
        int n = energy.length;
        int[] dp = new int[n];
        int maxEnergy = Integer.MIN_VALUE;

        for (int i = n - 1; i >= 0; i--) {
            if (i + k < n)
                dp[i] = energy[i] + dp[i + k];
            else
                dp[i] = energy[i];

            maxEnergy = Math.max(maxEnergy, dp[i]);
        }
        return maxEnergy;
    }

    static int maximumEnergyI(int[] energy, int k) {
        int n = energy.length;
        int maxEnergy = Integer.MIN_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            if (i + k < n) {
                energy[i] += energy[i + k];
            }
            maxEnergy = Math.max(maxEnergy, energy[i]);
        }
        return maxEnergy;
    }

    //    prefix sum
    static int maximumEnergyII(int[] energy, int k) {
        int n = energy.length;
        int maxEnergy = Integer.MIN_VALUE;
        for (int start = 0; start < k; start++) {
            int currSum = 0;
            for (int i = n - 1 - ((n - 1 - start) % k); i >= start; i -= k) {
                currSum += energy[i];
                maxEnergy = Math.max(maxEnergy, currSum);
            }
        }
        return maxEnergy;
    }
}

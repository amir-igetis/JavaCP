package leetcodeDiscussProbPatterns.dailyQuestions;

public class MinNumOfIncrementsOnSubArrToFormATargetArr {
    public static void main(String[] args) {
        int[] target = {1, 2, 3, 2, 1};
        System.out.println(minNumberOperations(target));
    }

    static int minNumberOperations(int[] target) {
        int operations = target[0];

        for (int i = 1; i < target.length; i++) {
            if (target[i] > target[i - 1])
                operations += target[i] - target[i - 1];
        }
        return operations;
    }

    // another soln
    static int minNumberOperationsI(int[] target) {
        int min = target[0];
        for (int i = 1; i < target.length; i++) {
            min += Math.max(target[i] - target[i - 1], 0);
        }
        return min;
    }
}

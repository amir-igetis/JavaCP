package leetcodeDiscussProbPatterns.dailyQuestions;

public class FinalValOfVariableAfterPerformingOps {
    public static void main(String[] args) {
        String[] operations = {"X++", "++X", "--X", "X--"};
        System.out.println(finalValueAfterOperationsI(operations));
    }

    static int finalValueAfterOperations(String[] operations) {
        int x = 0;
        for (int i = 0; i < operations.length; i++) {
            if (operations[i] == "++X" || operations[i] == "X++")
                x += 1;
            else x -= 1;
        }
        return x;
    }

    // answer to submit on leetcode
    static int finalValueAfterOperationsI(String[] operations) {
        int x = 0;
        for (int i = 0; i < operations.length; i++) {
            if (operations[i].equals("++X") || operations[i].equals("X++"))
                x += 1;
            else x -= 1;
        }
        return x;
    }
}

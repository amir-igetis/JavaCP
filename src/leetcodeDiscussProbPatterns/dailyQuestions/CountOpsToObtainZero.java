package leetcodeDiscussProbPatterns.dailyQuestions;

public class CountOpsToObtainZero {
    public static void main(String[] args) {
        int num1 = 2, num2 = 3;

        System.out.println(countOperationsI(num1, num2));
    }

    static int countOperations(int num1, int num2) {
        int count = 0;
        while (num1 != 0 && num2 != 0) {
            if (num1 >= num2)
                num1 -= num2;
            else
                num2 -= num1;

            count++;
        }

        return count;
    }

    static int countOperationsI(int num1, int num2) {
        int count = 0;
        while (num1 != 0 && num2 != 0) {
            if (num1 >= num2) {
                count += num1 / num2;
                num1 %= num2;
            } else {
                count += num2 / num1;
                num2 %= num1;
            }
        }
        return count;
    }
}

package leetcodeDiscussProbPatterns.dailyQuestions;

public class PartitioningIntoMinimumNumOfDeciBinaryNums {
    public static void main(String[] args) {
        String n = "32";
        System.out.println(minPartitions(n));

    }

    static int minPartitions(String n) {
        int res = 0;
        for (int i = 0; i < n.length(); i++)
            res = Math.max(res, n.charAt(i) - '0');

        return res;
    }
}

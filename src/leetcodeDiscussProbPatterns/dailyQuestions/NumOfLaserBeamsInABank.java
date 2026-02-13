package leetcodeDiscussProbPatterns.dailyQuestions;

public class NumOfLaserBeamsInABank {
    public static void main(String[] args) {
        String[] bank = {"011001", "000000", "010100", "001000"};
        System.out.println(numberOfBeams(bank));
    }

    static int numberOfBeams(String[] bank) {
        int prevRowCount = 0, total = 0;
        for (String row : bank) {
            int currRowCount = calc(row);
            if (currRowCount == 0)
                continue;

            total += currRowCount * prevRowCount;
            prevRowCount = currRowCount;
        }
        return total;
    }

    private static int calc(String s) {
        int count = 0;
        for (char c : s.toCharArray())
            count += c - '0';
        return count;
    }
}

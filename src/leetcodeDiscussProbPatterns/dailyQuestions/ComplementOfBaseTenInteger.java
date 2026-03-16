package leetcodeDiscussProbPatterns.dailyQuestions;

public class ComplementOfBaseTenInteger {
    public static void main(String[] args) {
        int n = 5;
        System.out.println(bitwiseComplement(n));
    }

    static int bitwiseComplement(int n) {
        int x = 1;
        while (n > x)
            x = x * 2 + 1;
        return x - n;
    }
}

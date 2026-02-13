package leetcodeDiscussProbPatterns.dailyQuestions;

public class OneBitAndTwoBitChar {
    public static void main(String[] args) {
        int[] bits = {1, 0, 0};
        System.out.println(isOneBitCharacter(bits));
    }

    static boolean isOneBitCharacter(int[] bits) {
        int i = 0;
        int n = bits.length;
        while (i < n - 1) {
            if (bits[i] == 1)
                i += 2;
            else
                i += 1;
        }

        return i == n - 1;
    }
}

package leetcodeDiscussProbPatterns.dailyQuestions;

public class FurthestPointFromOrigin {
    public static void main(String[] args) {
        String moves = "L_RL__R";
        System.out.println(furthestDistanceFromOrigin(moves));
    }

    static int furthestDistanceFromOrigin(String moves) {
        int L = 0, R = 0, B = 0;
        for (char ch : moves.toCharArray()) {
            if (ch == 'L')
                L++;
            else if (ch == 'R')
                R++;
            else B++;

        }

        return Math.abs(L - R) + B;
    }
}

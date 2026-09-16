package leetcodeDiscussProbPatterns.dailyQuestions;

public class RectangleOverlap {
    public static void main(String[] args) {
        int[] rec1 = {0, 0, 2, 2}, rec2 = {1, 1, 3, 3};
        System.out.println(isRectangleOverlap(rec1, rec2));
    }

    // check Position
    static boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        // check if either rectangle is actually a line
        if (rec1[0] == rec1[2] || rec1[1] == rec1[3] ||
                rec2[0] == rec2[2] || rec2[1] == rec2[3]) {
            // the line cannot have positive overlap
            return false;
        }

        return !(rec1[2] <= rec2[0] ||   // left
                rec1[3] <= rec2[1] ||   // bottom
                rec1[0] >= rec2[2] ||   // right
                rec1[1] >= rec2[3]);    // top
    }

    // check Area
    static boolean isRectangleOverlapI(int[] rec1, int[] rec2) {
        return (Math.min(rec1[2], rec2[2]) > Math.max(rec1[0], rec2[0]) && // width > 0
                Math.min(rec1[3], rec2[3]) > Math.max(rec1[1], rec2[1]));  // height > 0
    }

}

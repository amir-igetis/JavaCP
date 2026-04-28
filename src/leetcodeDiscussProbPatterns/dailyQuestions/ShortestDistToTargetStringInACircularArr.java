package leetcodeDiscussProbPatterns.dailyQuestions;

public class ShortestDistToTargetStringInACircularArr {
    public static void main(String[] args) {
        String[] words = {"hello", "i", "am", "leetcode", "hello"};
        String target = "hello";
        int startIndex = 1;

        System.out.println(closestTarget(words, target, startIndex));
    }

    static int closestTarget(String[] words, String target, int startIndex) {
        int ans = words.length;
        int n = words.length;
        for (int i = 0; i < n; i++) {
            if (words[i].equals(target)) {
                int dist = Math.abs(i - startIndex);
                ans = Math.min(ans, Math.min(dist, n - dist));
            }
        }
        return ans < n ? ans : -1;
    }
}

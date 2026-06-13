package leetcodeDiscussProbPatterns.dailyQuestions;

public class WeightedWordMapping {
    public static void main(String[] args) {
        String[] words = {"abcd", "def", "xyz"};
        int[] weights = {5, 3, 12, 14, 1, 2, 3, 2, 10, 6, 6, 9, 7, 8, 7, 10, 8, 9, 6, 9, 9, 8, 3, 7, 7, 2};
        System.out.println(mapWordWeights(words, weights));
    }

    // simulation tc O(n) and sc O(1)
    static String mapWordWeights(String[] words, int[] weights) {
        StringBuilder ans = new StringBuilder(words.length);
        for (String word : words) {
            int s = 0;
            for (int i = 0; i < word.length(); i++)
                s += weights[word.charAt(i) - 'a'];

            ans.append((char) ('z' - (s % 26)));

        }
        return ans.toString();
    }
}

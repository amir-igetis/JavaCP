package leetcodeDiscussProbPatterns.dailyQuestions;

public class MinScoreOfAPathBetweenTwoCities {
    public static void main(String[] args) {
        int[][] roads = {{1, 2, 9}, {2, 3, 6}, {2, 4, 5}, {1, 4, 7}};
        int n = 4;
        System.out.println(minScore(n, roads));
    }

    static int minScore(int n, int[][] roads) {
        int[] root = new int[n + 1];
        for (int i = 0; i <= n; i++)
            root[i] = i;
        for (int[] r : roads)
            root[find(root, r[0])] = find(root, r[1]);
        int res = 1001;
        for (int[] r : roads)
            if (find(root, r[0]) == find(root, 1))
                res = Math.min(res, r[2]);

        return res;
    }

    private static int find(int[] root, int i) {
        if (root[i] == i)
            return i;
        return root[i] = find(root, root[i]);
    }

}

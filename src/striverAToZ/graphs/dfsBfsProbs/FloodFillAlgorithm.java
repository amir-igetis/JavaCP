package striverAToZ.graphs.dfsBfsProbs;

public class FloodFillAlgorithm {
    public static void main(String[] args) {
        int[][] image = {
                {1, 1, 1,},
                {1, 1, 0},
                {1, 0, 1}
        };

        // sr = 1, sc = 1, newColor = 2
        int[][] ans = floodFill(image, 1, 1, 2);
        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans[i].length; j++) {
                System.out.print(ans[i][j] + " ");
            }
            System.out.println();
        }
    }

    // soln for
    // https://practice.geeksforgeeks.org/problems/flood-fill-algorithm1856/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=flood-fill-algorithm
    // soln for https://leetcode.com/problems/flood-fill/
    static int[][] floodFill(int[][] image, int sr, int sc, int color) {

        int initialColor = image[sr][sc];

        // No need to do anything if color is the same
        if (initialColor == color) {
            return image;
        }

        int[] delRow = {-1, 0, 1, 0};
        int[] delCol = {0, 1, 0, -1};

        dfs(sr, sc, image, initialColor, color, delRow, delCol);

        return image;
    }

    private static void dfs(int row, int col,
                            int[][] image,
                            int initialColor,
                            int newColor,
                            int[] delRow,
                            int[] delCol) {

        image[row][col] = newColor;

        int m = image.length;
        int n = image[0].length;

        for (int i = 0; i < 4; i++) {

            int newRow = row + delRow[i];
            int newCol = col + delCol[i];

            if (newRow >= 0 && newRow < m &&
                    newCol >= 0 && newCol < n &&
                    image[newRow][newCol] == initialColor) {

                dfs(newRow, newCol,
                        image,
                        initialColor,
                        newColor,
                        delRow,
                        delCol);
            }
        }
    }

}

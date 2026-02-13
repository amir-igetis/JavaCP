package LeetcodeInterview.divideAndConquer;

public class ConstructQuadTree {
    public static void main(String[] args) {
//
        int[][] grid = {{0, 1}, {1, 0}};

        Node ans = construct(grid);
        System.out.println(ans.val);
    }

    static Node construct(int[][] grid) {
        return buildTree(grid, 0, 0, grid.length);
    }

    private static Node buildTree(int[][] grid, int row, int col, int size) {
        if (isUniform(grid, row, col, size))
            return new Node(grid[row][col] == 1, true);

        int newSize = size / 2;

        Node topLeft = buildTree(grid, row, col, newSize);
        Node topRight = buildTree(grid, row, col + newSize, newSize);
        Node bottomLeft = buildTree(grid, row + newSize, col, newSize);
        Node bottomRight = buildTree(grid, row + newSize, col + newSize, newSize);

        return new Node(false, false, topLeft, topRight, bottomLeft, bottomRight);

    }

    private static boolean isUniform(int[][] grid, int row, int col, int size) {
        int value = grid[row][col];
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (grid[i][j] != value)
                    return false;
            }
        }
        return true;
    }
}

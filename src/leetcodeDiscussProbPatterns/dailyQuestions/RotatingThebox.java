package leetcodeDiscussProbPatterns.dailyQuestions;

public class RotatingThebox {
    public static void main(String[] args) {
        char[][] boxGrid = {{'#', '.', '#' }};
        char[][] ans = rotateThebox(boxGrid);
        for (char[] i : ans) {
            for (char j : i)
                System.out.print(j + " ");
            System.out.println();
        }
        System.out.println();
    }

    // Row by Row (Brute Force)
    static char[][] rotateTheBoxI(char[][] box) {
        int m = box.length;
        int n = box[0].length;
        char[][] result = new char[n][m];

        // Create the transpose of the input grid in `result`
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result[i][j] = box[j][i];
            }
        }

        // Reverse each row in the transpose grid to complete the 90° rotation
        for (int i = 0; i < n; i++) {
            reverse(result[i]);
        }

        // Apply gravity to let stones fall to the lowest possible empty cell in each column
        for (int j = 0; j < m; j++) {
            // Process each cell in column `j` from bottom to top
            for (int i = n - 1; i >= 0; i--) {
                if (result[i][j] == '.') { // Found an empty cell; check if a stone can fall into it
                    int nextRowWithStone = -1;

                    // Look for a stone directly above the empty cell `result[i][j]`
                    for (int k = i - 1; k >= 0; k--) {
                        if (result[k][j] == '*') break; // Obstacle blocks any stones above
                        if (result[k][j] == '#') { // Stone found with no obstacles in between
                            nextRowWithStone = k;
                            break;
                        }
                    }

                    // If a stone was found above, let it fall into the empty cell `result[i][j]`
                    if (nextRowWithStone != -1) {
                        result[nextRowWithStone][j] = '.';
                        result[i][j] = '#';
                    }
                }
            }
        }
        return result;
    }


    // Helper function to reverse an array
    private static void reverse(char[] row) {
        int left = 0;
        int right = row.length - 1;
        while (left < right) {
            char temp = row[left];
            row[left] = row[right];
            row[right] = temp;
            left++;
            right--;
        }
    }

    // Row By Row (Optimized)
    static char[][] rotateTheBoxII(char[][] box) {
        int m = box.length;
        int n = box[0].length;
        char[][] result = new char[n][m];

        // Create the transpose of the input grid in `result`
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result[i][j] = box[j][i];
            }
        }

        // Reverse each row in the transpose grid to complete the 90° rotation
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m / 2; j++) {
                char temp = result[i][j];
                result[i][j] = result[i][m - 1 - j];
                result[i][m - 1 - j] = temp;
            }
        }

        // Apply gravity to let stones fall to the lowest possible empty cell in each column
        for (int j = 0; j < m; j++) {
            int lowestRowWithEmptyCell = n - 1;
            // Process each cell in column `j` from bottom to top
            for (int i = n - 1; i >= 0; i--) {
                // Found a stone - let it fall to the lowest empty cell
                if (result[i][j] == '#') {
                    result[i][j] = '.';
                    result[lowestRowWithEmptyCell][j] = '#';
                    lowestRowWithEmptyCell--;
                }
                // Found an obstacle - reset `lowestRowWithEmptyCell` to the row directly above it
                if (result[i][j] == '*') {
                    lowestRowWithEmptyCell = i - 1;
                }
            }
        }
        return result;
    }


    // Combine rotation and gravity operations
    static char[][] rotateThebox(char[][] boxGrid) {
        int m = boxGrid.length;
        int n = boxGrid[0].length;
        char[][] result = new char[n][m];

        // Initialize the result grid with '.'
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result[i][j] = '.';
            }
        }

        // Apply gravity to let stones fall to the lowest possible empty cell in each column
        for (int i = 0; i < m; i++) {
            int lowestRowWithEmptyCell = n - 1;
            // Process each cell in row `i` in reversed order
            for (int j = n - 1; j >= 0; j--) {
                // Found a stone - let it fall to the lowest empty cell
                if (boxGrid[i][j] == '#') {
                    // Place it in the correct position in the rotated grid
                    result[lowestRowWithEmptyCell][m - i - 1] = '#';
                    lowestRowWithEmptyCell--;
                }
                // Found an obstacle - reset `lowestRowWithEmptyCell` to the row directly above it
                if (boxGrid[i][j] == '*') {
                    // Place the obstacle in the correct position in the rotated grid
                    result[j][m - i - 1] = '*';
                    lowestRowWithEmptyCell = j - 1;
                }
            }
        }
        return result;

    }
}

package leetcodeDiscussProbPatterns.dailyQuestions;

public class CountServersThatCommunicate {
    public static void main(String[] args) {
        int[][] grid = {{1, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
        System.out.println(countServers(grid));
        System.out.println(countServersI(grid));
        System.out.println(countServersII(grid));
        System.out.println(countServersIII(grid));

    }


    // Brute Force

    /// Let m be the number of rows and n be the number of columns in the grid.
    ///
    /// Time complexity: O(m⋅n⋅(m+n))
    ///
    /// The algorithm traverses through each cell in the grid using nested loops, where the outer loop runs m times (for each row) and the inner loop runs n times (for each column). For each cell containing a server (grid[row][col] == 1), it performs two additional checks:
    ///
    /// It checks the entire row to see if there is another server in the same row. This involves iterating over n columns.
    /// If no server is found in the same row, it checks the entire column to see if there is another server in the same column. This involves iterating over m rows.
    /// Since these checks are performed for each server, the worst-case time complexity is O(m⋅n⋅(m+n)).
    ///
    /// Space complexity: O(1)
    ///
    /// The algorithm uses a constant amount of extra space, as it only maintains a few variables (numRows, numCols, communicableServersCount, canCommunicate, etc.). No additional data structures are used that scale with the input size. Therefore, the space complexity is O(1).
    static int countServers(int[][] grid) {
        int numRows = grid.length;
        int numCols = numRows > 0 ? grid[0].length : 0;
        int communicableServersCount = 0;

        // Traverse through the grid
        for (int row = 0; row < numRows; ++row) {
            for (int col = 0; col < numCols; ++col) {
                if (grid[row][col] == 1) {
                    boolean canCommunicate = false;

                    // Check for communication in the same row
                    for (int otherCol = 0; otherCol < numCols; ++otherCol) {
                        if (otherCol != col && grid[row][otherCol] == 1) {
                            canCommunicate = true;
                            break;
                        }
                    }

                    // If a server was found in the same row, increment
                    // communicableServersCount
                    if (canCommunicate) {
                        communicableServersCount++;
                    } else {
                        // Check for communication in the same column
                        for (int otherRow = 0; otherRow < numRows; ++otherRow) {
                            if (otherRow != row && grid[otherRow][col] == 1) {
                                canCommunicate = true;
                                break;
                            }
                        }

                        // If a server was found in the same column, increment
                        // communicableServersCount
                        if (canCommunicate) {
                            communicableServersCount++;
                        }
                    }
                }
            }
        }

        return communicableServersCount;
    }

    // Track Using Two Array

    /// Let m be the number of rows and n be the number of columns in the grid.
    ///
    /// Time complexity: O(m⋅n)
    ///
    /// The first nested loop iterates over each row in the grid to count the number of servers in each row and column. The outer loop runs m times (for each row), and the inner loop runs n times (for each column). This results in a time complexity of O(m⋅n).
    ///
    /// The second nested loop also iterates over each row in the grid to determine if a server can communicate with others in its row or column. This again involves an outer loop running m times and an inner loop running n times, resulting in a time complexity of O(m⋅n).
    ///
    /// Since both loops are independent and each has a time complexity of O(m⋅n), the overall time complexity is O(m⋅n).
    ///
    /// Space complexity: O(m+n)
    ///
    /// The algorithm uses two additional arrays:
    /// - rowCounts of size n (number of columns) to store the count of servers in each column.
    /// - colCounts of size m (number of rows) to store the count of servers in each row.
    ///
    /// The space required for these arrays is O(m+n).
    ///
    /// The space used by the input grid is not counted towards the space complexity as it is part of the input.
    static int countServersI(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int[] rowCounts = new int[grid[0].length];
        int[] colCounts = new int[grid.length];

        // Count servers in each row and each column
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    rowCounts[col]++;
                    colCounts[row]++;
                }
            }
        }

        int communicableServersCount = 0;

        // Count servers that can communicate (in the same row or column)
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    if (rowCounts[col] > 1 || colCounts[row] > 1) {
                        communicableServersCount++;
                    }
                }
            }
        }

        return communicableServersCount;
    }

    // Server Grouping

    /// Let m be the number of rows and n be the number of columns in the grid.
    ///
    /// Time complexity: O(m⋅n)
    ///
    /// The algorithm performs two passes over the grid. In the first pass, it iterates over each cell in the grid to count the number of servers in each row and column. This involves nested loops where the outer loop runs m times (for each row) and the inner loop runs n times (for each column). This results in a time complexity of O(m⋅n).
    ///
    /// The second pass iterates over the rows to check if servers can communicate based on the counts computed in the first pass. This pass runs in O(m) time. Since O(m⋅n) dominates O(m), the overall time complexity is O(m⋅n).
    ///
    /// Space complexity: O(m+n)
    ///
    /// The algorithm uses two additional data structures: colCount and lastServerInRow. The colCount array has a size of n (number of columns), and the lastServerInRow array has a size of m (number of rows). Therefore, the space complexity is O(m+n).
    ///
    /// The space used by the input grid is not counted towards the space complexity as it is part of the input.
    static int countServersII(int[][] grid) {
        int communicableServersCount = 0;
        int[] colCount = new int[grid[0].length];
        int[] lastServerInRow = new int[grid.length];
        for (int i = 0; i < lastServerInRow.length; i++) {
            lastServerInRow[i] = -1;
        }

        // First pass to count servers in each row and column
        for (int row = 0; row < grid.length; row++) {
            int serverCountInRow = 0;
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    serverCountInRow++;
                    colCount[col]++;
                    lastServerInRow[row] = col;
                }
            }
            // If there is more than one server in the row, increase the count
            if (serverCountInRow != 1) {
                communicableServersCount += serverCountInRow;
                lastServerInRow[row] = -1;
            }
        }

        // Second pass to check if servers can communicate
        for (int row = 0; row < grid.length; row++) {
            if (
                    lastServerInRow[row] != -1 &&
                            colCount[lastServerInRow[row]] > 1
            ) {
                communicableServersCount++;
            }
        }

        return communicableServersCount;
    }

    // Space Optimized

    /// Let m be the number of rows and n be the number of columns in the grid.
    ///
    /// Time complexity: O(m×n)
    ///
    /// The algorithm iterates over each cell in the grid once to count the number of servers in each row and determine if they can communicate. For each row, it takes O(n) time to count the servers and O(m) time to check if a server in a row can communicate with another server in the same column. Since there are m rows, the total time complexity is O(m×n).
    ///
    /// The nested loops and the checks for communication contribute to this time complexity. The outer loop runs m times, and the inner loops run n times and m times respectively, leading to the overall time complexity of O(m×n).
    ///
    /// Space complexity: O(1)
    ///
    /// The space complexity is constant because the algorithm does not allocate any additional memory that depends on the size of the input grid. All operations are performed in-place using a fixed number of variables.
    static int countServersIII(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int communicableServersCount = 0;

        for (int rowIndex = 0; rowIndex < rows; ++rowIndex) {
            int rowCounts = 0;
            int serverColumnIndex = -1;

            // Count the number of servers in the current row and record the
            // first server's column index.
            for (int colIndex = 0; colIndex < cols; ++colIndex) {
                if (grid[rowIndex][colIndex] == 1) {
                    if (rowCounts == 0) {
                        serverColumnIndex = colIndex;
                    }
                    rowCounts++;
                }
            }

            // If more than one server in the row, it can communicate
            boolean canCommunicate = (rowCounts != 1);

            // If there's only one server in the row, check if there's a server
            // in the same column in another row.
            if (!canCommunicate) {
                for (int i = 0; i < rows; ++i) {
                    if (i != rowIndex && grid[i][serverColumnIndex] == 1) {
                        canCommunicate = true;
                        break;
                    }
                }
            }

            // If the server can communicate, add rowCounts to the result.
            if (canCommunicate) {
                communicableServersCount += rowCounts;
            }
        }

        return communicableServersCount;
    }
}

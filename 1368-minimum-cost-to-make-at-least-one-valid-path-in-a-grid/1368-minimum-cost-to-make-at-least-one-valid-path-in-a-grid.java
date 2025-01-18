class Solution {

    public int minCost(int[][] grid) {
        int numRows = grid.length, numCols = grid[0].length;
        int[][] minChanges = new int[numRows][numCols];

        // Initialize all cells with max value
        for (int[] row : minChanges) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        minChanges[0][0] = 0;

        while (true) {
            // Store previous state to check for convergence
            int[][] prevState = new int[numRows][numCols];
            for (int row = 0; row < numRows; row++) {
                prevState[row] = Arrays.copyOf(minChanges[row], numCols);
            }

            // Forward pass: check cells coming from left and top
            for (int row = 0; row < numRows; row++) {
                for (int col = 0; col < numCols; col++) {
                    // Check cell above
                    if (row > 0) {
                        minChanges[row][col] = Math.min(
                            minChanges[row][col],
                            minChanges[row - 1][col] +
                            (grid[row - 1][col] == 3 ? 0 : 1)
                        );
                    }
                    // Check cell to the left
                    if (col > 0) {
                        minChanges[row][col] = Math.min(
                            minChanges[row][col],
                            minChanges[row][col - 1] +
                            (grid[row][col - 1] == 1 ? 0 : 1)
                        );
                    }
                }
            }

            // Backward pass: check cells coming from right and bottom
            for (int row = numRows - 1; row >= 0; row--) {
                for (int col = numCols - 1; col >= 0; col--) {
                    // Check cell below
                    if (row < numRows - 1) {
                        minChanges[row][col] = Math.min(
                            minChanges[row][col],
                            minChanges[row + 1][col] +
                            (grid[row + 1][col] == 4 ? 0 : 1)
                        );
                    }
                    // Check cell to the right
                    if (col < numCols - 1) {
                        minChanges[row][col] = Math.min(
                            minChanges[row][col],
                            minChanges[row][col + 1] +
                            (grid[row][col + 1] == 2 ? 0 : 1)
                        );
                    }
                }
            }

            // If no changes were made in this iteration, we've found optimal solution
            if (Arrays.deepEquals(prevState, minChanges)) {
                break;
            }
        }

        return minChanges[numRows - 1][numCols - 1];
    }
}
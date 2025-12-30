class Solution {
    public int numMagicSquaresInside(int[][] grid) {
        int R = grid.length, C = grid[0].length;
        if (R < 3 || C < 3) return 0;

        // Distinct digits 1-9, Center must be 5.
        // Pre-computed 8 valid patterns (flattened for easier comparison)
        int[][] patterns = {
            {8, 1, 6, 3, 5, 7, 4, 9, 2}, {6, 1, 8, 7, 5, 3, 2, 9, 4},
            {4, 9, 2, 3, 5, 7, 8, 1, 6}, {2, 9, 4, 7, 5, 3, 6, 1, 8},
            {8, 3, 4, 1, 5, 9, 6, 7, 2}, {4, 3, 8, 9, 5, 1, 2, 7, 6},
            {6, 7, 2, 1, 5, 9, 8, 3, 4}, {2, 7, 6, 9, 5, 1, 4, 3, 8}
        };

        int count = 0;
        for (int r = 0; r < R - 2; r++) {
            for (int c = 0; c < C - 2; c++) {
                // Optimization: Center must be 5
                if (grid[r + 1][c + 1] != 5) continue;
                if (matches(grid, r, c, patterns)) count++;
            }
        }
        return count;
    }

    private boolean matches(int[][] grid, int r, int c, int[][] patterns) {
        int[] subgrid = {
            grid[r][c], grid[r][c + 1], grid[r][c + 2],
            grid[r + 1][c], grid[r + 1][c + 1], grid[r + 1][c + 2],
            grid[r + 2][c], grid[r + 2][c + 1], grid[r + 2][c + 2]
        };
        
        for (int[] p : patterns) {
            if (Arrays.equals(subgrid, p)) return true;
        }
        return false;
    }
}
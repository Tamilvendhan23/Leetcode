class Solution {
    public int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k >> 1; j++) {
                int temp = grid[x + j][y + i];
                grid[x + j][y + i] = grid[x + k - j - 1][y + i];
                grid[x + k - j - 1][y + i] = temp;
            }
        }

        return grid;
    }
}